package ru.itis.gitstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.gitstats.service.CharacteristicService;
import ru.itis.gitstats.service.GitHubService;

@Controller
public class PageController {

    @Autowired
    private GitHubService gitHubService;

    @Autowired
    private CharacteristicService characteristicService;

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/repo")
    public String getRepoPage(Model model, @RequestParam("owner") String owner, @RequestParam("repo") String repo) {
        model.addAttribute("repo", gitHubService.findRepo(owner, repo));
        model.addAttribute("owner", owner);
        model.addAttribute("repo_name", repo);
        return "repository";
    }

    @GetMapping("/repo/activity")
    public String getRepoActivityPage(Model model, @RequestParam("owner") String owner, @RequestParam("repo") String repo) {
        model.addAttribute("characteristics", characteristicService.getAllCharacteristics(repo, owner));
        return "repo_contributor_activity";
    }

}
