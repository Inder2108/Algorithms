import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    private class Node<Item> {
        private Node<Item> next = null;
        private Node<Item> previous = null;
        private Item item = null;
    };

    public Deque() {
        head = null;
        tail = head;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node<Item> temp = new Node<Item>();
            if (head == null) {
                temp.item = item;
                head = temp;
                tail = temp;
            } else {
                temp.item = item;
                temp.next = head;
                head.previous = temp;
                head = temp;
            }
            size++;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            Node<Item> temp = new Node<Item>();
            if (tail == null) {
                temp.item = item;
                head = temp;
                tail = temp;
            } else {
                temp.item = item;
                temp.previous = tail;
                tail.next = temp;
                tail = temp;
            }
            size++;
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else if (size == 1) {
            Item itemToReturn = head.item;
            tail = null;
            head = null;
            size = 0;
            return itemToReturn;
        } else {
            Item itemToReturn = head.item;
            head = head.next;
            size--;
            return itemToReturn;
        }
    }

    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        } else if (size == 1) {
            Item itemToReturn = tail.item;
            tail = null;
            head = null;
            size = 0;
            return itemToReturn;
        } else {
            Item itemToReturn = tail.item;
            tail = tail.previous;
            size--;
            return itemToReturn;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> tempHead = head;
        private int tempSize = size;

        public boolean hasNext() {
            return tempSize != 0;
        }

        public Item next() {
            if (tempSize == 0) {
                throw new java.util.NoSuchElementException();
            }
            Item item = tempHead.item;
            tempHead = tempHead.next;
            tempSize--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.isEmpty();
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);
        deque.addFirst(10);
        System.out.println(deque.removeLast());
    }

}