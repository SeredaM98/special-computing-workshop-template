package ru.spbu.apcyb.svp.tasks;


import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Задание 1.
 */
public class Task1 {

  static Logger Log = Logger.getLogger(Task1.class.getName());

  /**
   * Запуск программы.
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    long amount;
    try {
      amount = in.nextLong();
    } catch (java.util.InputMismatchException e) {
      throw new RuntimeException("Неверный ввод суммы размена.");
    }
    String str = in.nextLine();
    long[] notes = getNotes(str);
    List<long[]> combinations;
    combinations = findCombinations(amount, notes);
    StringBuilder res = new StringBuilder(("Комбинации:\n"));
    Log.info("Количество кобинаций: " + combinations.size());
    for (long[] combination : combinations) {
      res.append(combToString(notes, combination)).append("\n");
    }
    Log.info(res.toString());
  }

  /**
   * Преобразование строки в отсортированный набор купюр.
   */
  public static long[] getNotes(String str) {
    while (str.contains("  ")) {
      str = str.replace("  ", " ");
    }
    String[] dt = str.trim().split(" ");
    Set<Long> notes = new HashSet<>();

    for (String s : dt) {
      try {
        notes.add(Long.parseLong(s));

      } catch (NumberFormatException e) {
        throw new NumberFormatException("Неверный ввод купюр.");
      }
    }
    return Longs.toArray(notes);
  }

  /**
   * Сумма для текущей комбинации купюр.
   */
  private static long sum(long[] notes, long[] currcomb) {
    long sum = 0;
    for (int i = 0; i < notes.length; i++) {
      sum += notes[i] * currcomb[i];
    }
    return sum;
  }

  /**
   * Формирование строки для комбинации.
   */
  private static String combToString(long[] notes, long[] currcomb) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < notes.length; i++) {
      output.append(currcomb[i]).append("[").append(notes[i]).append("] ");
    }
    return output.toString();

  }

  /**
   * Поиск комбинаций.
   */
  public static List<long[]> findCombinations(long amount, long[] notes) {
    List<long[]> combinations = new ArrayList<>();
    long[] currComb = new long[notes.length];
    int index = 0;
    if (notes.length == 0) {
      throw new IllegalArgumentException("Требуется хотя бы один номинал купюры");
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Сумма размена должна быть больше нуля");
    }
    if (Arrays.stream(notes).min().getAsLong() <= 0) {
      throw new IllegalArgumentException("Все купюры должны быть больше нуля");
    }
    while (true) {

      currComb[index] = (amount - sum(notes, currComb)) / notes[index];
      if (sum(notes, currComb) < amount) {
        currComb[index]++;
      }
      if ((amount - sum(notes, currComb)) % notes[index] == 0) {

        Log.info("Найдена комбинация: "
            + combToString(notes, currComb)
            + "\nКобинаций найдено: "
            + (combinations.size() + 1) + "\n");
        combinations.add(currComb.clone());
      }

      if (index == notes.length - 1) {
        return combinations;
      } else {
        for (int i = index; sum(notes, currComb) >= amount; i++) {
          currComb[i] = 0;
          if (i != notes.length - 1) {
            currComb[i + 1]++;
          } else {
            index++;
          }
        }
      }
    }
  }
}
