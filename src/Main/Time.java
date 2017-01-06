package Main;

/**
 * Created by Merike on 08-Oct-16.
 */
public class Time {
    int hours;
    int minutes;
    int seconds;

    public Time(int h, int m, int s){
        hours = h;

        hours += m/60;
        minutes = m%60;

        hours+=s/3600;
        minutes+=(s%3600)/60;
        if (minutes >= 60){
            hours += minutes/60;
            minutes = minutes%60;
        }
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
        int newSec=first.seconds + second.seconds;

        Time newTime = new Time(newHours, newMin, newSec);
        return newTime;
    }

    //tagastab aja sekundites
    public int getTimeInSeconds(){
        int timeInSeconds = seconds + minutes * 60 + hours * 3600;
        return timeInSeconds;
    }

    //tagastab aja tundides
    public double getTimeInHours(){
        double seconds = (double) getTimeInSeconds();
        double hours = seconds / 3600;
        return hours;
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

    //tagastab aja kujul hh:mm:ss
    public String toString(){
        String hoursString;
        String minutesString;
        String secondsString;
        if (hours<10){
            hoursString = "0" + hours;
        } else {
            hoursString = Integer.toString(hours);
        }
        if (minutes < 10) {
            minutesString = "0" + minutes;
        } else {
            minutesString = Integer.toString(minutes);
        }
        if (seconds < 10){
            secondsString = "0" + seconds;
        } else {
            secondsString = Integer.toString(seconds);
        }

        return(hoursString + ":" + minutesString + ":" + secondsString);
    }

}
