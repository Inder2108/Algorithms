import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] deque;
    private int size;

    public RandomizedQueue() {
        this.deque = (Item[]) new Object[1];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            if (size == deque.length) {
                Item[] tempItems = (Item[]) new Object[deque.length * 2];
                for (int i = 0; i <= size - 1; i++) {
                    tempItems[i] = deque[i];
                }
                deque = tempItems;
            }
            deque[size] = item;
            size++;
        }
    }

    public Item dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            if (size >= 4 && size <= deque.length / 4) {
                Item[] tempItems = (Item[]) new Object[deque.length / 2];
                for (int i = 0; i <= size - 1; i++) {
                    tempItems[i] = deque[i];
                }
                deque = tempItems;
            }
            Item itemToReturn;
            if (size == 1) {
                itemToReturn = deque[0];
            } else {
                Item[] tempItems = (Item[]) new Object[deque.length];
                int randomPosition = StdRandom.uniform(0, size);
                itemToReturn = deque[randomPosition];
                deque[randomPosition] = null;
                for (int i = 0, j = 0; i <= size - 1; i++) {
                    if (deque[i] == null) {
                        continue;
                    } else {
                        tempItems[j] = deque[i];
                        j++;
                    }
                }
                deque = tempItems;
            }
            size--;
            return itemToReturn;
        }
    }

    public Item sample() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else {
            int randomPosition = StdRandom.uniform(0, size);
            return deque[randomPosition];
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();

    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Item[] tempDeque = deque;
        private int tempSize = size;

        public boolean hasNext() {
            return tempSize != 0;
        }

        public Item next() {
            if (tempSize == 0) {
                throw new java.util.NoSuchElementException();
            }
            int randomPosition = StdRandom.uniform(0, tempSize);
            Item itemToReturn = tempDeque[randomPosition];
            tempDeque[randomPosition] = null;
            Item[] tempItems = (Item[]) new Object[size];
            for (int i = 0, j = 0; i <= tempSize - 1; i++) {
                if (tempDeque[i] == null) {
                    continue;
                } else {
                    tempItems[j] = tempDeque[i];
                    j++;
                }
            }
            tempDeque = tempItems;
            tempSize--;
            return itemToReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        rq.enqueue(470);
        rq.enqueue(324);
        rq.enqueue(230);
        rq.enqueue(131);
        rq.enqueue(555);
        rq.enqueue(999);
        rq.enqueue(397);
        rq.dequeue();

        System.out.println(rq.sample());
    }
}