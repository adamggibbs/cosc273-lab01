public class PiEstimator{
    
    public static long numPoints;
    public static int numThreads;

    public PiEstimator(long num_Points, int num_Threads){
        numPoints = num_Points;
        numThreads = num_Threads;
    }

    public double getPiEstimate(){

        int in = 0;
        
        // for(int i = 0; i < numPoints; i++){
        //     double x = Math.random()-0.5;
        //     double y = Math.random()-0.5; 

        //     if(x*x + y*y <= 0.25){
        //         in++;
        //     }
        // }

        for(int i = 0; i < numPoints; i++){
            double x = Math.random()*2-1;
            double y = Math.random()*2-1; 

            if(Math.sqrt(x*x + y*y) <= 1){
                in++;
            }
        }

        return 4.0 * in / numPoints;
    }

    public static void main(String[] args){

        int NUM_POINTS = 1_073_741_824;
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