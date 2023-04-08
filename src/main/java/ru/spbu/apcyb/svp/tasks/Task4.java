package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

/**
 * Задание 4.
 */
public class Task4 {

  static Logger Log = Logger.getLogger(Task4.class.getName());

  /**
   * Многопоточное вычисление тангенсов.
   */

  public static long multiThreadTan(int linesAmount, int threadAmount, BufferedReader reader,
      PrintWriter writer) throws IOException {
    final long startTime = System.currentTimeMillis();

    ExecutorService executorService = null;
    int i = 0;
    try {
      String line = "";
      executorService = Executors.newFixedThreadPool(threadAmount);
      while (i < linesAmount && line != null) {
        Future[] tang = new Future[10];
        int k = 0;
        while (k < 10 && (((line = reader.readLine()) != null) && i + k < linesAmount)) {
          final String num = line;
          tang[k] = executorService.submit(() -> Math.tan(Double.parseDouble(num)));
          k++;

        }
        for (int j = 0; j < k; j++) {
          writer.println(tang[j].get());
        }
        i += k;
      }
    } catch (ExecutionException | InterruptedException exception) {
      Log.severe("Ошибка потока " + Thread.currentThread().getName());
      Thread.currentThread().interrupt();
    } finally {
      executorService.shutdown();
    }

    return System.currentTimeMillis() - startTime;
  }

  /**
   * Однопоточное вычисление тангенсов.
   */
  public static long singleThreadTan(int linesAmount, BufferedReader reader,
      PrintWriter writer) throws IOException {
    final long startTime = System.currentTimeMillis();
    String line;
    int i = 0;
    while (((line = reader.readLine()) != null) && (i < linesAmount)) {
      try {
        writer.println(Math.tan(
            Double.parseDouble(line)));
      } catch (NumberFormatException exception) {
        throw new NumberFormatException(exception.getMessage());
      }
      i++;
    }
    return System.currentTimeMillis() - startTime;
  }

  /**
   * Main Задает количество чисел для обрабоки, создает reader и writer, вызывает методы
   * multiThreadTan, singleThreadTan и выводит время их выполнения в ms.
   */

  public static void main(String[] args) throws IOException {
    int count = 10000;
    try (
        BufferedReader reader = new BufferedReader(new FileReader(
            ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\input.txt"));
        PrintWriter writer = new PrintWriter(
            new FileWriter(".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\output.txt"))) {
      long multiThreadTime = multiThreadTan(count, 10, reader, writer);
      long singleThreadTime = singleThreadTan(count, reader, writer);

      Log.info("Многопоточное время: " + multiThreadTime + "ms");
      Log.info("Однопоточное время: " + singleThreadTime + "ms");

    } catch (IOException exception) {
      throw new IOException(exception.getMessage());
    }
  }
}

