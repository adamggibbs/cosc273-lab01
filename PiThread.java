import java.util.concurrent.ThreadLocalRandom;

public class PiThread implements Runnable{

    public long numThreadPoints;
    private int id;
    private long[] outputs;

    public PiThread(long numThreadPoints, int id, long[] outputs){
        this.numThreadPoints = numThreadPoints;
        this.id = id;
        this.outputs = outputs;
    }

    public void run(){
        long in = 0;

        for(int i = 0; i < numThreadPoints; i++){
            double x = ThreadLocalRandom.current().nextDouble()-0.5;
            double y = ThreadLocalRandom.current().nextDouble()-0.5; 

            if(x*x + y*y <= 0.25){
                in++;
            }
        }

        outputs[id] = in;
    }
}