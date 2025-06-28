package ru.shift.service;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileReader {

    public void readFile(List<String> files, FileWriter writer, StatisticPrinter printer) {
        List<BufferedReader> readers = new ArrayList<>();
        try {
            for (String file : files) {
                readers.add(Files.newBufferedReader(Paths.get(file)));
            }
            boolean reading = true;
            while (reading) {
                reading = false;
                for (BufferedReader reader : readers) {
                    String text = reader.readLine();
                    if (text != null) {
                        reading = true;
                        DataType type = DataClassificator.getType(text);
                        writer.write(type, text);
                        printer.addLine(type, text);
                    }
                }
            }

        } catch (IOException e) {
            log.error("Ошибка чтения файла {} ", e.getMessage(), e);
        } finally {
            for (BufferedReader reader : readers) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("Ошибка закрытия файла", e);
                }
            }
        }
    }
}
