import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {

    private final int[][] blocks;
    private final int dimension;
    private final int hamming;
    private final int manhattan;

    public Board(int[][] blocks) {
        this.blocks = blocks;
        this.dimension = blocks[0].length;
        int temp = 1;
        int tempHamming = 0;
        int tempManhattan = 0;
        int[] iIndex = new int[(dimension * dimension)];
        int[] jIndex = new int[(dimension * dimension)];

        int indexCount = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                iIndex[indexCount] = i;
                jIndex[indexCount] = j;
                indexCount++;
            }
        }

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.blocks[i][j] != 0 && this.blocks[i][j] != temp) {
                    tempHamming++;
                    tempManhattan += (iIndex[this.blocks[i][j] - 1] > i ? (iIndex[this.blocks[i][j] - 1] - i)
                            : (i - iIndex[this.blocks[i][j] - 1]))
                            + (jIndex[this.blocks[i][j] - 1] > j ? (jIndex[this.blocks[i][j] - 1] - j)
                                    : (j - jIndex[this.blocks[i][j] - 1]));
                }
                temp++;
            }
        }

        this.hamming = tempHamming;
        this.manhattan = tempManhattan;
    }

    public int dimension() {
        return dimension;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        return this.manhattan == 0;
    }

    public Board twin() {
        int[][] tempBlocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tempBlocks[i][j] = blocks[i][j];
            }
        }
        boolean isShuffleComplete = false;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j + 1 < dimension && blocks[i][j] != 0
                        && blocks[i][j + 1] != 0) {
                    tempBlocks[i][j] = blocks[i][j + 1];
                    tempBlocks[i][j + 1] = blocks[i][j];
                    isShuffleComplete = true;
                    break;
                }
            }
            if (isShuffleComplete)
                break;
        }
        return new Board(tempBlocks);
    }

    public boolean equals(Object y) {
        if (y == this)
            return true;
        if (y == null || !(y instanceof Board)
                || ((Board) y).blocks.length != blocks.length)
            return false;
        Board yBoard = (Board) y;
        boolean isBoardEqual = true;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] != yBoard.blocks[i][j]) {
                    isBoardEqual = false;
                }
            }
        }
        return isBoardEqual;
    }

    public Iterable<Board> neighbors() {
        int tempI = 0;
        int tempJ = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (blocks[i][j] == 0) {
                    tempI = i;
                    tempJ = j;
                    break;
                }
            }
        }
        ArrayList<Board> neighbours = new ArrayList<Board>();
        if (tempI + 1 <= dimension - 1) {
            int[][] tempBlocks = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    tempBlocks[i][j] = blocks[i][j];
                }
            }
            tempBlocks[tempI][tempJ] = tempBlocks[tempI + 1][tempJ];
            tempBlocks[tempI + 1][tempJ] = 0;
            neighbours.add(new Board(tempBlocks));
        }
        if (tempI - 1 >= 0) {
            int[][] tempBlocks = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    tempBlocks[i][j] = blocks[i][j];
                }
            }
            tempBlocks[tempI][tempJ] = tempBlocks[tempI - 1][tempJ];
            tempBlocks[tempI - 1][tempJ] = 0;
            neighbours.add(new Board(tempBlocks));
        }
        if (tempJ + 1 <= dimension - 1) {
            int[][] tempBlocks = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    tempBlocks[i][j] = blocks[i][j];
                }
            }
            tempBlocks[tempI][tempJ] = tempBlocks[tempI][tempJ + 1];
            tempBlocks[tempI][tempJ + 1] = 0;
            neighbours.add(new Board(tempBlocks));
        }
        if (tempJ - 1 >= 0) {
            int[][] tempBlocks = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    tempBlocks[i][j] = blocks[i][j];
                }
            }
            tempBlocks[tempI][tempJ] = tempBlocks[tempI][tempJ - 1];
            tempBlocks[tempI][tempJ - 1] = 0;
            neighbours.add(new Board(tempBlocks));
        }
        return neighbours;
    }

    public String toString() {
        String temp = dimension + "\n";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                temp += blocks[i][j] + " ";
            }
            temp += "\n";
        }
        return temp;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        // System.out.println(initial.neighbors());
        for (Board board : initial.neighbors())
            StdOut.println(board);
    }
}