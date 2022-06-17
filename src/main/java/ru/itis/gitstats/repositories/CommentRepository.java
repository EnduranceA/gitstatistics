package ru.itis.gitstats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.gitstats.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
