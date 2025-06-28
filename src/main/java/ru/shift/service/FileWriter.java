package ru.shift.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class FileWriter {
    Map<DataType, BufferedWriter> writers = new HashMap<>();
    ArgumentParser arguments;

    public FileWriter (ArgumentParser arguments) {
        this.arguments = arguments;
    }

    public void write (DataType type, String text) throws IOException {
        BufferedWriter writer = writers.get(type);
        if (writer == null) {
            String fileName = switch(type) {
                case INTEGER -> "integers.txt";
                case FLOAT -> "floats.txt";
                case STRING -> "strings.txt";
            };
            Path path = Paths.get(arguments.getOutputDir(), arguments.getPrefix()+fileName);
            writer = Files.newBufferedWriter(
                    path,
                    arguments.isAppend() ? new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND}
                            : new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING}
            );
            writers.put(type, writer);

        }
        writer.write(text);
        writer.newLine();
    }

    public void closeWriters() {
        for(BufferedWriter writer: writers.values()) {
            try {
                writer.close();
            } catch (IOException e) {
                log.error("Ошибка закрытия файла");
            }
        }
    }
}
