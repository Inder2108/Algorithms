import edu.princeton.cs.algs4.StdRandom;

public class _1SelectionSort {

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int minimumIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[minimumIndex])) {
                    minimumIndex = j;
                }
            }
            exch(a, i, minimumIndex);
        }
    }

    public static void main(String args[]) {
        Comparable[] a = new Comparable[100];
        for (int i = 0; i < 100; i++) {
            a[i] = StdRandom.uniform(999);
        }
        show(a);
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
