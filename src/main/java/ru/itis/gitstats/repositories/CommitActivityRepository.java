package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.CommitActivity;

@Repository
public interface CommitActivityRepository extends JpaRepository<CommitActivity, Long> {
}
