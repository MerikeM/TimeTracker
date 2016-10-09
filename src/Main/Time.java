package Main;

/**
 * Created by Merike on 08-Oct-16.
 */
public class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int h, int m, int s){
        hours = h;

        hours += m/60;
        minutes = m%60;

        hours+=s/3600;
        minutes+=(s%3600)/60;
        seconds=s%60;
    }

    public void setTime(int h, int m, int s){
        hours = h;

        hours += m/60;
        minutes = m%60;

        hours+=s/3600;
        minutes+=(s%3600)/60;
        seconds=s%60;
    }

    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    public static Time addTimes(Time first, Time second){
        int newHours=first.hours + second.hours;
        int newMin=first.minutes + second.minutes;
        if (newMin>=60){
            newHours+=newMin/60;
            newMin%=60;
        }
        int newSec=first.seconds + second.seconds;
        if (newSec >=3600){
            newHours+=newSec/3600;
            newMin+=(newSec%3600)/60;
            newSec%=60;
        }

        Time newTime = new Time(newHours, newMin, newSec);
        return newTime;
    }

    public String toString(){
        return(hours + "h " + minutes + "min " + seconds + "sek");
    }








}
