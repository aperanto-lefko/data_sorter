package ru.shift.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class StatisticPrinter {
    List<Long> longs = new ArrayList<>();
    List<Double> floats = new ArrayList<>();
    List<String> strings = new ArrayList<>();

    public void addLine(DataType type, String text) {
        switch (type) {
            case INTEGER -> longs.add(Long.parseLong(text));
            case FLOAT -> floats.add(Double.parseDouble(text));
            case STRING -> strings.add(text);
        }
    }

    public void print(StatsStatus status) {
        if (status == StatsStatus.NONE) {
            return;
        }

        if (!longs.isEmpty()) {
            log.info("Целые числа: {}", longs.size());

            if (status == StatsStatus.FULL) {
                log.info(
                        "Мин: {}, макс: {}, сумма {}, среднее значение {}",
                        Collections.min(longs),
                        Collections.max(longs),
                        longs.stream().mapToLong(Long::longValue).sum(),
                        longs.stream().mapToLong(Long::longValue).average().orElse(0)
                );

            }
        }
        if (!floats.isEmpty()) {
            log.info("Вещественные числа {} ", floats.size());

            if (status == StatsStatus.FULL) {
                log.info(
                        "Мин: {}, макс: {}, сумма {}, среднее значение {}",
                        Collections.min(floats),
                        Collections.max(floats),
                        floats.stream().mapToDouble(Double::doubleValue).sum(),
                        floats.stream().mapToDouble(Double::doubleValue).average().orElse(0)
                );
            }
        }
        if (!strings.isEmpty()) {
            log.info("Строки {}", strings.size());

            if (status == StatsStatus.FULL) {
                int min = strings.stream()
                        .mapToInt(String::length)
                        .min()
                        .orElse(0);
                int max = strings.stream()
                        .mapToInt(String::length)
                        .max()
                        .orElse(0);

                log.info("Длина самой короткой строки {}, длина самой длинной строки {}", min, max);
            }
        }
    }

}
