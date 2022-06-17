package ru.itis.gitstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.gitstats.dto.github.GitHubWebhookForm;
import ru.itis.gitstats.service.GitHubService;

@RestController
@RequestMapping("/webhooks/github")
public class GitHubWebhookController {

    @Autowired
    private GitHubService gitHubService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void handleWebhook(@RequestBody GitHubWebhookForm form) {
        gitHubService.handleWebhook(form);
    }

}
