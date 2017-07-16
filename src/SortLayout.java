public class SortLayout {

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
