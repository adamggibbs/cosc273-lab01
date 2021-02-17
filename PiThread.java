import java.util.concurrent.ThreadLocalRandom;

public class PiThread implements Runnable{

    // stores number of points calculated on this thread
    public long numThreadPoints;
    // stores id of thread, used for storing output of array
    private int id;
    // shared array where number of hits will be stored
    private long[] outputs;

    // constructor
    public PiThread(long numThreadPoints, int id, long[] outputs){
        this.numThreadPoints = numThreadPoints;
        this.id = id;
        this.outputs = outputs;
    }

    // method to call when thread is started
    public void run(){

        // variable to store number of hits 
        long hits = 0;

        // caluclate numThreadPoints amount of random points and see if it's inside the circle
        for(int i = 0; i < numThreadPoints; i++){
            // find random double in [0.5,0.5)
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble(); 

            // use a^2 + b^2 <= r^2 as criteria for being in circle
            // if point is in circle, increment hits
            if(x*x + y*y <= 1){
                hits++;
            }
        }

        // store number of hits in shared array
        outputs[id] = hits;
    }
}