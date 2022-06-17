package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.PullRequest;

@Repository
public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
}
