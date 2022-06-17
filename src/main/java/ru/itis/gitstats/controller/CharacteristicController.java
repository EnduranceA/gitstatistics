package ru.itis.gitstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.service.CharacteristicService;

@RestController
public class CharacteristicController {

    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/repos/{owner}/{repo}/stats/contributors/characteristics")
    public ResponseEntity<CharacteristicsDto> getCharacteristics(@PathVariable String owner, @PathVariable String repo) {
        return ResponseEntity.ok(characteristicService.getAllCharacteristics(repo, owner));
    }
}
