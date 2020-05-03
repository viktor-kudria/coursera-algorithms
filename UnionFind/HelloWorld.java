import java.io.*;

public class HelloWorld {
    public static void main(String[] args) throws java.io.IOException {
        File file = new File(".\\data.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int n = Integer.parseInt(br.readLine());
        UnionFind uf = new QuickUnion(n);

        String[] strs = new String[2];
        while ((st = br.readLine()) != null) {
            strs = st.split(" ");

            int p = Integer.parseInt(strs[0]);
            int q = Integer.parseInt(strs[1]);

            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
