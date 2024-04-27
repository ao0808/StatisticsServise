package ru.sbrf.statisticsServise.interfase;

import ru.sbrf.statisticsServise.model.MetricModel;

import java.util.Map;
import java.util.TreeMap;

public interface Report {
    Map<String, MetricModel> create(String location);

    void print(Map<String, MetricModel> map);

    TreeMap<String, MetricModel> sort(Map<String, MetricModel> map);
}
