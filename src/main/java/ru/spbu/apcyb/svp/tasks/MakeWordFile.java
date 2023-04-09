package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class MakeWordFile implements Runnable {

  private final int lowBound;
  private final String word;
  private final Integer num;
  private final String dir;

  /**
   * Создаем слову соответствующий файл.
   *
   * @param num - количество слов.
   * @param word - само слово.
   * @param dir директория где создаем файл.
   * @param lowBound - нижняя граница количества вхождений слова для создания файла.
   */
  public MakeWordFile(Integer num, String word, String dir, Integer lowBound) {
    this.num = num;
    this.word = word;
    this.dir = dir;
    this.lowBound = lowBound;
  }

  @Override
  public void run() {
    if (!new File(dir).isDirectory()) {
      throw new IllegalArgumentException("dir не является директорией");
    }
    File out = new File(dir, word + ".txt");
    if (num >= lowBound) {
      try (FileWriter fileWriter = new FileWriter(out)) {
        fileWriter.write(String.join(" ", Collections.nCopies(num, word)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
