
public class _4MergeSort {
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
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // Copy contents to auxiliary array
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];

        int j = lo, k = mid + 1;
        for (int i = lo; i <= hi; i++) {
            if (j > mid)
                a[i] = aux[k++];
            else if (k > hi)
                a[i] = aux[j++];
            else if (less(aux[j], aux[k]))
                a[i] = aux[j++];
            else
                a[i] = aux[k++];
        }
    }

    public static void main(String args[]) {

        Integer[] a = { 1, 3, 1, 6, 5, 8, 4, 6, 9, 4, 4, 7, 8, 9, 0, 7, 6, 11 };
        System.out.println(isSorted(a));
        show(a);
        sort(a);
        System.out.println(isSorted(a));
        show(a);

    }
}
