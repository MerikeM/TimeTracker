package Main;

import org.sqlite.SQLiteException;

import java.sql.*;

/**
 * Created by Merike on 02-Dec-16.
 */
public class Database {
    Connection conn = null;

    public Database() {
        createConnection();
        createTable();
    }

    private void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:timeTracker.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Andmebaas avatud");
    }

    public void createTable() {
        // Käsk ise on CREATE TABLE ja sulgude vahel on kõik tulbad, mida tahan, et tabel hoiaks.
        String sql = "CREATE TABLE IF NOT EXISTS Entries (id INT, projectName TEXT, day INT, month INT, year INT," +
                " hours INT, minutes INT, seconds INT);";
        try {
            // Statement objekt on vajalik, et SQL_Login käsku käivitada
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            stat.close(); // Statement tuleb samuti kinni panna nagu ka Connection.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readData(){
        try {
            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM Entries";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()){
                //loe andmed sisse
                int id = rs.getInt("id");
                String projectName = rs.getString("projectName");
                int day = rs.getInt("day");
                int month = rs.getInt("month");
                int year = rs.getInt("year");
                int hours = rs.getInt("hours");
                int minutes = rs.getInt("minutes");
                int seconds = rs.getInt("seconds");

                //vajadusel loo uus projekt
                Project project;
                try {
                project = ProjectList.findProjectByName(projectName);
                }
                catch (IllegalArgumentException e){
                    project = new Project(projectName);
                    Main.projectList.getProjectList().add(project);
                }

                Date date = new Date (year, month, day);
                Time time = new Time(hours, minutes, seconds);
                Entry entry = new Entry(time, date, projectName);
                project.newEntry(entry);
                EntryView.data.add(new TableData(projectName, entry.getDateString(), entry.getTimeString(), entry.getEntryID()));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Ühendus suletud");
    }
}
