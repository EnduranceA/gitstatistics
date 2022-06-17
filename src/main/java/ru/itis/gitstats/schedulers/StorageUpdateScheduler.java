package ru.itis.gitstats.schedulers;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.itis.gitstats.repositories.CharacteristicsRepository;
import ru.itis.gitstats.repositories.RepoRepository;
import ru.itis.gitstats.repositories.UserRepository;
import ru.itis.gitstats.service.StorageService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class StorageUpdateScheduler {

    private static final Integer HOURS_TO_UPDATE = 1;

    private final RepoRepository repoRepository;
    private final UserRepository userRepository;
    private final CharacteristicsRepository characteristicsRepository;

    private final StorageService storageService;

    @Scheduled(cron = "0 0 * * * ?")
    public void checkForUpdate() {
        LocalDateTime beforeDate = LocalDateTime.now().minusHours(HOURS_TO_UPDATE);
        repoRepository.findByLastUpdatedBefore(beforeDate).forEach(storageService::updateRepo);
        userRepository.findByLastUpdatedBefore(beforeDate).forEach(storageService::updateUser);
        characteristicsRepository.findByLastUpdatedBefore(beforeDate).forEach(storageService::updateCharacteristics);
    }

}
