public class _3ShellSort {

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
        int N = a.length;
        int h = 1;

        // h goes like this till N/3. 1, 4, 13, 40, 121, 364, 1093, ...
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }

    public static void main(String args[]) {
        Comparable[] a = { 1, 3, 1, 6, 5, 8, 4, 6, 9, 4, 4, 7, 8, 9, 0, 7, 6, 11 };
        System.out.println(isSorted(a));
        show(a);
        sort(a);
        System.out.println(isSorted(a));
        show(a);
    }
}
