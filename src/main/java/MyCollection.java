import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyCollection<E> implements Collection<E> {

    private int size;

    private Object[] elementData = new Object[10];

    /**
     * Добавление элемента в конец коллекции.
     *
     * @param e добавляемый элемент
     * @return true
     */
    @Override
    public boolean add(final E e) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (size * 1.5f));
        }
        elementData[size++] = e;
        return true;
    }

    /**
     * @return количество элементов в коллекции
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return true - если коллекция пуста, false - если есть хоть один элемент
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * @return Итератор текущей коллекции.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>();
    }

    /**
     * Проверяет, что переданный объект содержится в коллекции (сравнение с помощью метода equals).
     *
     * @param o Искомый объект.
     * @return Если искомый объект найден возвращает true, если нет - false.
     */
    @Override
    public boolean contains(final Object o) {
        for (E e : this) {
            if (e == null || o == null) {
                if (e == o) {
                    return true;
                }
            } else {
                if (e.equals(o)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @return элементы коллекции в виде массива Object.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.elementData, this.size);
    }

    /**
     * Возвращает объекты коллекции в виде массив указанного классса.
     *
     * @param a   Массивв, в который записываются элементы коллекции.
     *            Если переданный массив уже заполнен, то значения перепишутся.
     *            Если размер переданного массива меньше размера коллекции,
     *            то метод создает новый массив с типом элементов T и размером равным размеру коллекции,
     *            заполняет его элементами коллекции и возвращает.
     *            Если размер массива больше размера коллекции, его первые элементы заменяются элементами коллекции,
     *            и метод возвращает этот массив.
     * @param <T> Тип переданного массива.
     * @return Массив заданного типа из элементов коллекции.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size) {
            return (T[]) Arrays.copyOf(this.elementData, this.size);

        } else {
            if (a.length >= this.size) {
                for (int i = 0; i < this.size; i++) {
                    a[i] = (T) Arrays.copyOfRange(this.elementData, i, i + 1)[0];
                }
            }
        }
        return a;
    }

    /**
     * Удаляет первый элемент равным переданному объекту (сравнение с помощью метода equals).
     *
     * @param o Удаляемый объект
     * @return Если переданный объект найден и удален возвращает true, если нет - false.
     */
    @Override
    public boolean remove(final Object o) {
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();

            if (element == null || o == null) {
                if (element == o) {
                    iterator.remove();
                    return true;
                }
            } else {
                if (element.equals(o)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяет, что все элементы переданной коллекции содержатся в коллекции (сравнение с помощью метода equals).
     *
     * @param c Коллекция искомых элементов.
     * @return Если все элементы найдены возвращает true, если нет false.
     */
    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object e : c) {
            if (!this.contains(e)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Добавляет все элементы переданной коллекции в текущую коллекцию.
     *
     * @param c Коллекция добавляемых элементов.
     * @return Если все элементы добавились вернуть true. В нашем случае всегда true.
     */
    @Override
    public boolean addAll(final Collection<? extends E> c) {
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    /**
     * Удаляет все элементы переданной коллекции из текущей коллекции (сравнение с помощью метода equals).
     *
     * @param c Коллекция удаляемых элементов.
     * @return Если хотя бы один объект найден и удален возвращает true, если нет - false.
     */
    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean wasDeletion = false;

        for (Object deletedElement : c) {
            Iterator<E> thisIterator = this.iterator();

            while (this.remove(deletedElement)) {
                wasDeletion = true;
            }
        }

        return wasDeletion;
    }

    /**
     * Оставляет в текущей коллекции только элементы переданной коллекции.
     *
     * @param c Коллекция оставляемых элементов.
     * @return Если коллекция осталось неизменной вернёт false, иначе - true.
     */
    @Override
    public boolean retainAll(final Collection<?> c) {
        boolean wasDeleting = false;
        Iterator<E> iterator = this.iterator();

        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                wasDeleting = true;
            }
        }
        return wasDeleting;
    }

    /**
     * Очищает коллекцию.
     */
    @Override
    public void clear() {
        Iterator<E> iterator = this.iterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    /**
     * Вложенный класс, описывающий итератор текущей коллекции.
     *
     * @param <T> Класс, которым типизирована данная коллекция.
     */
    private class MyIterator<T> implements Iterator<T> {

        private int cursor = 0;

        private boolean wasNext = false;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        /**
         * Перевод курсора на одну позицию "вправо".
         *
         * @return элемент справа от курсора (до перевода)
         */
        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }

            this.wasNext = true;
            return (T) elementData[cursor++];
        }

        /**
         * Удаление объекта, последний раз полученного методом next().
         */
        @Override
        public void remove() {
            if (!wasNext) {
                throw new IllegalStateException();
            }

            int count = cursor;
            while (count < size()) {
                elementData[count - 1] = elementData[count];
                count++;
            }
            this.wasNext = false;
            elementData[count] = null;
            this.cursor--;
            size--;
        }
    }
}