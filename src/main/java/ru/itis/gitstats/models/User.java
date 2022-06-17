package ru.itis.gitstats.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gitHubId;
    private String login;
    private String type;

    private LocalDateTime lastUpdated;

    private String gistsUrl;
    private String reposUrl;
    private String followingUrl;
    private String starredUrl;
    private String followersUrl;
    private String url;
    private String subscriptionsUrl;
    private String receivedEventsUrl;
    private String avatarUrl;
    private String eventsUrl;
    private String htmlUrl;
    private Boolean siteAdmin;
    private String gravatarId;
    private String nodeId;
    private String organizationsUrl;

    @PrePersist
    public void prePersist() {
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}