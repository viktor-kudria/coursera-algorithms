/* *****************************************************************************
 *  Name: Deque
 *  Date: 11-05-20
 *  Description: Deque
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] s;
    private int size = 2;
    private int numberOfElements = 0;
    private int head = -1;
    private int tail = -1;

    public Deque() {
        s = (Item[]) new Object[size];
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public int size() {
        return numberOfElements;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (head == -1) {
            head = 0;
            tail = 0;
        } else {
            if (isFull()) {
                resize(size * 2);
            }

            if (head == 0) {
                head = size - 1;
            } else {
                head--;
            }
        }

        s[head] = item;

        numberOfElements++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (tail == -1) {
            tail = 0;
            head = 0;
        } else {
            if (isFull()) {
                resize(size * 2);
            }

            if (tail == size - 1) {
                tail = 0;
            } else {
                tail++;
            }
        }

        s[tail] = item;

        numberOfElements++;
    }

    public Item removeFirst() {
        if (head == -1) throw new java.util.NoSuchElementException();

        Item item = s[head];

        if (head == tail) {
            head = -1;
            tail = -1;
        } else if (head == size - 1) {
            head = 0;
        } else {
            head++;
        }

        numberOfElements--;
        if (head != -1 && numberOfElements <= size / 4) {
            resize(size / 2);
        }

        return item;
    }

    public Item removeLast() {
        if (head == -1) throw new java.util.NoSuchElementException();

        Item item = s[tail];

        if (tail == head) {
            head = -1;
            tail = -1;
        } else if (tail == 0) {
            tail = size - 1;
        }   else {
          tail--;
        }

        numberOfElements--;
        if (head != -1 && numberOfElements <= size / 4) {
            resize(size / 2);
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        int i = 0;
        Iterator<Item> iterator = this.iterator();

        while (iterator.hasNext()) {
            copy[i++] = iterator.next();
        }

        head = 0;
        tail = numberOfElements - 1;

        s = copy;
        size = newSize;
    }

    private Boolean isFull() {
        return ((head == 0 && tail == size - 1) || head == tail + 1);
    }

    private class DequeIterator implements Iterator<Item> {
        private int i = -1;

        public boolean hasNext() {
            return i != tail;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            if (i == -1) {
                i = head;
            } else if (i == size - 1) {
                i = 0;
            } else {
                i++;
            }

            return s[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Double> deck = new Deque<Double>();
        for (int i = 0; i < 5; i++) {
            double item;
            if (i % 3 == 0) {
                item = StdRandom.uniform();
                System.out.println("Adding first " + item);
                deck.addFirst(item);
            } else {
                item = StdRandom.uniform();
                System.out.println("Adding last " + item);
                deck.addLast(item);
            }

            if (deck.isFull()) {
                System.out.println("Deck is full. Current size: " + deck.size());
            }
        }

        for (Double aDouble : deck) {
            System.out.println(aDouble);
        }

        while (!deck.isEmpty()) {
            deck.removeLast();
        }

        try {
            deck.removeFirst();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("There is no items in deque. Current size is " + deck.size());
        }
    }
}
