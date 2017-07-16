import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private final Board boardInitial;
    private final int moves;
    private final ArrayList<Board> solution;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int manhattan;

        public SearchNode(Board board) {
            this.board = board;
            this.manhattan = board.manhattan();
        }

        public int compareTo(SearchNode arg0) {
            return manhattan - arg0.board.manhattan();
        }
    }

    public Solver(Board initial) {
        boardInitial = initial;
        int movesTemp = 0;
        solution = new ArrayList<Board>();

        // Create a minimum priority queue
        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();

        if (initial.isGoal()) {
            solution.add(initial);
            moves = 0;
        } else {
            // Pick up initial board as first step with 0 moves
            solution.add(boardInitial);
            Board previous = boardInitial;

            // Add initial board's neighbors to PQ
            for (Board board : boardInitial.neighbors()) {
                minPQ.insert(new SearchNode(board));
            }

            while (true) {

                // Remove least priority item from queue
                Board minimumFromQueue = minPQ.delMin().board;

                // Add this as a step in solution array and update previous
                solution.add(minimumFromQueue);
                previous = minimumFromQueue;
                movesTemp++;

                // Check if reached goal, else add neighbors to PQ
                if (minimumFromQueue.isGoal()) {
                    break;
                } else {
                    minPQ = new MinPQ<SearchNode>();
                    for (Board board : minimumFromQueue.neighbors()) {
                        if (!board.equals(previous))
                            minPQ.insert(new SearchNode(board));
                    }
                }
            }
            moves = movesTemp;
        }

    }

    public boolean isSolvable() {

        // Create a minimum priority queue for two instances
        MinPQ<Board> minPQOriginal = new MinPQ<Board>(new Comparator<Board>() {
            public int compare(Board b1, Board b2) {
                return b1.manhattan() - b2.manhattan();
            }
        });
        MinPQ<Board> minPQTwin = new MinPQ<Board>(new Comparator<Board>() {
            public int compare(Board b1, Board b2) {
                return b1.manhattan() - b2.manhattan();
            }
        });
        Board initialTwin = boardInitial.twin();

        if (boardInitial.isGoal()) {
            return true;
        } else {

            // Add initial board's neighbors to PQ
            for (Board board : boardInitial.neighbors()) {
                minPQOriginal.insert(board);
            }
            for (Board board : initialTwin.neighbors()) {
                minPQTwin.insert(board);
            }

            while (true) {

                // Remove least priority item from queue
                Board minimumFromQueueOriginal = minPQOriginal.delMin();
                Board minimumFromQueueTwin = minPQTwin.delMin();

                // Check if reached goal, else add neighbors to PQ
                if (minimumFromQueueOriginal.isGoal()) {
                    return true;
                } else if (minimumFromQueueTwin.isGoal()) {
                    return false;
                } else {
                    for (Board board : minimumFromQueueOriginal.neighbors()) {
                        minPQOriginal.insert(board);
                    }
                    for (Board board : minimumFromQueueTwin.neighbors()) {
                        minPQTwin.insert(board);
                    }
                }
            }
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
