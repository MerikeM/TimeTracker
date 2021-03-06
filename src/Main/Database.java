//Kasutatud: https://github.com/KristerV/javaSQLNaide/blob/master/src/Andmebaas.java

package Main;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Merike on 02-Dec-16.
 */
public class Database {
    ProjectList projectList;

    Connection conn = null;

    public Database (){
        createConnection();
        createTable();
    }

    public Database(ProjectList pl) {
        projectList = pl;
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
                project = projectList.findProjectByName(projectName);
                }
                catch (IllegalArgumentException e){
                    project = new Project(projectName);
                    projectList.add(project);
                }

                Calendar calendar = new GregorianCalendar(year, month, day);
                java.util.Date date = calendar.getTime();
                Time time = new Time(hours, minutes, seconds);
                Entry entry = new Entry(time, date, projectName, id);
                project.newEntryWithoutDb(entry);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void newEntry(Entry entry){
        int id = entry.getEntryID();
        String projectName = entry.getProjectName();
        java.util.Date date = entry.getDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Time time = entry.getTime();
        int hours = time.getHours();
        int minutes = time.getMinutes();
        int seconds = time.getSeconds();

        String sql = "INSERT INTO Entries (id, projectName, day, month, year, hours, minutes, seconds) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, projectName);
            preparedStatement.setInt(3, day);
            preparedStatement.setInt(4, month);
            preparedStatement.setInt(5, year);
            preparedStatement.setInt(6, hours);
            preparedStatement.setInt(7, minutes);
            preparedStatement.setInt(8, seconds);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e){
            System.out.println("sissekande lisamine ebaõnnestus");
        }
    }

    public void deleteEntry (int id){
       String sql = "DELETE FROM Entries WHERE id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e){
            System.out.println("sissekande eemaldamine ebaõnnestus");
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}