package ru.itis.gitstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.gitstats.feign.NLPClient;
import ru.itis.gitstats.utils.nlp.StanfordNlp;

import java.util.List;
import java.util.Map;

@Component
public class NLPServiceImpl implements NLPService{

    @Autowired
    private NLPClient nlpClient;

    @Autowired
    private StanfordNlp stanfordNlp;

    @Override
    public List<Map<String, Double>> analyzeRussianText(String text) {
        return nlpClient.analyze(text);
    }

    @Override
    public Map<String, Integer> analyzeEnglishText(String text) {
        return stanfordNlp.estimatingSentiment(text);
    }
}
