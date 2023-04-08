package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Задание 3.
 */
public class Task3 {


  /**
   * Рекурсивный обход файлового дерева.
   */

  public static String findPaths(File current) {
    StringBuilder tree = new StringBuilder();
    if (current.isDirectory()) {
      tree.append(current.getPath());
      for (int i = 0; i < current.listFiles().length; i++) {
        tree.append("\n").append(findPaths(current.listFiles()[i]));
      }
    } else {
      return current.getPath();
    }
    return tree.toString();
  }

  /**
   * Запуск программы.
   */
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length >= 2) {
      File file = new File(args[0]);
      if (file.exists()) {
        try (FileWriter writer = new FileWriter(args[1], false)) {
          writer.write(findPaths(file));
          writer.flush();
        } catch (IOException exception) {
          throw new IOException(exception.getMessage());
        }
      } else {
        throw new FileNotFoundException("Дирректории обхода не существует");
      }
    } else {
      throw new IllegalArgumentException("Недостаточно аргументов");
    }

  }

}
