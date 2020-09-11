import javax.management.Query;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    private List<String> [] hashTable ;
    // for hash function
    private int m;
    private int p = 1000000007;
    private int x = 263;

    public static void main(String[] args) throws IOException {

        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * x + s.charAt(i)) % p;
        return (int) hash % m;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                String elementToAddToAdd = query.s;
                int hashValue = hashFunc(elementToAddToAdd);
                    if(hashTable[hashValue] == null){
                        hashTable[hashValue]=new ArrayList<>();
                        hashTable[hashValue].add(0,elementToAddToAdd);
                    }else {
                        //whether this elementToAddToAdd exist in this list so we ignore this query or it doesn't exist so we add it to this list
                        if(!hashTable[hashValue].contains(elementToAddToAdd)){
                            hashTable[hashValue].add(0,elementToAddToAdd);
                        }
                    }
//                if (!elems.contains(query.s))
//                    elems.add(0, query.s);
                break;
            case "del":
                String deleteElement = query.s;
                int hashValueForDel = hashFunc(deleteElement);
                if (hashTable[hashValueForDel] !=null && hashTable[hashValueForDel].contains(deleteElement)){
                    hashTable[hashValueForDel].remove(deleteElement);
                }
//                if (elems.contains(query.s))
//                    elems.remove(query.s);
                break;
            case "find":
                String elementFind = query.s;
                int findValue = hashFunc(elementFind);
                if(hashTable[findValue] != null && hashTable[findValue].contains(elementFind)){
                    out.println("yes");
                }else {
                    out.println("no");
                }
//                writeSearchResult(elems.contains(query.s));
                break;
            case "check":

                int hashValueForCheck = query.ind;
                if(hashTable[hashValueForCheck]!=null){
                    for(String s : hashTable[hashValueForCheck]){
                        out.print(s + " ");
                    }
                }
                out.println();
//                for (String cur : elems)
//                    if (hashFunc(cur) == query.ind)
//                        out.print(cur + " ");
//                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();

        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        m = in.nextInt();
        hashTable = new ArrayList[m];
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
