public class QuickUnion extends UnionFind {

    public QuickUnion(int N) {
        super(N);
    }

    void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);

        id[pRoot] = qRoot;
    }

    boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    protected int getRoot(int i) {
        while (id[i] != i) {
            i = id[i];
        }

        return i;
    }
}
