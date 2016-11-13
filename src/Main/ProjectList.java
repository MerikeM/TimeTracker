package Main;

import java.util.ArrayList;

/**
 * Created by Merike on 12-Nov-16.
 */
public class ProjectList {
    private static ArrayList<Project> projectList; //sisaldab kõiki loodud projekte

    public ProjectList(){
        projectList = new ArrayList<Project>();
    }

    public String getTotalTimesAsString(){
        String result = "";
        for (int i = 0; i < projectList.size(); i++) {
            Project p = projectList.get(i);
            String s = p.toString() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    public ArrayList <Project> getProjectList(){
        return projectList;
    }

    //Sisendiks projekte sisaldav ArrayList ja otsitava projekti nimi.
    //Väljastab otsitava nimega projekti.
    public static Project findProjectByName(String name){
        for (int i = 0; i < projectList.size(); i++) {
            Project p = projectList.get(i);
            if (p.toString().equals(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("sellist projekti ei ole");
    }

    // koostab sõne, mis sisaldab kõiki projekte ja nende koguaegu
    public static String getTotalTimes(){
        String result = "";
        for (int i = 0; i < projectList.size(); i++) {
            Project p = projectList.get(i);
            String s = p.toString() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    //väljastab i-nda projekti
    public static Project getProject(int i){
        Project p = projectList.get(i);
        return p;
    }

}
