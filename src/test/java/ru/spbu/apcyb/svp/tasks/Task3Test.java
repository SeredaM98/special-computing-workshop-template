package ru.spbu.apcyb.svp.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 3.
 */
public class Task3Test {

  @Test
  void testExceptions() {
    String[] args = {".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir"};
    String[] args2 = {".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\notexist",
        ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir\\actual.txt"};

    Assertions.assertThrows(IllegalArgumentException.class,()->Task3.main(args));
    Assertions.assertThrows(FileNotFoundException.class,()->Task3.main(args2));
  }

  @Test
  void testMain() throws IOException {
    String[] args = {".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir",
        ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir\\actual.txt"};
    Task3.main(args);
    File actual = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir\\actual.txt");
    File expected = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test3Dir\\expected.txt");
    Assertions.assertTrue(FileUtils.contentEquals(actual,expected));
  }


}
