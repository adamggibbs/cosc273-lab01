public class PiEstimator{
    
    // variable to store number of points and threads used
    public long numPoints;
    public int numThreads;

    // contructor
    public PiEstimator(long numPoints, int numThreads){
        this.numPoints = numPoints;
        this.numThreads = numThreads;
    }

    // function that splits job of randomly making points to threads, 
    // then calculating pi
    public double getPiEstimate(){

        // array to store threads
        Thread[] threads = new Thread[numThreads];
        // number of points calculated per thread -- an even split among each thread
        long numThreadPoints = numPoints / numThreads;
        // shared array to store number of "hits" from each thread
        long[] outputs = new long[numThreads];

        // create the number of threads specified by numThreads
        for(int i = 0; i < numThreads; i++){
            threads[i] = new Thread(new PiThread(numThreadPoints, i, outputs));
        }

        // start all the threads
        for(Thread t : threads){
            t.start();
        }

        // wait for all the threads to finish 
        // note: code chunk from Multithreading notes from class webpage
        for (Thread t : threads) {
            try {
                t.join();
            }
            catch (InterruptedException ignored) {
                // don't care if t was interrupted
            }
        }

        // add the number of hits from each thread
        long hits = 0;
        for(long output : outputs){
            hits += output;
        }

        // calculate and return estimate of pi
        return 4.0 * hits / numPoints;
    }

} // PiEstimator