package Main;

/**
 * Created by Merike on 03-Oct-16.
 */
public class Stopwatch {
    private long start = 0;
    private long end = 0;
    private Time length;
    private Time currentLength;
    private boolean running=false;

    //käivitab stopper
    public void start(){
        start=System.currentTimeMillis();
        running=true;
    }

    //peatab stopperi
    public void end(){
        end=System.currentTimeMillis();
        running=false;
    }

    //arvutab alguse ja lõpuaegade vahe
    public void calcLength(){
        int lengthInt=(int)(end-start)/1000;
        length = new Time(0,0,lengthInt);
    }

    public Time getLength(){
        return length;
    }

    //väljastab stopperi näidu ilma stopperit peatamata
    public Time getCurrentLength(){
        int currentLengthInt=(int)(System.currentTimeMillis()-start)/1000;
        currentLength = new Time(0,0,currentLengthInt);
        return currentLength;
    }

    public boolean isRunning() {
        return running;
    }

}

