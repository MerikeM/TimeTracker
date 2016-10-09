package Main;

/**
 * Created by Merike on 09-Oct-16.
 */
public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int d, int m, int y){
        if (d<=0 || d>31 || (d>30 && m==4 || m==6 || m==9 || m==11) || (m==2 && d>28) ){ //LISA JUURDE LIIGAASTA VÕIMALUS!!!
            throw new IllegalArgumentException("vale kuupäev");
        } else {
            day = d;
        }
        if (m>12 || m<1){
            throw new IllegalArgumentException("kuu peab olema number vahemikus 1-12");
        } else {
            month = m;
        }
        if (y<2000 || y>2100){
            throw new IllegalArgumentException("aktsepteeritakse aastaarve vahemikus 2000-2100");
        } else {
            year = y;
        }
    }

    public void setDate(int d, int m, int y){
        if (d<=0 || d>31 || (d>30 && m==4 || m==6 || m==9 || m==11) || (m==2 && d>28) ){ //LISA JUURDE LIIGAASTA VÕIMALUS!!!
            throw new IllegalArgumentException("vale kuupäev");
        } else {
            day = d;
        }
        if (m>12 || m<1){
            throw new IllegalArgumentException("kuu peab olema number vahemikus 1-12");
        } else {
            month = m;
        }
        if (y<2000 || y>2100){
            throw new IllegalArgumentException("aktsepteeritakse aastaarve vahemikus 2000-2100");
        } else {
            year = y;
        }
    }

    public String toString(){
        return(String.format("%02d.%02d.%02d", day, month, year));
    }
}
