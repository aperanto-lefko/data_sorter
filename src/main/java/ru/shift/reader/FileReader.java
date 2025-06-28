package ru.shift.reader;

import lombok.extern.slf4j.Slf4j;
import ru.shift.classifier.DataClassifier;
import ru.shift.printer.StatisticPrinter;
import ru.shift.enums.DataType;
import ru.shift.writer.FileWriter;

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
                        DataType type = DataClassifier.getType(text);
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
