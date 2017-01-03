package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.util.*;

import static Main.Main.projectList;

/**
 * Created by Merike on 23-Dec-16.
 */
public class StatisticsViewProgress {
    Pane progressArea;
    TreeMap<String, HashMap> results;

    public StatisticsViewProgress(){
        progressArea = new Pane();
    }

    public Pane open(){
        calcStat();
        drawChart();

        return progressArea;
    }

    public void calcStat(){
        SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy.MM.dd");
        results = new TreeMap();
        Date minDate = new Date(Long.MAX_VALUE);
        Date maxDate = new Date(Long.MIN_VALUE);
        for (Project project: projectList.projectList) {
            for (Entry entry: project.entries.entryList) {
                String projectName = entry.getProjectName();
                Time entryTime = entry.getTime();
                Date entryDate = entry.getDate();
                if(entryDate.after(maxDate)){
                    maxDate = entryDate;
                }
                if(entryDate.before(minDate)){
                    minDate = entryDate;
                }
                if(results.containsKey(simpledatafo.format(entryDate))){
                    HashMap <String, Time> temp = results.get(simpledatafo.format(entryDate));
                    if(temp.containsKey(projectName)){
                        Time currentTime = temp.get(projectName);
                        Time newTime = Time.addTimes(currentTime, entryTime);
                        temp.put(projectName, newTime);
                    }
                    else {
                        temp.put(projectName, entryTime);
                    }
                    results.put(simpledatafo.format(entryDate), temp);
                } else {
                    HashMap<String, Time> times = new HashMap();
                    times.put(projectName, entryTime);
                    results.put(simpledatafo.format(entryDate), times);
                }
            }
        }


        for (Project project:projectList.projectList) {
            String projectName=project.getName();

            Date testDate = minDate;
            while (!testDate.after(maxDate)) {
                if (results.containsKey(simpledatafo.format(testDate))) {
                    HashMap <String, Time> temp = results.get(simpledatafo.format(testDate));
                    if (!temp.containsKey(projectName)){
                        temp.put(projectName,new Time(0,0,0));
                        results.put(simpledatafo.format(testDate), temp);
                    }
                } else {
                    HashMap<String, Time> hm = new HashMap();
                    hm.put(projectName, new Time (0,0,0));
                    results.put(simpledatafo.format(testDate),hm);
                }
                Calendar c = Calendar.getInstance();
                c.setTime(testDate);
                c.add(Calendar.DATE, 1);
                testDate = c.getTime();

            }
        }

    }

    public void drawChart(){
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart lineChart = new LineChart(xAxis, yAxis);

        ObservableList<XYChart.Series<String, Double>> chartData = FXCollections.observableArrayList();

        for (Project project: projectList.projectList) {
            String projectName = project.getName();
            XYChart.Series<String, Double> series = new XYChart.Series();
            series.setName(projectName);

            for(Map.Entry<String, HashMap> a : results.entrySet()) {
                String date = a.getKey();
                HashMap<String, Time> times = a.getValue();

                for(Map.Entry<String, Time> b : times.entrySet()) {
                    String entryProjectName = b.getKey();
                    Time time = b.getValue();
                    int timeInMinutes = time.getTimeInSeconds()/60;

                   if(projectName.equals(entryProjectName)){
                       series.getData().add(new XYChart.Data(date.toString(), timeInMinutes));
                   }
                }
            }

            chartData.add(series);
        }

        lineChart.setData(chartData);
        progressArea.getChildren().addAll(lineChart);

    }

}
