package ru.itis.gitstats.service;

import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.dto.github.UserDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.models.Characteristics;
import ru.itis.gitstats.models.Repo;
import ru.itis.gitstats.models.User;

import java.util.List;
import java.util.Optional;

public interface StorageService {
    Optional<List<Repo>> findReposByUsername(String username);

    Optional<User> findUserByUserName(String username);

    Optional<Repo> findRepoByOwnerAndName(String owner, String repo);

    void updateReposByUsername(String username, List<RepoDto> fromApi);

    User saveToStorage(UserDto fromApi);

    Repo saveRepoByUsername(String username, RepoDto fromApi);

    Optional<List<User>> findRepoContributors(String owner, String repo);

    List<User> saveRepoContributors(String owner, String repo, List<UserDto> fromApi);

    Optional<Characteristics> findCharacteristicsByRepo(String owner, String repo);

    Characteristics saveCharacteristicsByRepo(String repo, String owner, CharacteristicsDto characteristicsDto);

    void updateRepo(Repo repo);

    void updateUser(User user);

    void updateCharacteristics(Characteristics characteristics);
}
