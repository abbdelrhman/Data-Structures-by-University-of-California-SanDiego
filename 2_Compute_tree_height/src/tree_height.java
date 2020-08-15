import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class Node {
        ArrayList<Node> children;
        int value;


        public int getValue() {
            return value;
        }

        public Node(int value) {
            this.value = value;
            children = new ArrayList<>();
        }

        public void addNode(Node node) {
            children.add(node);
        }
    }

    public class TreeHeight {
        int n;
        int indexOfRoot;
        int parent[];
        Node[] nodes;
        Node root;
        HashMap< Integer,Integer> map = new HashMap<>();

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                nodes[i] = new Node(i);
                parent[i] = in.nextInt();
                map.put(i,parent[i]);
                if (parent[i] == -1) {
                    indexOfRoot = i;
                }
            }
             root = nodes[indexOfRoot];
            for (int i = 0; i < map.size(); i++) {
                if (map.get(i) == -1) {

                } else {
                    nodes[map.get(i)].addNode(nodes[i]);
                }
            }

        }


        int computeHeight(Node root) {
            // Replace this code with a faster implementation

            if(root == null){
                return 0;
            }
            int h = 0;
            for (Node n : root.children){
                h = Math.max(h,computeHeight(n));
            }
            return h+1;

        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight(tree.root));
    }
}
