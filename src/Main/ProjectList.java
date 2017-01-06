package Main;

import java.util.ArrayList;

/**
 * Created by Merike on 12-Nov-16.
 */
public class ProjectList {
    ArrayList<Project> projectList = new ArrayList<Project>();

    //tagastab kõigi projektide ajad kujul "projekti nimi: aeg"
    public String getTotalTimesAsString(){
        String result = "";
        for (int i = 0; i < projectList.size(); i++) {
            Project p = projectList.get(i);
            String s = p.getName() + ": " + p.getTotalTime() + "\n";
            result = result + s;
        }
        return result;
    }

    //Sisendiks projekte sisaldav ArrayList ja otsitava projekti nimi.
    //Väljastab otsitava nimega projekti.
    public Project findProjectByName(String name){
        for (int i = 0; i < projectList.size(); i++) {
            Project p = projectList.get(i);
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("sellist projekti ei ole");
    }

    //tagastab sissekande objekti, mis on vastava id-ga
    public Entry findEntryById (int id){
        for (Project project: projectList) {
            EntryList list = project.entries;
            for (Entry entry: list.entryList){
                if (entry.getEntryID()==id){
                    return entry;
                }
            }
        }
        throw new IllegalArgumentException("sellise id-ga sissekannet ei ole");
    }

    //väljastab i-nda projekti
    public Project getProject(int i){
        Project p = projectList.get(i);
        return p;
    }

    //lisab uue projekti
    public void add(Project p){
        projectList.add(p);
    }

    public ArrayList <Project> getProjectList(){
        return projectList;
    }


}
