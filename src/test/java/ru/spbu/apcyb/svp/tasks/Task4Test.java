package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 4.
 */
public class Task4Test {

    /**
     * Считает 100 тангенсов однопоточным методом и проверяет значения в каждой строке при помощи
     * Math.tan
     */
    @Test
    void singleThreadTest() throws IOException {
      int numberOfLines = 100;
      try (
          BufferedReader reader = new BufferedReader(new FileReader(
              ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\input.txt"));
          PrintWriter writer = new PrintWriter(
              new FileWriter(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test4Dir\\test.txt"))){
        Task4.singleThreadTan(numberOfLines,reader,writer);
      }

      try (
          BufferedReader reader = new BufferedReader(new FileReader(
              ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\input.txt"));
          BufferedReader res = new BufferedReader(new FileReader(
              ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test4Dir\\test.txt"))){
        for (int i=0;i<numberOfLines;i++){
          assertEquals(Math.tan(Double.parseDouble(reader.readLine())),Double.parseDouble(res.readLine()));
        }
      }
    }

    /**
     * Аналогично предыдущему тесту, только для многопоточного метода.
     */
    @Test
    void multiThreadTest() throws IOException {
      int numberOfLines = 100;
      try (
          BufferedReader reader = new BufferedReader(new FileReader(
              ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\input.txt"));
          PrintWriter writer = new PrintWriter(
              new FileWriter(
                  ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test4Dir\\test.txt"))) {
        Task4.multiThreadTan(numberOfLines,10, reader, writer);
      }

      try (
          BufferedReader reader = new BufferedReader(new FileReader(
              ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\input.txt"));
          BufferedReader res = new BufferedReader(new FileReader(
              ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test4Dir\\test.txt"))) {
        for (int i = 0; i < numberOfLines; i++) {
          assertEquals(Math.tan(Double.parseDouble(reader.readLine())),
              Double.parseDouble(res.readLine()));
        }
      }
    }


  }