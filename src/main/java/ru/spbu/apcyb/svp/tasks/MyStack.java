package ru.spbu.apcyb.svp.tasks;

import java.util.Stack;

/**
 * Стек на основе MyList.
 */
public class MyStack extends Stack {

  private final MyList stack;

  public MyStack() {
    stack = new MyList();
  }

  @Override
  public Object push(Object e) {
    stack.add(0, e);
    return e;
  }

  @Override
  public synchronized Object pop() {
    Object tmp = stack.get(0);
    stack.remove(0);
    return tmp;
  }

  @Override
  public synchronized Object peek() {
    return stack.get(0);
  }

  @Override
  public synchronized boolean  isEmpty() {
    return stack.isEmpty();
  }


}


