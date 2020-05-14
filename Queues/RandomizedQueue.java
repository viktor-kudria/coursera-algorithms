/* *****************************************************************************
 *  Name: RandomizedQueue
 *  Date: 14-05-2020
 *  Description: RandomizedQueue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int capacity = 4;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size == capacity) {
            resize(capacity * 2);
        }

        s[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        int index = StdRandom.uniform(size);

        Item item = s[index];
        s[index] = s[size - 1];

        size--;

        if (size * 4 <= capacity) {
            resize(capacity / 2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        return s[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueIterator();
    }

    private void resize(int newCapacity) {
        Item[] copy = (Item[])new Object[newCapacity];

        int limit;
        if (newCapacity > s.length) {
            limit = s.length;
        } else {
          limit = newCapacity;
        }

        for (int i = 0; i < limit; i++) {
            copy[i] = s[i];
        }

        s = copy;
        capacity = newCapacity;
    }

    private class RandomQueIterator implements Iterator<Item> {
        private int[] indecies = new int[size()];
        private int n = 0;

        public RandomQueIterator() {
            for (int i = 0; i < size(); i++) {
                indecies[i] = i;
            }

            for (int i = indecies.length - 1; i > 0; i--) {
                swap(StdRandom.uniform(i + 1), i);
            }
        }

        public boolean hasNext() {
            return n < indecies.length - 1;
        }

        public Item next() {
            return s[indecies[n++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void swap(int a, int b) {
            int tmp = indecies[a];

            indecies[a] = indecies[b];
            indecies[b] = tmp;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
        }

        System.out.println("Size: " + queue.size());
        System.out.println("Just a random number: " + queue.sample());

        System.out.println("Iterator #1:");
        for (int item : queue) {
            System.out.println(item);
        }

        System.out.println("Iterator #2:");
        for (int item : queue) {
            System.out.println(item);
        }

        System.out.println("Dequeing:");
        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
