package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Задание 5.
 */
public class Task5 {

  /**
   * Main.
   * */
  public static void main(String[] args) throws IOException {
    String book = ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\book.txt";
    String outCountFile = ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\wordCounts.txt";
    String outWordsDir = ".\\src\\main\\java\\ru\\spbu\\apcyb\\svp\\tasks\\words";
    writeCounts(outCountFile, book);
    createWordFiles(book, outWordsDir, 50);
  }

  /**
   * Считываем поток и преобразуем его в Map со словами и их количеством.
   */
  public static Map<String, Integer> readFile(String filePath) throws IOException {

    try (Stream<String> stream = Files.lines(Paths.get(filePath),
        Charset.forName("windows-1251"))) {
      Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
      stream
          .flatMap(line -> Stream.of(
              line
                  .toLowerCase()
                  .replaceAll("\\p{Punct}", " ")
                  .split("\\s+")
          )).forEach(word -> {
            if (!word.isEmpty() && !word.equals(" ")) {
              concurrentHashMap.merge(word, 1, (prev, next) -> prev + 1);
            }
          });
      return concurrentHashMap;
    } catch (IOException exception) {
      throw new IOException("Не возможно прочитать файл");
    }

  }

  /**
   * Считает слова и записывает их в файл.
   */
  public static void writeCounts(String writePath, String readPath) throws IOException {
    File writeFile = new File(writePath);
    try (PrintWriter printWriter = new PrintWriter(writeFile)) {
      readFile(readPath).forEach((key, value) -> printWriter.println(key + " " + value));
    } catch (IOException exception) {
      throw new IOException("Не возможно прочитать файл");
    }
  }

  /**
   * Создает файлы со словами в директории.
   */
  public static void createWordFiles(String readPath, String writeDir, int lowBound)
      throws IOException {
    Map<String, Integer> map = readFile(readPath);
    File writeFile = new File(writeDir);
    if (!writeFile.isDirectory()) {
      throw new IllegalArgumentException(
          "Указаный путь для создания файлов не является директорией.");
    }
    ExecutorService executorService = null;
    try {
      executorService = Executors.newFixedThreadPool(10);
      ExecutorService finalExecutorService = executorService;
      map.forEach(
          (key, value) -> CompletableFuture.runAsync(
              new MakeWordFile(value, key, writeDir, lowBound),
              finalExecutorService));
    } finally {
      if (executorService != null) {
        executorService.shutdown();
      }
    }
    try {
      if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
        throw new RuntimeException("Termination timeout");
      }
    } catch (InterruptedException exception) {
      throw new InterruptedIOException("Запись файлов wordFiles была прервана");
    }

  }
}
