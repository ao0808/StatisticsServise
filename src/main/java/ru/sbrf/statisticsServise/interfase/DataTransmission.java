package ru.sbrf.statisticsServise.interfase;

import ru.sbrf.statisticsServise.annotation.Metrics;

public interface DataTransmission {
    @Metrics
    void transmissionUltra();
    @Metrics
    void transmissionTurbo();
    @Metrics
    void transmissionClassic();
}
