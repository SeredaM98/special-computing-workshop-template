package ru.spbu.apcyb.svp.tasks;


import com.google.common.primitives.Longs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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
    long[] notes = get_notes(str);
    List<long[]> combinations;
    combinations = find_combinations(amount, notes);
    StringBuilder res = new StringBuilder(("Комбинации:\n"));
    Log.info("Количество кобинаций: " + combinations.size());
    for (long[] combination : combinations) {
      res.append(comb_to_string(notes, combination)).append("\n");
    }
    Log.info(res.toString());
  }

  /**
   * Преобразование строки в отсортированный набор купюр.
   */
  public static long[] get_notes(String str) {
    while (str.contains("  ")) {
      str = str.replace("  ", " ");
    }
    String[] dt = str.trim().split(" ");
    long[] notes = new long[dt.length];

    for (int i = 0; i < dt.length; i++) {
      try {
        notes[i] = Long.parseLong(dt[i]);

      } catch (NumberFormatException e) {
        throw new NumberFormatException("Неверный ввод купюр.");
      }
    }
    Arrays.sort(notes);
    ArrayList<Long> newnotes = new ArrayList<>();
    for (long note : notes) {
      if (Arrays.binarySearch(newnotes.toArray(), note) < 0) {
        newnotes.add(note);
      }
    }
    return Longs.toArray(newnotes);
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
  private static String comb_to_string(long[] notes, long[] currcomb) {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < notes.length; i++) {
      output.append(currcomb[i]).append("[").append(notes[i]).append("] ");
    }
    return output.toString();

  }

  /**
   * Поиск комбинаций.
   */
  public static List<long[]> find_combinations(long amount, long[] notes) {
    List<long[]> combinations = new ArrayList<>();
    long[] currcomb = new long[notes.length];
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

      currcomb[index] = (amount - sum(notes, currcomb)) / notes[index];
      if (sum(notes, currcomb) < amount) {
        currcomb[index]++;
      }
      if ((amount - sum(notes, currcomb)) % notes[index] == 0) {

        Log.info("Найдена комбинация: "
            + comb_to_string(notes, currcomb)
            + "\nКобинаций найдено: "
            + (combinations.size() + 1) + "\n");
        combinations.add(currcomb.clone());
      }

      if (index == notes.length - 1) {
        return combinations;
      } else {
        for (int i = index; sum(notes, currcomb) >= amount; i++) {
          currcomb[i] = 0;
          if (i != notes.length - 1) {
            currcomb[i + 1]++;
          } else {
            index++;
          }
        }
      }
    }
  }
}
