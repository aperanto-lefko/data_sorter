package ru.shift;

import lombok.extern.slf4j.Slf4j;
import ru.shift.service.ArgumentParser;
import ru.shift.service.DataClassificator;
import ru.shift.service.DataType;
import ru.shift.service.FileReader;
import ru.shift.service.FileWriter;
import ru.shift.service.StatisticPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataSorterApp {
    public static void main(String[] args) {
        try {
            ArgumentParser arguments = new ArgumentParser(args);
            FileWriter writer = new FileWriter(arguments);
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