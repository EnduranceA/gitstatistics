package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.Repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {
    List<Repo> findAllByOwnerLogin(String ownerLogin);

    Optional<Repo> findByOwnerLoginAndName(String ownerLogin, String name);

    List<Repo> findByLastUpdatedBefore(LocalDateTime lastUpdated);
}
