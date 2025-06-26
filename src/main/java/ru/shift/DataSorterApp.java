package ru.shift;

import java.io.IOException;

public class DataSorterApp {
    public static void main(String[] args) {
       try {
           ArgumentParser config = new ArgumentParser(args);

       } catch (IllegalArgumentException e) {
           System.err.println("Ошибка аргументов: " + e.getMessage());
//       } catch (IOException e) {
//           System.err.println("Ошибка ввода/вывода: " + e.getMessage());
////       } catch (Exception e) {
//           System.err.println("Неожиданная ошибка: " + e.getMessage());
       }
}
}