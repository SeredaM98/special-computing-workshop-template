package ru.spbu.apcyb.svp.tasks;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 1.
 */
public class Task1Test {

  @Test
  void inputTest() {
    Assertions.assertArrayEquals(new long[] {1,2,3},Task1.get_notes("2  3 1"));
    Assertions.assertThrows(NumberFormatException.class,
        () ->Task1.get_notes("2ts  3 1"));
  }

  @Test
  void exceptionsTest() {
    long[] dummy = {1, 2};
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Task1.find_combinations(-4, dummy));
    long[] emptyDummy = new long[]{};
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Task1.find_combinations(4, emptyDummy));
    dummy[0] = -1;
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> Task1.find_combinations(4, dummy));
  }

  @Test
  void output1Test() {
    long[] testNotes = new long[] {1};
    List<long[]> expectedCombinations = new ArrayList<>();
    expectedCombinations.add(new long[]{4});
    List<long[]> actualCombinations = Task1.find_combinations(4,testNotes);
    Assertions.assertArrayEquals(expectedCombinations.toArray(),actualCombinations.toArray());

  }

  @Test
  void output2Test() {
    long[] testNotes = new long[] {1,2};
    List<long[]> expectedCombinations = new ArrayList<>();
    expectedCombinations.add(new long[]{4,0});
    expectedCombinations.add(new long[]{2,1});
    expectedCombinations.add(new long[]{0,2});
    List<long[]> actualCombinations = Task1.find_combinations(4,testNotes);
    Assertions.assertArrayEquals(expectedCombinations.toArray(),actualCombinations.toArray());

  }


}
