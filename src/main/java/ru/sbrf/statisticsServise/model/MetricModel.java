package ru.sbrf.statisticsServise.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricModel {
    private int count;
    private int Id;
    private String nameClass;
    private String nameMethod;
    private int numberOfCalls;
    private Double timeInSeconds;

}

