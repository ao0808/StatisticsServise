package ru.sbrf.statisticsServise.implementation.report;

import ru.sbrf.statisticsServise.model.MetricModel;

import java.util.ArrayList;
import java.util.List;

public class ReportHolder {
    private static ThreadLocal<ReportHolder> holder = ThreadLocal.withInitial(ReportHolder::new);

    private List<MetricModel> records = new ArrayList<>();

    public void add(MetricModel row) {
        records.add(row);
    }

    public List<MetricModel> getRecords() {
        return new ArrayList<>(records);
    }

    public static void start() {
        holder.set(new ReportHolder());
    }

    public static ReportHolder get() {
        return holder.get();
    }

    public static ReportHolder flush() {
        try {
            return holder.get();
        } finally {
            holder.remove();
        }
    }
}
