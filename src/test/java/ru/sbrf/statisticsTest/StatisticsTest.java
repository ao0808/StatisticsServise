package ru.sbrf.statisticsTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sbrf.statisticsServise.configurationSpring.ApplicationConfiguration;
import ru.sbrf.statisticsServise.implementation.report.ReportHolder;
import ru.sbrf.statisticsServise.interfase.DataTransmission;
import ru.sbrf.statisticsServise.interfase.Report;
import ru.sbrf.statisticsServise.model.MetricModel;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext
public class StatisticsTest extends Assert {
    @Autowired
    DataTransmission transmission5G;

    @Autowired
    DataTransmission transmission4G;

    @Autowired
    DataTransmission transmission3G;

    @Autowired
    Report reportMyMetrics;

    public void transmission5GRan(){
        for (int i = 0; i < 5; i++) {
            transmission5G.transmissionClassic();
            transmission5G.transmissionTurbo();
            transmission5G.transmissionUltra();
        }
    }

    public void transmission4GRan(){
        for (int i = 0; i < 2; i++) {
            transmission4G.transmissionClassic();
            transmission4G.transmissionTurbo();
            transmission4G.transmissionUltra();
        }
    }

    public void transmission3GRan(){
        transmission3G.transmissionClassic();
        transmission3G.transmissionTurbo();
        transmission3G.transmissionUltra();
    }
    private void runMetrics() {
        transmission3GRan();
        transmission4GRan();
        transmission5GRan();
    }

    @Test
    public void mapReportTest(){
        try {
            ReportHolder.start();
            runMetrics();
        } finally {
            ReportHolder report = ReportHolder.flush();
            List<MetricModel> records = report.getRecords();
        }


        Map<String, MetricModel> map = reportMyMetrics.create("classpath:metrics.log");
        assertFalse(map.isEmpty());
        assertEquals(map.size(), 9);
        assertTrue(map.containsKey("Transmission5GtransmissionUltra"));
        assertTrue(map.containsKey("Transmission3GtransmissionTurbo"));
        assertTrue(map.containsKey("Transmission4GtransmissionClassic"));
        reportMyMetrics.print(map);
    }

    @Test
    public void sortMapReportTest(){
        runMetrics();
        Map<String, MetricModel> map = reportMyMetrics.sort(reportMyMetrics.create("classpath:metrics.log"));
        assertFalse(map.isEmpty());
        assertEquals(map.size(), 9);
        assertTrue(map.containsKey("Transmission5GtransmissionUltra"));
        assertTrue(map.containsKey("Transmission3GtransmissionTurbo"));
        assertTrue(map.containsKey("Transmission4GtransmissionClassic"));
        reportMyMetrics.print(map);
    }
}
