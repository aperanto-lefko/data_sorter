package ru.shift.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ArgumentParser {
    final List<String> inputFiles = new ArrayList<>();
    String outputDir = ".";
    String prefix = "";
    boolean append = false;
    StatsStatus status = StatsStatus.NONE;

    public ArgumentParser(String[] args) {
        if (args.length == 0) throw new IllegalArgumentException("Не указаны входные данные");
        log.info("Парсинг строки запуска {}", Arrays.toString(args));
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-o" -> {
                        if (i + 1 >= args.length) {
                            throw new IllegalArgumentException("Не указан путь для опции -o");
                        }
                        outputDir = args[++i];
                    }

                    case "-p" -> {
                        if (i + 1 >= args.length) {
                            throw new IllegalArgumentException("Не указан путь для опции -o");
                        }
                        prefix = args[++i];
                    }
                    case "-a" -> append = true;
                    case "-s" -> status=StatsStatus.SHORT;
                    case "-f" -> status=StatsStatus.FULL;
                    default -> {
                        if (args[i].startsWith("-")) {
                            log.warn("Неизвестная опция: {}", args[i]);
                            throw new IllegalArgumentException("Неизвестная опция: " + args[i]);
                        }
                        inputFiles.add(args[i]);
                    }
                }
            }
        } catch (Exception e) {
            log.info("Ошибка парсинга аргументов", e);
            throw e;
        }
        if (inputFiles.isEmpty()) throw new IllegalArgumentException("Не указаны входные данные");
    }


}
