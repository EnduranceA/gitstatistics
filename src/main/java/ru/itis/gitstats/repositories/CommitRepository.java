package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.Commit;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
}
