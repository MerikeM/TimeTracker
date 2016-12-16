package Main;

import java.sql.*;
import java.sql.Date;

import static Main.Main.entryView;
import static Main.Main.projectList;

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
                project = projectList.findProjectByName(projectName);
                }
                catch (IllegalArgumentException e){
                    project = new Project(projectName);
                    projectList.add(project);
                }

                Date date = new Date (year, month, day);
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
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDate();
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
        System.out.println("Ühendus suletud");
    }
}
