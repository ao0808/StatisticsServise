package ru.sbrf.statisticsServise.configurationSpring;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sbrf.statisticsServise.implementation.report.ReportMyMetrics;
import ru.sbrf.statisticsServise.implementation.transmission.Transmission3G;
import ru.sbrf.statisticsServise.implementation.transmission.Transmission4G;
import ru.sbrf.statisticsServise.implementation.transmission.Transmission5G;
import ru.sbrf.statisticsServise.interfase.DataTransmission;
import ru.sbrf.statisticsServise.interfase.Report;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public DataTransmission transmission5G(){
        return new Transmission5G();
    }

    @Bean
    public DataTransmission transmission4G(){
        return new Transmission4G();
    }

    @Bean
    public DataTransmission transmission3G(){return new Transmission3G();
    }

    @Bean
    public BeanPostProcessor myBeanProxy(){
        return new MyBeanProxy();
    }

    @Bean
    @Scope("prototype")
    public Report report(){
        return new ReportMyMetrics();
    }
}
