package ru.shift.writer;

import ru.shift.enums.DataType;

public class StandartFileNameGenerator implements FileNameGenerator {
    @Override
    public String getFileName(DataType type) {
        return switch (type) {
            case INTEGER -> "integers.txt";
            case FLOAT -> "floats.txt";
            case STRING -> "strings.txt";
        };
    }
}
