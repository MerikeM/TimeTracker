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
    public int getHours(){
        return hours;
    }

    public int getMinutes(){
        return minutes;
    }

    public int getSeconds(){
        return seconds;
    }

    //liidab kaks aega kokku
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

    public int getTimeInSeconds(){
        int timeInSeconds = seconds + minutes * 60 + hours * 3600;
        return timeInSeconds;
    }

    //lahutab esimesest ajast teise aja
    //tulemus ei tohi olla negatiivne, st esimene aeg >= teisega
    public static Time subtractTimes(Time first, Time second){
        int newHours, newMin, newSec;
        if (first.seconds<second.seconds){
            first.seconds += 60;
            first.minutes--;
        }
        newSec = first.seconds-second.seconds;
        if (first.minutes<second.minutes){
            first.minutes += 60;
            first.hours--;
        }
        newMin = first.minutes-second.minutes;
        newHours = first.hours-second.hours;
        if (newHours<0){
            throw new IllegalArgumentException("esimene aeg peab olema suurem kui teine");
        }

        Time time = new Time(newHours, newMin, newSec);
        return time;
    }

    //tagastab aja kujul ..h ..min ..sek
    public String toString(){
        return(hours + "h " + minutes + "min " + seconds + "sek");
    }



}
