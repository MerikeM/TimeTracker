package Main;

/**
 * Created by Merike on 03-Oct-16.
 */
public class Stopwatch {
    private static long start = 0;
    private static long end = 0;
    private static int length;
    private static int currentLength;
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
        length=(int)(end-start)/1000;
    }

    public static int getCurrentLength(){
        currentLength=(int)(System.currentTimeMillis()-start)/1000;
        return currentLength;
    }

    public static long getStart() {
        return start;
    }

    public static void setStart(long start) {
        Stopwatch.start = start;
    }

    public static long getEnd() {
        return end;
    }

    public static void setEnd(long end) {
        Stopwatch.end = end;
    }

    public static int getLength() {
        return length;
    }

    public static void setLength(int length) {
        Stopwatch.length = length;
    }

    public static void setCurrentLength(int currentLength) {
        Stopwatch.currentLength = currentLength;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Stopwatch.running = running;
    }
}

