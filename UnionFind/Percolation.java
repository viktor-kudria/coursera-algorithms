import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // the size of side
    private final int n;
    // the grid model
    private final int[][] id;
    // number of open sites
    private int openSites = 0;

    // weighted union find algorithm implementation
    private final WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        id = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                id[i][j] = 0;
            }
        }

        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!checkArguments(row, col)) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;

        id[row - 1][col - 1] = 1;
        openSites++;

        int elIndex = getIndex(row, col);
        // geting neighbors
        int up;
        int right;
        int down;
        int left;

        if (row == 1) {
            up = 0;
            uf.union(elIndex, up);
        } else {
            up = getIndex(row - 1, col);

            if (isOpen(row - 1, col)) {
                uf.union(elIndex, up);
            }
        }

        if (row == n) {
            down = n * n + 1;
            uf.union(elIndex, down);
        } else {
            down = getIndex(row + 1, col);

            if (isOpen(row + 1, col)) {
                uf.union(elIndex, down);
            }
        }


        if (col < n) {
            right = getIndex(row, col + 1);
            if (isOpen(row, col + 1)) {
                uf.union(elIndex, right);
            }
        }

        if (col > 1) {
            left = getIndex(row, col - 1);
            if (isOpen(row, col - 1)) {
                uf.union(elIndex, left);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkArguments(row, col)) throw new IllegalArgumentException();

        return id[row - 1][col - 1] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkArguments(row, col)) throw new IllegalArgumentException();

        return uf.find(getIndex(row, col)) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(0) == uf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 1);
        percolation.open(2, 2);
        percolation.open(3, 3);
    }

    // if arguments are correct
    private boolean checkArguments(int row, int col) {
        return row - 1 >= 0 && col - 1 >= 0 && row <= n && col <= n;
    }

    // return element index
    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }
}
