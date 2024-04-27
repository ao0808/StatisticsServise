package ru.sbrf.statisticsServise.implementation.report;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import ru.sbrf.statisticsServise.interfase.Report;
import ru.sbrf.statisticsServise.model.MetricModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ReportMyMetrics implements Report {

    @SneakyThrows
    @Override
    public Map<String, MetricModel> create(String location) {
        return parsToMap(location);
    }

    private Map<String, MetricModel> parsToMap(String location) throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        String text = IOUtils.toString(loader.getResource(location).getInputStream(), StandardCharsets.UTF_8);
        Map<String, MetricModel> treeWitheMetricsModel = new HashMap<>();
        String[] lines = text.split("\n");
        for (int i = 0; i <  Arrays.stream(text.split("\n")).count(); i++) {
            parsStringToElement(treeWitheMetricsModel, lines[i]);
        }
        return treeWitheMetricsModel;
    }

    private void parsStringToElement(Map<String, MetricModel> treeWitheMetricsModel, String lines) {
        String[] element = lines.split("\\s+");
        if (element[0].equals("ID:")) {
            createNewMetricsData(treeWitheMetricsModel, element);
        }
    }

    private void createNewMetricsData(Map<String, MetricModel> treeWitheMetricsModel, String[] element) {
        MetricModel data = createNewMetricModelData(element);
        String key = data.getNameClass() + data.getNameMethod();
        if(!treeWitheMetricsModel.containsKey(key)){
            treeWitheMetricsModel.put(key, data);
        } else {
            updateMetricsData(treeWitheMetricsModel, data, key);
        }
    }

    private void updateMetricsData(Map<String, MetricModel> treeWitheMetricsModel, MetricModel data, String key) {
        MetricModel dataGet = (MetricModel) treeWitheMetricsModel.get(key);
        dataGet.setTimeInSeconds((dataGet.getTimeInSeconds() + data.getTimeInSeconds())/2.0);
        dataGet.setCount(dataGet.getCount() + 1);
    }

    private MetricModel createNewMetricModelData(String[] element) {
        return MetricModel.builder().Id(Integer.parseInt(element[1])).
                nameMethod(element[5]).nameClass(element[3]).
                timeInSeconds(Double.valueOf(element[7])).count(1).build();
    }

    @Override
    public void print(Map<String, MetricModel> stringObjectMap) {
        Character character = 'N';
        String className = "Class";
        String methodName = "Method";
        String executeTime = "Time, ms";
        String count = "number of method calls";
        System.out.format("%-5s%-15s%-25s%-15s%-15s", character, className, methodName, executeTime, count);
        System.out.println();
        int i = 1;
        for (Map.Entry<String, MetricModel> pair : stringObjectMap.entrySet()) {
            MetricModel data = (MetricModel) pair.getValue();
            System.out.format("%-5s%-15s%-25s%-15s%-15s",
                    i++,
                    data.getNameClass(),
                    data.getNameMethod(),
                    data.getTimeInSeconds(),
                    data.getCount());
            System.out.println();
        }
    }
        @Override
        public TreeMap<String, MetricModel> sort(Map<String, MetricModel> hashMap){
            TreeMap<String, MetricModel> sortedMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    MetricModel m1 = hashMap.get(o1);
                    MetricModel m2 = hashMap.get(o2);
                    double v1 = m1.getTimeInSeconds();
                    double v2 = m2.getTimeInSeconds();
                    return Double.compare(v2, v1);
                }
            });
            sortedMap.putAll(hashMap);
            return sortedMap;
        }
}
