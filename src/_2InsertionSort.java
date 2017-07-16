public class _2InsertionSort {

    private static boolean less(Integer a, Integer b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("");
    }

    public static boolean isSorted(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static void sort(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else
                    break;
            }
        }
    }

    public static void main(String args[]) {
        Integer[] a = { 0, 1, 12, 32, 1, 43, 67, 88, 9, 0, 0, 0, 102, 3, 4, 4, 4, 5, 6, 6, 6, 7, 7, 8, 8, 9, 9, 11,
                88 };
        System.out.println(isSorted(a));
        show(a);
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
