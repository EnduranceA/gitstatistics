package ru.itis.gitstats.service;

import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import lombok.SneakyThrows;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import ru.itis.gitstats.dto.DeveloperProductivityDto;
import ru.itis.gitstats.dto.github.*;
import ru.itis.gitstats.dto.github.pullrequest.CommentDto;
import ru.itis.gitstats.dto.github.pullrequest.PullRequestDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityShortDto;
import ru.itis.gitstats.dto.github.statistics.WeeksItemDto;
import ru.itis.gitstats.mappers.GitHubMapper;
import ru.itis.gitstats.models.Characteristics;
import ru.itis.gitstats.utils.nlp.StanfordNlp;
import ru.itis.gitstats.utils.parser.JavaDependencies;
import ru.itis.gitstats.utils.parser.JavaPackage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CharacteristicServiceImpl implements CharacteristicService {

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NLPService nlpService;

    @Autowired
    private GitHubMapper mapper;

    @Override
    public List<ContributorCommitActivityShortDto> getActivityRating(String owner, String repo) {
        return gitHubService.findContributorsCommitActivity(owner, repo)
                .stream().map(contributorCommitActivity -> ContributorCommitActivityShortDto.builder()
                        .name(contributorCommitActivity.getAuthor().getLogin())
                        .add(contributorCommitActivity.getWeeks().stream().map(WeeksItemDto::getA).reduce(0, Integer::sum))
                        .delete(contributorCommitActivity.getWeeks().stream().map(WeeksItemDto::getD).reduce(0, Integer::sum))
                        .commit(contributorCommitActivity.getWeeks().stream().map(WeeksItemDto::getC).reduce(0, Integer::sum))
                        .build())
                .sorted(Comparator.comparingInt(ContributorCommitActivityShortDto::getCommit))
                .collect(Collectors.toList());
    }

    @Override
    public long getAverageTimeEstimationPR(String owner, String repo) {
        List<PullRequestDto> pullRequests = gitHubService.findPullRequests(owner, repo, "all");
        long countDay = 0;
        for (PullRequestDto pullRequest : pullRequests) {
            if (Objects.nonNull(pullRequest.getClosedAt()) && Objects.nonNull(pullRequest.getCreatedAt())) {
                Period period = Period.between(LocalDate.ofInstant(Instant.parse(pullRequest.getCreatedAt()), ZoneId.systemDefault()),
                        LocalDate.ofInstant(Instant.parse(pullRequest.getClosedAt()), ZoneId.systemDefault()));
                countDay += period.getDays();
            }
        }
        return pullRequests.size() != 0 ? countDay / pullRequests.size() : 0;
    }

    @Override
    public Set<String> getPackagesProneToBreakage(String directory) {
        int sumCe = 0;
        Collection<JavaPackage> packages = getPackages(directory);
        for (JavaPackage jPackage : packages) {
            int Ce = jPackage.efferentCoupling();
            sumCe += Ce;
        }
        double average = sumCe * 1.0 / packages.size();
        return packages.stream()
                .filter(p -> p.efferentCoupling() >= average)
                .map(JavaPackage::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getImportantPackages(String directory) {
        int sumCa = 0;
        Collection<JavaPackage> packages = getPackages(directory);
        for (JavaPackage jPackage : packages) {
            int Ca = jPackage.afferentCoupling();
            sumCa += Ca;
        }
        double average = sumCa * 1.0 / packages.size();
        return packages.stream()
                .filter(p -> p.afferentCoupling() >= average)
                .map(JavaPackage::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getBalancedPackages(String directory) {
        Set<String> balancedPackages = new HashSet<>();
        Collection<JavaPackage> packages = getPackages(directory);
        for (JavaPackage jPackage : packages) {
            float D = Math.abs(jPackage.instability() + jPackage.abstractness() - 1);
            if (D == 1.0) {
                balancedPackages.add(jPackage.getName());
            }
        }
        return balancedPackages;
    }

    @Override
    public Set<String> getStablePackages(String directory) {
        Set<String> stablePackages = new HashSet<>();
        Collection<JavaPackage> packages = getPackages(directory);
        for (JavaPackage jPackage : packages) {
            if (jPackage.instability() <= 0.3) {
                stablePackages.add(jPackage.getName());
            }
        }
        return stablePackages;
    }

    @Override
    public Set<String> getUnstablePackages(String directory) {
        Set<String> unstablePackages = new HashSet<>();
        Collection<JavaPackage> packages = getPackages(directory);
        for (JavaPackage jPackage : packages) {
            if (jPackage.instability() >= 0.7) {
                unstablePackages.add(jPackage.getName());
            }
        }
        return unstablePackages;
    }

    @Override
    public String getTeamAtmosphere(String owner, String repo) throws IOException {
        List<CommentDto> comments = gitHubService.findCommentsOnPullRequests(owner, repo);
        String language = getLanguage(comments.get(0).getBody());
        int size = comments.size();
        if (size > 0) {
            int neutral = 0;
            int negative = 0;
            int positive = 0;
            System.out.println(language);
            if (language.equals("en")) {
                for (CommentDto comment : comments) {
                    Map<String, Integer> res = nlpService.analyzeEnglishText(comment.getBody());
                    if (res.containsKey("Neutral")) {
                        neutral += 1;
                    } else if (res.containsKey("Negative") || res.containsKey("Very negative")) {
                        negative += 1;
                    } else if (res.containsKey("Positive") || res.containsKey("Very positive")) {
                        positive += 1;
                    }
                }
            } else if (language.equals("ru")) {
                for (CommentDto comment : comments) {
                    Map<String, Double> result = nlpService.analyzeRussianText(comment.getBody()).get(0);
                    if (result.containsKey("neutral")) {
                        neutral += 1;
                    } else if (result.containsKey("negative")) {
                        negative += 1;
                    } else if (result.containsKey("positive")) {
                        positive += 1;
                    }
                }
            }
            if (neutral >= negative && neutral >= positive) {
                    return "В коллективе преобладает нейтральная атмосфера.";
                } else if (negative >= neutral && negative >= positive) {
                    return "В коллективе преобладает негативная атмосфера.";
                } else {
                    return "В коллективе преобладает доброжелательная, позитивная атмосфера.";
            }
        }
        return "Невозможно определить атмосферу в команде, так как нет комментариев к pull-request-ам";
    }

    @Override
    public String getDeveloperTurnoverPerYear(String owner, String repo) {
        List<CommitDto> commits = gitHubService.findRepoCommits(owner, repo);
        List<UserDto> allCommitters = gitHubService.findRepoContributors(owner, repo);

        Map<String, Set<String>> months = new HashMap<>();
        for (CommitDto commit: commits) {
            CommitterDto committer = commit.getCommitDetails().getCommitter();
            Calendar c = parseDate(committer.getDate());
            String stringDate = c.get(Calendar.YEAR) + " " + (c.get(Calendar.MONTH) + 1);
            Set<String> committers;

            if (!months.containsKey(stringDate)) {
                committers = new HashSet<>();
                committers.add(committer.getEmail());
                months.put(stringDate, committers);
            }
            else {
                months.get(stringDate).add(committer.getEmail());
            }
        }

        int countCommitters = allCommitters.size();

        //среднесписочное количество разработчиков в месяц
        int averageCount = countCommitters/months.size();

        Map.Entry<String, Set<String>> entry = months.entrySet().iterator().next();

        //кол-во выбывших разработчиков
        int countRetiredDevelopers = countCommitters - entry.getValue().size();

        int developerTurnover = averageCount != 0 ? countRetiredDevelopers * 100/averageCount : 0;
        if (developerTurnover >= 10) {
            return "Текучесть ваших разработчиков составляет " + developerTurnover + "%. Полученный результат превышает " +
                    "нормативное значение коэффициента текучести (8-10%).";
        }
        return "Текучесть ваших разработчиков составляет " + developerTurnover + "%. Полученный результат не превышает " +
                "нормативное значение коэффициента текучести (8-10%).";
    }

    @Override
    public List<DeveloperProductivityDto> getDevelopersPerformPoorly(String owner, String repo) {
        List<ContributorCommitActivityDto> activities = gitHubService.findContributorsCommitActivity(owner, repo);
        List<DeveloperProductivityDto> result = new ArrayList<>();

        for (ContributorCommitActivityDto activity: activities) {
            List<WeeksItemDto> weeksItems = activity.getWeeks();
            for (WeeksItemDto weeksItem: weeksItems) {
                if (weeksItem.getC() != 0) {
                    if (weeksItem.getC() < 3 || weeksItem.getA() < 300) {
                        DeveloperProductivityDto dto = DeveloperProductivityDto.builder()
                                .author(activity.getAuthor().getLogin())
                                .date(parseUnixDate(weeksItem.getW()))
                                .weeksItem(weeksItem)
                                .build();
                        result.add(dto);
                    }
                }
            }
        }
        return result;
    }

    @SneakyThrows
    @Override
    public CharacteristicsDto getAllCharacteristics(String repo, String owner) {
        Optional<Characteristics> fromStorage = storageService.findCharacteristicsByRepo(owner, repo);
        Characteristics characteristics;
        if (fromStorage.isPresent()) {
            characteristics = fromStorage.get();
        } else {
            CharacteristicsDto characteristicsDto = calculateCharacteristicsForRepo(repo, owner);
            characteristics = storageService.saveCharacteristicsByRepo(repo, owner, characteristicsDto);
        }
        return mapper.characteristicsModelToDto(characteristics);
    }

    @Override
    public CharacteristicsDto calculateCharacteristicsForRepo(String repo, String owner) throws IOException {
        RepoDto repository = gitHubService.findRepo(owner, repo);

        File directory = cloneRepository(repository.getCloneUrl());
        String path = directory.getAbsolutePath();

        CharacteristicsDto dto = CharacteristicsDto.builder()
                .contributorsActivity(getActivityRating(owner, repo))
                .averageTimeEstimationPR(getAverageTimeEstimationPR(owner, repo))
                .importantPackages(getImportantPackages(path))
                .packagesProneToBreakage(getPackagesProneToBreakage(path))
                .stablePackages(getStablePackages(path))
                .unstablePackages(getUnstablePackages(path))
                .balancedPackages(getBalancedPackages(path))
                .teamAtmosphere(getTeamAtmosphere(owner, repo))
                .developersPerformPoorly(getDevelopersPerformPoorly(owner, repo))
                .developerTurnoverPerYear(getDeveloperTurnoverPerYear(owner, repo))
                .build();

        // Удаляем все файлы во временной папке, тк уже все проанализировали
        FileSystemUtils.deleteRecursively(directory);
        return dto;
    }

    @SneakyThrows
    private String getLanguage(String text) {
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .build();

        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();

        TextObject textObject = textObjectFactory.forText(text);
        return languageDetector.detect(textObject).get().getLanguage();
    }

    private File cloneRepository(String url) {
        File tempDir = new File("temp_" + UUID.randomUUID() + "/");
        tempDir.mkdirs();
        try {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(tempDir)
                    .call();
        } catch (GitAPIException e) {
            throw new IllegalArgumentException(e);
        }
        return tempDir;
    }

    private Calendar parseDate(String stringToParse) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(stringToParse);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String parseUnixDate(long numberWeek) {
        Date date = new Date(numberWeek * 1000);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (c.get(Calendar.DAY_OF_MONTH) + 1) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR);
    }

    private Collection<JavaPackage> getPackages(String directory) {
        JavaDependencies javaDepend = new JavaDependencies();
        javaDepend.addDirectory(directory);
        return javaDepend.analyze();
    }

    @Override
    public List<DeveloperProductivityDto> getDevelopersRecycles(String owner, String repo) {
        List<CommentDto> comments = gitHubService.findCommentsOnPullRequests(owner, repo);
        return List.of();
    }
}


