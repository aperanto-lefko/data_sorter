package ru.shift.service;



public class DataClassificator {
    public static DataType getType(String line) {
        if (line == null) {
            return DataType.STRING;
        }
        if (line.matches("-?\\d+")) return DataType.INTEGER;
        if (line.matches("-?(\\d+\\.\\d*|\\.\\d+|\\d+)([eE][-+]?\\d+)?")) return DataType.FLOAT;
        return DataType.STRING;
    }
}
