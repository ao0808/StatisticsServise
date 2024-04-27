package ru.sbrf.statisticsServise.implementation.transmission;

import lombok.SneakyThrows;
import ru.sbrf.statisticsServise.annotation.Metrics;
import ru.sbrf.statisticsServise.interfase.DataTransmission;

import java.util.concurrent.TimeUnit;

public class Transmission3G implements DataTransmission {
    @SneakyThrows
    @Override
    @Metrics
    public void transmissionUltra() {
        TimeUnit.MILLISECONDS.sleep(500);
    }

    @SneakyThrows
    @Override
    @Metrics
    public void transmissionTurbo() {
        TimeUnit.MILLISECONDS.sleep(750);
    }

    @Override
    @Metrics
    @SneakyThrows
    public void transmissionClassic() {
        TimeUnit.MILLISECONDS.sleep(1000);
    }
    public void someMethodWithOutMetricsAnnotation() {}
}
