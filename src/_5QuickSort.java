
public class _5QuickSort {
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

    }

    private static void partition(Comparable[] a) {
        int pe=0,j = 0, k = a.length - 1;
        while (j < k) {
            if(less(a[j],a[pe])){j++;}
            if(less(a[pe],a[k])){k--;}
            if()
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
