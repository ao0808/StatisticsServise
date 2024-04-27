package ru.sbrf.statisticsServise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbrf.statisticsServise.configurationSpring.ApplicationConfiguration;
import ru.sbrf.statisticsServise.interfase.DataTransmission;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        DataTransmission fiveG = context.getBean(DataTransmission.class);
        fiveG.transmissionClassic();
    }
}
