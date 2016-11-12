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
}
