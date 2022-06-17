package ru.itis.gitstats.service;

import lombok.SneakyThrows;
import ru.itis.gitstats.dto.DeveloperProductivityDto;
import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityShortDto;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CharacteristicService {

    //РЕЙТИНГ АКТИВНОСТИ РАЗРАБОТЧИКОВ
    List<ContributorCommitActivityShortDto> getActivityRating(String owner, String repo);

    //СРЕДНЕЕ ВРЕМЯ ОЦЕНКИ PULL REQUEST
    long getAverageTimeEstimationPR(String owner, String repo);

    //СТАБИЛЬНЫЕ ПАКЕТЫ
    Set<String> getStablePackages(String directory);

    //НЕСТАБИЛЬНЫЕ ПАКЕТЫ
    Set<String> getUnstablePackages(String directory);

    //ПАКЕТЫ, СКЛОННЫЕ К ПОЛОМКЕ(высокая афферентная связь)
    Set<String> getPackagesProneToBreakage(String directory);

    //ВАЖНЫЕ ПАКЕТЫ КОДОВОЙ БАЗЫ (высокая афферентная связь)
    Set<String> getImportantPackages(String directory);

    //СБАЛАНСИРОВАННЫЕ ПАКЕТЫ (с точки зрения стабильности и абстрактности)
    Set<String> getBalancedPackages(String directory);

    //АТМОСФЕРА В КОМАНДЕ
    String getTeamAtmosphere(String owner, String repo) throws IOException;

    //ТЕКУЧЕСТЬ РАЗРАБОТЧИКОВ
    String getDeveloperTurnoverPerYear(String owner, String repo);

    //РАЗРАБОТЧИКИ, КОТОРЫЕ НЕ ПОКАЗЫВАЮТ РЕЗУЛЬТАТ
    List<DeveloperProductivityDto> getDevelopersPerformPoorly(String owner, String repo);

    CharacteristicsDto calculateCharacteristicsForRepo(String repo, String owner) throws IOException;

    //РАЗРАБОТЧИКИ, КОТОРЫЕ ПЕРЕРАБАТЫВАЮТ
    List<DeveloperProductivityDto> getDevelopersRecycles(String owner, String repo);

    //получение всех характеристик
    CharacteristicsDto getAllCharacteristics(String repo, String owner);
}
