package ru.itis.gitstats.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.dto.github.UserDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.feign.GitHubClient;
import ru.itis.gitstats.mappers.GitHubMapper;
import ru.itis.gitstats.models.Characteristics;
import ru.itis.gitstats.models.Repo;
import ru.itis.gitstats.models.User;
import ru.itis.gitstats.repositories.CharacteristicsRepository;
import ru.itis.gitstats.repositories.RepoRepository;
import ru.itis.gitstats.repositories.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private GitHubService gitHubService;
    @Autowired
    private CharacteristicService characteristicService;

    @Autowired
    private RepoRepository repoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @Autowired
    private GitHubMapper gitHubMapper;
    @Autowired
    private GitHubClient gitHubClient;

    // Методы поиска в хранилище
    @Override
    public Optional<List<Repo>> findReposByUsername(String username) {
        List<Repo> repos = repoRepository.findAllByOwnerLogin(username);
        return repos.isEmpty() ? Optional.empty() : Optional.of(repos);
    }

    @Override
    public Optional<User> findUserByUserName(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public Optional<Repo> findRepoByOwnerAndName(String owner, String repo) {
        return repoRepository.findByOwnerLoginAndName(owner, repo);
    }
    // --------------------


    // Методы обновления и сохранения данных в хранилище
    @Override
    public void updateReposByUsername(String username, List<RepoDto> fromApi) {
        UserDto user = gitHubService.getUserByUsername(username);
        User owner = gitHubMapper.userDtoToModel(user);

        List<Repo> repos = gitHubMapper.repoDtosToModels(fromApi);
        repos.forEach(repo -> repo.setOwner(owner));
        repoRepository.saveAll(repos);
    }

    @Override
    public User saveToStorage(UserDto fromApi) {
        // Проверяем, есть ли уже пользователь с таким логином
        Optional<User> byLogin = userRepository.findByLogin(fromApi.getLogin());
        User user;
        if (byLogin.isPresent()) {
            user = byLogin.get();
        } else {
            // Если нет, то сохраняем
            user = gitHubMapper.userDtoToModel(fromApi);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public Repo saveRepoByUsername(String username, RepoDto fromApi) {
        UserDto user = gitHubService.getUserByUsername(username);
        User owner = gitHubMapper.userDtoToModel(user);

        Repo repo = gitHubMapper.repoDtoToModel(fromApi);
        repo.setOwner(owner);

        repoRepository.save(repo);
        return repo;
    }

    @Override
    public Optional<List<User>> findRepoContributors(String owner, String repo) {
        Optional<Repo> optionalRepo = repoRepository.findByOwnerLoginAndName(owner, repo);
        if (optionalRepo.isEmpty()) {
            return Optional.empty();
        } else {
            Set<User> contributors = optionalRepo.get().getContributors();
            if (contributors.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(new ArrayList<>(contributors));
            }
        }
    }

    @Override
    public List<User> saveRepoContributors(String owner, String repo, List<UserDto> fromApi) {
        Repo repository = gitHubMapper.repoDtoToModel(gitHubService.findRepo(owner, repo));

        List<User> contributors = fromApi.stream().map(this::saveToStorage).collect(Collectors.toList());
        repository.setContributors(new HashSet<>(contributors));

        repoRepository.save(repository);
        return contributors;
    }

    @Override
    public Optional<Characteristics> findCharacteristicsByRepo(String owner, String repo) {
        return characteristicsRepository.findByRepoOwnerLoginAndRepoName(owner, repo);
    }

    @Override
    public Characteristics saveCharacteristicsByRepo(String repo, String owner, CharacteristicsDto characteristicsDto) {
        Repo repository = gitHubService.findRepoModel(owner, repo);

        Characteristics characteristics = gitHubMapper.characteristicsDtoToModel(characteristicsDto);
        characteristics.setRepo(repository);

        characteristicsRepository.save(characteristics);
        return characteristics;
    }
    // ------------------

    // Методы обновления данных. Вызываются шедулером
    @Override
    @Transactional
    public void updateRepo(Repo repo) {
        Repo repository = gitHubMapper.repoDtoToModel(
                gitHubClient.findRepo(repo.getOwner().getLogin(), repo.getName())
        );
        repository.setId(repo.getId());
        repository.setOwner(repo.getOwner());
        repoRepository.save(repository);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        User newUser = gitHubMapper.userDtoToModel(gitHubClient.findUser(user.getLogin()));
        newUser.setId(user.getId());
        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void updateCharacteristics(Characteristics characteristics) {
        Repo repo = characteristics.getRepo();
        CharacteristicsDto characteristicsDto = characteristicService.calculateCharacteristicsForRepo(repo.getName(), repo.getOwner().getLogin());
        Characteristics newCharacteristics = gitHubMapper.characteristicsDtoToModel(characteristicsDto);
        newCharacteristics.setRepo(repo);
        characteristicsRepository.save(characteristics);
    }
}
