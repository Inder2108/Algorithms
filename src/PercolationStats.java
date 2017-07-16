import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int percolationSize = 0;
    private int trialSize = 0;
    private double[] percolationStats = null;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        } else {
            this.percolationSize = n;
            this.trialSize = trials;
            this.percolationStats = new double[trials];
            for (int i = 0; i < trials; i++) {
                Percolation percolationObject = new Percolation(percolationSize);
                while (!percolationObject.percolates()) {
                    int randomRowIndex = StdRandom.uniform(1, percolationSize + 1);
                    int randomColIndex = StdRandom.uniform(1, percolationSize + 1);
                    percolationObject.open(randomRowIndex, randomColIndex);
                }
                this.percolationStats[i] = (percolationObject.numberOfOpenSites() + 0.0)
                        / (percolationSize * percolationSize);
            }
        }
    }

    public double mean() {
        return StdStats.mean(percolationStats);
    }

    public double stddev() {
        return StdStats.stddev(percolationStats);
    }

    public double confidenceLo() {
        double stdDev = this.stddev();
        double mean = this.mean();
        return mean - ((1.96 * stdDev) / Math.sqrt(trialSize));
    }

    public double confidenceHi() {
        double stdDev = this.stddev();
        double mean = this.mean();
        return mean + ((1.96 * stdDev) / Math.sqrt(trialSize));
    }

    public static void main(String[] args) {
        int percolationSize = Integer.parseInt(args[0]);
        int numberOfTests = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(percolationSize, numberOfTests);

        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println(percolationStats.confidenceLo());
        System.out.println(percolationStats.confidenceHi());
    }
}
