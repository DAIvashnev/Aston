package ru.ivashnev.aston.intensive_61.task1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация ArrayList на основе предоставленных методов интерфейсом IntensiveList.
 * @author Дмитрий Ивашнев
 */
public class ArrayList_DmitryIvashnev<E> implements IntensiveList<E> {
    private E[] list;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size = 0;
    private boolean checkSort = false;

    /**
     * Конструктор по умолчанию, с default capacity = 10;
     */
    public ArrayList_DmitryIvashnev() {
        capacity = DEFAULT_CAPACITY;
        list = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с параметром capacity
     * @param capacity - default значение capacity заменится на значение введенное пользователем.
     * @throws IllegalArgumentException - выбросит исключение, в случае если capacity < 0.
     */
    public ArrayList_DmitryIvashnev(int capacity) throws IllegalArgumentException {
        this.capacity = capacity;
        if (capacity < 0) {
            throw  new IllegalArgumentException("Illegal Capacity: " + capacity);
        } else {
            list = (E[]) new Object[capacity];
        }
    }

    /**
     *
     * @return - возвращает колличество элементов в ArrayList
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавление нового элемента в конец списка ArrayList
     * @param element - новый эллемент
     */
    @Override
    public void add(E element) {
        if(size == capacity) {
            expansionArray();
        }
        list[size++] = element;
        checkSort = size == 1;
    }

    /**
     * Добавление нового элемента в список ArrayList в указанную позицию
     * @param index - позиция в которую будет добавлен новый элемент
     * @param element - новый элемент
     * @throws IndexOutOfBoundsException - выбросит исключение, в случае если index находится вне диапозона size
     */
    @Override
    public void add(int index, E element) {
        if(index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if(size == capacity) {
            expansionArray();
        }
        for(int i = size; i > index; i--) {
            list[i] = list[i-1];
        }
        list[index] = element;
        size++;
        checkSort = size == 1;
    }

    /**
     * Получение элемента из ArrayList по индексу
     * @param index - позиция в ArrayList из которой мы должны получить элемент
     * @return - найденный элемент
     * @throws IndexOutOfBoundsException - выбросит исключение, в случае если index находится вне диапозона size
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        return list[index];
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке, на другой указанный элемент
     * @param index - позиция элемента для замены
     * @param element - элемент который будет сохранен в указанной позиции
     * @return - возращает ранее находившийся элемент в указанном положении
     * @throws IndexOutOfBoundsException - выбросит исключение, в случае если index находится вне диапозона size
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        E oldElement = list[index];
        list[index] = element;
        checkSort = size == 1;
        return oldElement;
    }

    /**
     * Удаляет элемент в указанной позиции
     * @param index - позиция удаляемого элемента
     * @return - возвращает элемент, который был удален
     * @throws IndexOutOfBoundsException - выбросит исключение, в случае если index находится вне диапозона size
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        E element = list[index];
        for(int i = index; i < size-1; i++) {
            list[i] = list[i+1];
        }
        size--;
        return element;
    }

    /**
     * Очищает список
     */
    @Override
    public void clear() {
        list = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Сортирует элемент списка по возрастанию
     * @param comparator - условия сортировки
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        sort(list, 0, size-1, comparator);
        checkSort = true;
    }

    /**
     * Возвращает boolean - список отсортирован?
     * @return - true - список отсортирован, false - список не отсортирован.
     */
    @Override
    public boolean isSorted() {
        return checkSort;
    }

    /**
     * Обрезает список до указанного размера
     * @param size - размер, до которого нужно обрезать список
     */
    @Override
    public void split(int size) {
        list = Arrays.copyOf(list, size);
        this.size = size;
    }

    private void expansionArray() {
        capacity = capacity == 0 ? DEFAULT_CAPACITY : capacity * 2;
        list = Arrays.copyOf(list, capacity);
    }

    private void sort(E[] list, int leftIndex, int rightIndex, Comparator<E> comparator) {
        if(size == 0 || leftIndex >= rightIndex) {
            return;
        }
        E pivot = list[(leftIndex + rightIndex) / 2];
        int leftMarkerIndex = leftIndex;
        int rightMarkerIndex = rightIndex;


        while (leftMarkerIndex <= rightMarkerIndex) {
            while (comparator.compare(list[leftMarkerIndex], pivot) < 0) {
                leftMarkerIndex++;
            }
            while (comparator.compare(list[rightMarkerIndex], pivot) > 0) {
                rightMarkerIndex--;
            }
            if(leftMarkerIndex <= rightMarkerIndex) {
                E swap = list[leftMarkerIndex];
                list[leftMarkerIndex] = list[rightMarkerIndex];
                list[rightMarkerIndex] = swap;
                leftMarkerIndex++;
                rightMarkerIndex--;
            }
        }
        if(leftIndex < rightMarkerIndex) {
            sort(list, leftIndex, rightMarkerIndex, comparator);
        }
        if(rightIndex > leftMarkerIndex) {
            sort(list, leftMarkerIndex, rightIndex, comparator);
        }
    }

    /**
     * Представление объекта в текстовом виде
     * @return - возвращает строковое представление объекта
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
}
