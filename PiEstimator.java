public class PiEstimator{
    
    public long numPoints;
    public int numThreads;

    public PiEstimator(long numPoints, int numThreads){
        this.numPoints = numPoints;
        this.numThreads = numThreads;
    }

    public double getPiEstimate(){

        Thread[] threads = new Thread[numThreads];
        long[] outputs = new long[numThreads];
        long numThreadPoints = numPoints / numThreads;

        for(int i = 0; i < numThreads; i++){
            threads[i] = new Thread(new PiThread(numThreadPoints, i, outputs));
        }

        for(Thread t : threads){
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            }
            catch (InterruptedException ignored) {
                // don't care if t was interrupted
            }
        }

        long in = 0;

        for(long output : outputs){
            in += output;
        }

        return 4.0 * in / numPoints;
    }

    public static void main(String[] args){

        int NUM_POINTS = 10_048_576;
        int n = 1;

        System.out.println("Running Monte Carlo simulation with n = " + NUM_POINTS + " samples...\n");
	    System.out.println( "n threads | pi estimate | time (ms)\n"
			                +"-----------------------------------");

        PiEstimator pe = new PiEstimator(NUM_POINTS, n);
	    
	    long start = System.nanoTime();
	    
	    double est = pe.getPiEstimate();
	    
	    long end = System.nanoTime();

	    System.out.printf("%9d |     %.5f | %6d\n", n, est, (end - start) / 1_000_000);  
    }

}