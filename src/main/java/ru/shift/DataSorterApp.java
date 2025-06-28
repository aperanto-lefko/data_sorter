package ru.shift;

import lombok.extern.slf4j.Slf4j;
import ru.shift.parser.ArgumentParser;
import ru.shift.printer.StatisticPrinter;
import ru.shift.reader.FileReader;
import ru.shift.service.DataSorterService;
import ru.shift.writer.StandartFileNameGenerator;
import ru.shift.writer.FileWriter;

@Slf4j
public class DataSorterApp {
    public static void main(String[] args) {
        new DataSorterService().run(args);
    }
}