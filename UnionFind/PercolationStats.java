import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // number of experiments
    private final int trials;

    // the stats
    private final double[] stats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();

        stats = new double[trials];
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percalation = new Percolation(n);

            while (!percalation.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                percalation.open(row, col);
            }

            stats[i] = (double) percalation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(stats) -
                ((1.96 * StdStats.stddev(stats)) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(stats) +
                ((1.96 * StdStats.stddev(stats)) / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        PercolationStats ps =
                new PercolationStats(
                        Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println(
                "95% confidence interval = ["
                        + ps.confidenceLo()
                        + ", "
                        + ps.confidenceHi() + "]");
    }
}
