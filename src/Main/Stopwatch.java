package Main;

/**
 * Created by Merike on 03-Oct-16.
 */
public class Stopwatch {
    private static long start = 0;
    private static long end = 0;
    private static Time length;
    private static Time currentLength;
    private static boolean running=false;

    //käivitab stopper
    public static void start(){
        start=System.currentTimeMillis();
        running=true;
    }

    //peatab stopperi
    public static void end(){
        end=System.currentTimeMillis();
        running=false;
    }

    //arvutab alguse ja lõpuaegade vahe
    public static void calcLength(){
        int lengthInt=(int)(end-start)/1000;
        length = new Time(0,0,lengthInt);
    }

    public static Time getLength(){
        return length;
    }

    //väljastab stopperi näidu ilma stopperit peatamata
    public static Time getCurrentLength(){
        int currentLengthInt=(int)(System.currentTimeMillis()-start)/1000;
        currentLength = new Time(0,0,currentLengthInt);
        return currentLength;
    }

    public static boolean isRunning() {
        return running;
    }

}

