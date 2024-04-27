package ru.sbrf.statisticsServise.implementation.transmission;

import lombok.SneakyThrows;
import ru.sbrf.statisticsServise.annotation.Metrics;
import ru.sbrf.statisticsServise.interfase.DataTransmission;

import java.util.concurrent.TimeUnit;

public class Transmission4G implements DataTransmission {
    @SneakyThrows
    @Override
    @Metrics
    public void transmissionUltra() {
        TimeUnit.MILLISECONDS.sleep(250);
    }

    @SneakyThrows
    @Override
    @Metrics
    public void transmissionTurbo() {
        TimeUnit.MILLISECONDS.sleep(300);
    }

    @SneakyThrows
    @Override
    @Metrics
    public void transmissionClassic() {
        TimeUnit.MILLISECONDS.sleep(400);
    }
}
