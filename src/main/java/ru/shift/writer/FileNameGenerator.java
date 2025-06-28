package ru.shift.writer;

import ru.shift.enums.DataType;

public interface FileNameGenerator {
    String getFileName(DataType type);
}
