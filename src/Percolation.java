import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] array = null;
    private int virtualTop, virtualBottom = 0;
    private int openSites = 0;
    private int size = 0;
    private WeightedQuickUnionUF wquf = null;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        } else {
            this.virtualBottom = (n * n);
            this.virtualTop = (n * n) + 1;
            this.array = new int[n][n];
            this.size = n;
            this.wquf = new WeightedQuickUnionUF((n * n) + 2);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    this.array[i][j] = 0;
                }
            }
        }
    }

    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            row = row - 1;
            col = col - 1;
            if (row == 0) {
                wquf.union((row * size) + col, virtualTop);
            } else if (row == size - 1) {
                wquf.union((row * size) + col, virtualBottom);
            }
            this.array[row][col] = 1;
            // Check open site on left and connect if open
            if (col - 1 >= 0 && array[row][col - 1] == 1
                    && !wquf.connected((row * size) + col, (row * size) + (col - 1))) {
                wquf.union((row * size) + col, (row * size) + (col - 1));
            }

            // Check open site on top and connect if open
            if (row - 1 >= 0 && array[row - 1][col] == 1
                    && !wquf.connected((row * size) + col, ((row - 1) * size) + col)) {
                wquf.union((row * size) + col, ((row - 1) * size) + col);
            }

            // Check open site on bottom and connect if open
            if (row + 1 < size && array[row + 1][col] == 1
                    && !wquf.connected((row * size) + col, ((row + 1) * size) + col)) {
                wquf.union((row * size) + col, ((row + 1) * size) + col);
            }

            // Check open site on right and connect if open
            if (col + 1 < size && array[row][col + 1] == 1
                    && !wquf.connected((row * size) + col, (row * size) + (col + 1))) {
                wquf.union((row * size) + col, (row * size) + (col + 1));
            }
            this.openSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        return this.array[row - 1][col - 1] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            return wquf.connected(virtualTop, ((row - 1) * size) + (col - 1));
        }
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    public boolean percolates() {
        return wquf.connected(virtualBottom, virtualTop);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(Integer.parseInt(args[0]));

        while (!percolation.percolates()) {
            int randomRowIndex = StdRandom.uniform(1, Integer.parseInt(args[0]) + 1);
            int randomColIndex = StdRandom.uniform(1, Integer.parseInt(args[0]) + 1);
            percolation.open(randomRowIndex, randomColIndex);
        }
        System.out.println(percolation.openSites);

    }
}
