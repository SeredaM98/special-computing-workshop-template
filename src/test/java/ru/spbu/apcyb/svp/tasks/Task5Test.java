package ru.spbu.apcyb.svp.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 5.
 */
public class Task5Test {

  private final File testFile = new File(
      ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\testFile.txt");

  @Test
  void readInputFileTest() throws IOException {
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("Hello-?world?world!-hi!");
    testWriter.close();
    Map<String, Integer> exp = new HashMap<>();
    exp.put("hello", 1);
    exp.put("world", 2);
    exp.put("hi", 1);
    Map<String, Integer> testResult = Task5.readFile(
        testFile.getPath());
    Assertions.assertEquals(exp, testResult);
  }

  @Test
  void writeCountsTest() throws IOException {
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("May be yes may be now may me rain may be now");
    testWriter.close();
    Task5.writeCounts(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\actual.txt",
        ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\testFile.txt");
    File exp = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\expected.txt");
    File act = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\actual.txt");
    Assertions.assertTrue(FileUtils.contentEquals(act, exp));
  }

  @Test
  void createWordFilesTest() throws IOException, InterruptedException {
    PrintWriter testWriter = new PrintWriter(testFile);
    testWriter.write("May be yes may be no may be rain may be snow");
    testWriter.close();
    Task5.createWordFiles(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\testFile.txt",
        ".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\TestWordDir", 0);
    File act = new File(".\\src\\test\\java\\ru\\spbu\\apcyb\\svp\\tasks\\Test5Dir\\TestWordDir");
    File[] files = act.listFiles();
    Map<String,Integer> exp = new HashMap<>();
    exp.put("be.txt",4);
    exp.put("may.txt",4);
    exp.put("snow.txt",1);
    exp.put("no.txt",1);
    exp.put("rain.txt",1);
    exp.put("yes.txt",1);
    for (File file : files) {
      Assertions.assertTrue(exp.containsKey(file.getName()));
    }
    for (File file : files) {
      try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
        Assertions.assertEquals(reader.readLine(), String.join(" ",
            Collections.nCopies(exp.get(file.getName()), file.getName().replace(".txt", ""))));
      }
      }

  }

}
