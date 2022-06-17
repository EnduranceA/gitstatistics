package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.Committer;

@Repository
public interface CommitterRepository extends JpaRepository<Committer, Long> {
}
