package Main;
import java.util.Scanner;

/**
 * Created by Merike on 08-Oct-16.
 */
public class Konsoolipohine {
    public static void main(String[] args) {
        Project project = new Project();
        Scanner input = new Scanner(System.in);
        int h, min, s;
        int d, mo, y;
        Time entryTime = new Time(0,0,0);
        Date entryDate = new Date(1,1,2000);
        Entry entry;


        while (true){
            System.out.println("sisesta aeg kujul H M S");
            h = input.nextInt();
            min = input.nextInt();
            s = input.nextInt();
            entryTime.setTime(h, min, s);

            System.out.println("sisesta kuupäev kujul P K A (numbritega)");
            d = input.nextInt();
            mo = input.nextInt();
            y = input.nextInt();
            entryDate.setDate(d,mo,y);
            entry = new Entry(entryTime, entryDate);
            project.newEntry(entry);

            System.out.println("Töötatud aeg: " + project.getTotalTime());




        }









    }

}
