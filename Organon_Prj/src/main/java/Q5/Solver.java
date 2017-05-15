package Q5;

import Q5.utility.MinPQ;
import Q5.utility.StdIn;
import Q5.utility.StdOut;

import java.util.Stack;

/**
 * Created by melek on 29.03.2017.
 * With reference to Princeton University.
 */
public class Solver {
    private MinPQ<Node> pq = new MinPQ<Node>();
    private int minMoves = -1;
    private Node bestNode;
    private boolean solved;

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves, dist;
        private Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            dist = board.manhattan();
        }

        @Override
        public int compareTo(Node that) {
            return this.moves + this.dist - that.moves - that.dist;
        }
    }

    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        if (initial == null) {
            throw new NullPointerException();
        }
        pq.insert(new Node(initial, 0, null));
        pq.insert(new Node(initial.twin(), 0, null));
        while (!pq.isEmpty()) {
            Node current = pq.delMin();
            if (current.board.isGoal()) {
                Node root = root(current);
                if (!root.board.equals(initial)) {
                    break;
                }
                solved = true;
                if (minMoves == -1 || current.moves < minMoves) {
                    minMoves = current.moves;
                    bestNode = current;
                }
            }
            if (minMoves == -1 || current.moves + current.dist < minMoves) {
                Iterable<Board> it = current.board.neighbors();
                for (Board b : it) {
                    if (current.prev == null || !b.equals(current.prev.board)) {
                        pq.insert(new Node(b, current.moves + 1, current));
                    }
                }
            } else {
                break;
            }
        }
    }

    private Node root(Node node) {
        Node current = node;
        while (current.prev != null) {
            current = current.prev;
        }
        return current;
    }
    public boolean isSolvable() {
        // is the initial board solvable?
        return solved;
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return minMoves;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (isSolvable()) {
            Stack<Board> sol = new Stack<Board>();
            Node current = bestNode;
            while (current != null) {
                sol.push(current.board);
                current = current.prev;
            }
            return sol;
        }
        return null;
    }

    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        // create initial board from file
        //In in = new In(args[0]);3
        StdOut.println("Please enter square matrix N value...");
        int N = StdIn.readInt();
        int[][] blocks = new int[N][N];
        StdOut.println("Please enter square matrix cells...");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = StdIn.readInt();
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

