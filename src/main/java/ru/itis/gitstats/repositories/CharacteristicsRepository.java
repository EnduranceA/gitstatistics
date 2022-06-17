package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.Characteristics;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics, Long> {
    Optional<Characteristics> findByRepoOwnerLoginAndRepoName(String repo_owner_login, String repo_name);

    List<Characteristics> findByLastUpdatedBefore(LocalDateTime lastUpdated);
}