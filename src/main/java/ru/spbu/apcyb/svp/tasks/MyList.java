package ru.spbu.apcyb.svp.tasks;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Список на основе массива.
 */
public class MyList implements List<Object> {

  private final int cap;
  private Object[] data;
  private int size;

  public MyList() {
    data = new Object[10];
    size = 0;
    cap = data.length;
  }

  public void resize() {
    int newCap = (cap * 3) / 2 + 1;
    data = Arrays.copyOf(data, newCap);
  }

  /**
   * Добавление в конец.
   */
  @Override
  public boolean add(Object e) throws NullPointerException {
    if (e == null) {
      throw new NullPointerException();
    }
    int i = size;
    if (i == data.length) {
      resize();
    }
    size++;
    data[i] = e;
    return true;
  }

  /**
   * Добавление на конкретную позицию.
   */
  @Override
  public void add(int index, Object e)
      throws IndexOutOfBoundsException, NullPointerException {
    if (index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (e == null) {
      throw new NullPointerException();
    }
    if (size == data.length) {
      resize();
    }

    System.arraycopy(data, index, data, index + 1, size - index);
    data[index] = e;
    size++;

  }

  /**
   * Получение по индексу.
   */
  @Override
  public Object get(int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return data[index];
  }

  @Override
  public int size() {
    return size;
  }

  /**
   * Проверка пустоты.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Проверка наличия.
   */
  @Override
  public boolean contains(Object e) {
    return indexOf(e) != -1;
  }

  /**
   * Удаление по индексу.
   */
  @Override
  public Object remove(int index) throws IndexOutOfBoundsException {
    if (index >= size) {
      throw new IndexOutOfBoundsException();
    }
    Object e = data[index];
    System.arraycopy(data, index + 1, data, index, size - index - 1);
    size--;
    return e;
  }

  @Override
  public boolean remove(Object e) {
    if (contains(e)) {
      remove(indexOf(e));
      return true;
    }
    return false;
  }

  @Override
  public int indexOf(Object e) {
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (e.equals(data[i])) {
        index = i;
      }
    }
    return index;
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
