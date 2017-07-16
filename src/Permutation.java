import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> deque = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            deque.enqueue(temp);
        }
        for (int i = 0; i < k - 1; i++) {
            StdOut.println(deque.dequeue());
        }
    }
}