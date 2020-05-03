public class WeightedQuickUnion extends QuickUnion {
    protected int[] size;

    public WeightedQuickUnion(int N) {
        super(N);

        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
        }
    }

    void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);

        if (pRoot == qRoot) return;

        if (size[pRoot] <= size[qRoot]) {
            id[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            id[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }

    boolean connected(int p, int q) {
        return super.connected(p, q);
    }
}
