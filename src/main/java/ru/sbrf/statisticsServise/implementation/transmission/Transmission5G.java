package ru.sbrf.statisticsServise.implementation.transmission;

import lombok.SneakyThrows;
import ru.sbrf.statisticsServise.annotation.Metrics;
import ru.sbrf.statisticsServise.interfase.DataTransmission;

import java.util.concurrent.TimeUnit;

public class Transmission5G implements DataTransmission {
    @SneakyThrows
    @Override
    @Metrics
    public void transmissionUltra() {
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @SneakyThrows
    @Override
    @Metrics
    public void transmissionTurbo() {
        TimeUnit.MILLISECONDS.sleep(150);
    }

    @SneakyThrows
    @Override
    @Metrics
    public void transmissionClassic() {
        TimeUnit.MILLISECONDS.sleep(200);
    }
    public void someMethodWithOutMetricsAnnotation() {}
}
