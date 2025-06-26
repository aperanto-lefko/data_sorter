package ru.shift;

import lombok.extern.slf4j.Slf4j;
import ru.shift.service.ArgumentParser;
import ru.shift.service.DataClassificator;
import ru.shift.service.DataType;
import ru.shift.service.FileWriter;
import ru.shift.service.StatisticPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class DataSorterApp {
    public static void main(String[] args) {
        try {
            ArgumentParser arguments = new ArgumentParser(args);
            FileWriter writer = new FileWriter(arguments);
            StatisticPrinter printer = new StatisticPrinter();
            for (String file : arguments.getInputFiles()) {
               readFile(Paths.get(file), writer, printer);

            }
            writer.closeWriters();
            printer.print(arguments.isFullStats());
        } catch (IllegalArgumentException e) {
            log.error("Ошибка аргументов: {} ", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Неожиданная ошибка:{} ", e.getMessage(), e);
        }
    }

    private static void readFile(Path path, FileWriter writer, StatisticPrinter printer) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String text;
            while ((text = reader.readLine()) != null) {
                DataType type = DataClassificator.getType(text);
                writer.write(type, text);
                printer.addLine(type, text);
            }
        } catch (IOException e) {
            log.error("Ошибка чтения файла {} : {} ", path, e.getMessage());
        }
    }
}