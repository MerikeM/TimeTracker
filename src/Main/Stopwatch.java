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

    public static void start(){
        start=System.currentTimeMillis();
        running=true;
    }

    public static void end(){
        end=System.currentTimeMillis();
        running=false;
    }

    public static void calcLength(){
        int lengthInt=(int)(end-start)/1000;
        length = new Time(0,0,lengthInt);
    }

    public static Time getLength(){
        return length;
    }

    public static Time getCurrentLength(){
        int currentLengthInt=(int)(System.currentTimeMillis()-start)/1000;
        currentLength = new Time(0,0,currentLengthInt);
        return currentLength;
    }

    public static boolean isRunning() {
        return running;
    }

}

