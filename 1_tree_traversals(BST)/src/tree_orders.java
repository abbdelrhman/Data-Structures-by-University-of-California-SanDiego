import java.util.*;
import java.io.*;

public class tree_orders {
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            recursiveInOrder(result, 0);
            return result;
        }

        private void recursiveInOrder(ArrayList<Integer> ans, int i) {
            if (left[i] != -1) {
                recursiveInOrder(ans, left[i]);
            }
            ans.add(key[i]);
            if (right[i] != -1) {
                recursiveInOrder(ans, right[i]);
            }
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            recursivePreOrder(result,0);
            return result;
        }

        private void recursivePreOrder(ArrayList<Integer> ans, int i) {
            ans.add(key[i]);
            if(left[i]!=-1){
                recursivePreOrder(ans,left[i]);
            }
            if (right[i]!=-1){
                recursivePreOrder(ans,right[i]);
            }
        }


        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            recursivePosOrder(result,0);
            return result;
        }

        private void recursivePosOrder(ArrayList<Integer> ans , int i){
            if(left[i]!=-1){
                recursivePosOrder(ans,left[i]);
            }
            if (right[i]!=-1){
                recursivePosOrder(ans,right[i]);
            }
            ans.add(key[i]);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
