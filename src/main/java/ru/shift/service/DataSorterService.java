package ru.shift.service;

import lombok.extern.slf4j.Slf4j;
import ru.shift.parser.ArgumentParser;
import ru.shift.printer.StatisticPrinter;
import ru.shift.reader.FileReader;
import ru.shift.writer.FileWriter;
import ru.shift.writer.StandartFileNameGenerator;

@Slf4j
public class DataSorterService {
    public void run(String[] args) {
        try {
            ArgumentParser arguments = new ArgumentParser(args);
            FileWriter writer = new FileWriter(arguments, new StandartFileNameGenerator());
            StatisticPrinter printer = new StatisticPrinter();
            FileReader fileReader = new FileReader();
            fileReader.readFile(arguments.getInputFiles(), writer, printer);
            writer.closeWriters();
            printer.print(arguments.getStatus());
        } catch (IllegalArgumentException e) {
            log.error("Ошибка аргументов: {} ", e.getMessage(), e);
        } catch (Exception e) {
            log.error("Неожиданная ошибка:{} ", e.getMessage(), e);
        }
    }
}

