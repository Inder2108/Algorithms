import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {

    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Selection"))
            _1SelectionSort.sort(a);
        if (alg.equals("Insertion"))
            _2InsertionSort.sort(a);
        if (alg.equals("Shell"))
            _3ShellSort.sort(a);
        if (alg.equals("Merge"))
            _4MergeSort.sort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(timeRandomInput("Selection", 9000, 100));
        System.out.println("______________________________________");
        System.out.println(timeRandomInput("Insertion", 9000, 100));
        System.out.println("______________________________________");
        System.out.println(timeRandomInput("Shell", 9000, 100));
        System.out.println("______________________________________");
        System.out.println(timeRandomInput("Merge", 9000, 100));
    }
}
