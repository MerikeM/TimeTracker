package Main;

/**
 * Created by Merike on 03-Oct-16.
 */
public class Stopwatch {
    static long start = 0;
    static long end = 0;
    static int length;
    static int currentLength;
    static boolean running=false;

    public static void start(){
        start=System.currentTimeMillis();
        running=true;
    }

    public static void end(){
        end=System.currentTimeMillis();
        running=false;
    }

    public static int getLength(){
        length=(int)(end-start)/1000;
        return length;
    }

    public static int getCurrentLength(){
        currentLength=(int)(System.currentTimeMillis()-start)/1000;
        return currentLength;
    }

}

