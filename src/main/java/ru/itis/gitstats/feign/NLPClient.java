package ru.itis.gitstats.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "nlp-client", url = "http://0.0.0.0:3210")
public interface NLPClient {

    @GetMapping("/analyze")
    List<Map<String, Double>> analyze(@RequestParam String text);
}
