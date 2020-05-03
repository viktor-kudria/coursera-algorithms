public class QuickFind extends UnionFind {

    public QuickFind(int N) {
        super(N);
    }

    void union(int p, int q) {
        int pId = id[p];
        int qId = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    boolean connected(int p, int q) {
        return id[p] == id[q];
    }
}
