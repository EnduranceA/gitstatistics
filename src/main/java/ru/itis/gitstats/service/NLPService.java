package ru.itis.gitstats.service;

import java.util.List;
import java.util.Map;

public interface NLPService {

    List<Map<String,Double>> analyzeRussianText(String text);

    Map<String, Integer> analyzeEnglishText(String text);

}
