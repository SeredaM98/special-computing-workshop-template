package ru.spbu.apcyb.svp.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Тесты для задания 2.
 */
public class Task2Test {

  @Test
  void pushPeekTest() {
    MyStack testStack = new MyStack();
    testStack.push(5);
    Object actual = testStack.peek();
    Assertions.assertEquals(5, actual);
  }

  @Test
  void popTest() {
    MyStack testStack = new MyStack();
    testStack.push(3);
    testStack.push(2);
    testStack.push(1);
    Assertions.assertEquals(1, testStack.pop());
    Assertions.assertEquals(2, testStack.pop());
    Assertions.assertEquals(3, testStack.pop());
  }

  @Test
  void isEmptyTest() {
    MyStack testStack = new MyStack();
    Assertions.assertTrue(testStack.isEmpty());
  }

  @Test
  void listAdd1Test() {
    MyList testList = new MyList();

    for (int i = 0; i < 11; i++) {
      Assertions.assertTrue(testList.add(i));
    }
    for (int i = 0; i < 11; i++) {
      Assertions.assertEquals(i, testList.get(i));
    }
    Assertions.assertThrows(NullPointerException.class, () -> testList.add(null));

  }

  @Test
  void listAdd2Test() {
    MyList testList = new MyList();
    for (int i = 0; i < 11; i++) {
      testList.add(i, i);
    }
    testList.add(4, -4);
    Assertions.assertEquals(-4, testList.get(4));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.add(49, 19));
    Assertions.assertThrows(NullPointerException.class, () -> testList.add(5, null));
  }

  @Test
  void listRemove1Test() {
    MyList testList = new MyList();
    for (int i = 0; i < 10; i++) {
      testList.add(i);
    }
    Assertions.assertEquals(5, testList.remove(5));
    Assertions.assertEquals(6, testList.get(5));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.remove(20));
  }

  @Test
  void listRemove2Test() {
    MyList testList = new MyList();
    Object removeObj = 5;
    for (int i = 0; i < 10; i++) {
      testList.add(i);
    }
    Assertions.assertTrue(testList.remove(removeObj));
    Assertions.assertEquals(6, testList.get(5));
    removeObj = -4;
    Assertions.assertFalse(testList.remove(removeObj));

  }

  @Test
  void listGetTest() {
    MyList testList = new MyList();
    for (int i = 0; i < 10; i++) {
      testList.add(i);
    }
    Assertions.assertEquals(5, testList.get(5));
    Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testList.get(29));
  }

  @Test
  void listContainsTest() {
    MyList testList = new MyList();
    for (int i = 0; i < 10; i++) {
      testList.add(i);
    }
    Assertions.assertTrue(testList.contains(5));
    Assertions.assertFalse(testList.contains(100));
  }

  @Test
  void listIsEmptyTest() {
    MyList testList = new MyList();
    Assertions.assertTrue(testList.isEmpty());
    testList.add(1);
    Assertions.assertFalse(testList.isEmpty());
  }

  @Test
  void listException() {
    Object ele = 0;
    MyList testList = new MyList();
    int index;
    Object[] a = new Object[10];
    Assertions.assertThrows(UnsupportedOperationException.class, testList::clear);
    Assertions.assertThrows(UnsupportedOperationException.class,
        () -> testList.containsAll(testList));
    Assertions.assertThrows(UnsupportedOperationException.class, testList::iterator);
    Assertions.assertThrows(UnsupportedOperationException.class, testList::toArray);
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.toArray(a));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.addAll(testList));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.addAll(1, testList));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.removeAll(testList));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.retainAll(testList));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.set(1, ele));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.lastIndexOf(ele));
    Assertions.assertThrows(UnsupportedOperationException.class, testList::listIterator);
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.listIterator(1));
    Assertions.assertThrows(UnsupportedOperationException.class, () -> testList.subList(1, 3));

  }


}
