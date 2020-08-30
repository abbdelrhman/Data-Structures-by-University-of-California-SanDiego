import java.io.*;

import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;
    thread[] threads;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void minHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < threads.length && (threads[i].time > threads[left].time|| (threads[i].time==threads[left].time && threads[left].index<threads[i].index) ) )  {
            smallest = left;
        }
        if (right < threads.length && (threads[right].time < threads[smallest].time||(threads[right].time == threads[smallest].time && threads[right].index<threads[smallest].index)) ) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }


    private void swap(int i, int j) {
        thread temp = threads[i];
        threads[i] = threads[j];
        threads[j] = temp;
    }

    public void increaseKey(int i, long key) {
        threads[i].time += key;
        minHeapify(i);
    }

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();

        threads = new thread[numWorkers];
        for (int i = 0; i < numWorkers; i++) {
            threads[i] = new thread(i, 0);
        }
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            assignedWorker[i] = threads[0].index;
            startTime[i] = threads[0].time;
            increaseKey(0, duration);
        }
//        long[] nextFreeTime = new long[numWorkers];//threads
//        for (int i = 0; i < jobs.length; i++) {
//            int duration = jobs[i];
//            int bestWorker = 0;
//            for (int j = 0; j < numWorkers; ++j) {
//                if (nextFreeTime[j] < nextFreeTime[bestWorker])
//                    bestWorker = j;
//            }
//            assignedWorker[i] = bestWorker;
//            startTime[i] = nextFreeTime[bestWorker];
//            nextFreeTime[bestWorker] += duration;
//        }

    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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

    class thread {
        int index;
        long time;

        public thread(int index, long time) {
            this.index = index;
            this.time = time;
        }
    }

}

