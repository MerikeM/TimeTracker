package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Merike on 18-Dec-16.
 */
public class StatisticsViewProjects {
    ProjectList projectList;

    Label startLabel, endLabel;
    DatePicker startPicker, endPicker;
    Button submitDateButton;

    Button currentDay, currentWeek, currentMonth, currentYear;
    Button lastDay, lastWeek, lastMonth, lastYear;

    BorderPane statisticsArea;

    public StatisticsViewProjects(ProjectList pl){
        projectList = pl;

        startLabel = new Label("Algus: ");
        endLabel = new Label("Lõpp: ");
        submitDateButton = new Button("OK");
        startPicker = new DatePicker();
        endPicker = new DatePicker();

        currentDay = new Button("Praegune päev");
        currentWeek = new Button("Praegune nädal");
        currentMonth = new Button("Praegune kuu");
        currentYear = new Button("Praegune aasta");
        lastDay = new Button("Eelmine päev");
        lastWeek = new Button("Eelmine nädal");
        lastMonth = new Button("Eelmine kuu");
        lastYear = new Button("Eelmine aasta");
    }

    //tagastab Pane projektide statistikaga
    public Pane open(){
        convertDateFormat(startPicker);
        convertDateFormat(endPicker);

        GridPane startEndChooser = new GridPane();
        startEndChooser.add(startLabel, 1, 2);
        startEndChooser.add(endLabel, 1, 3);
        startEndChooser.add(startPicker, 2, 2);
        startEndChooser.add(endPicker, 2, 3);
        startEndChooser.add(submitDateButton, 1, 4);
        startEndChooser.add(currentDay, 4, 1);
        startEndChooser.add(currentWeek, 4, 2);
        startEndChooser.add(currentMonth, 4, 3);
        startEndChooser.add(currentYear, 4, 4);
        startEndChooser.add(lastDay, 5, 1);
        startEndChooser.add(lastWeek, 5, 2);
        startEndChooser.add(lastMonth, 5, 3);
        startEndChooser.add(lastYear, 5, 4);

        statisticsArea = new BorderPane();
        statisticsArea.setTop(startEndChooser);

        setDateButtons();
        submitDateButton.setOnAction(event -> calcStat());

        return statisticsArea;
    }

    //ajaperioodi valimise nuppude funktsioonid
    public void setDateButtons(){
        currentDay.setOnAction(event -> {
            startPicker.setValue(LocalDate.now());
            endPicker.setValue(LocalDate.now());
        });

        currentWeek.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.with(DayOfWeek.MONDAY));

            endPicker.setValue(today.with(DayOfWeek.SUNDAY));
        });

        currentMonth.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.withDayOfMonth(1));
            endPicker.setValue(today.withDayOfMonth(today.lengthOfMonth()));
        });

        currentYear.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.withMonth(1).withDayOfMonth(1));
            endPicker.setValue(today.withMonth(12).withDayOfMonth(31));
        });

        lastDay.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.minusDays(1));
            endPicker.setValue(today.minusDays(1));
        });

        lastWeek.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.with(DayOfWeek.MONDAY).minusWeeks(1));
            endPicker.setValue(today.with(DayOfWeek.SUNDAY).minusWeeks(1));
        });

        lastMonth.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.withDayOfMonth(1).minusMonths(1));
            endPicker.setValue(today.withDayOfMonth(today.lengthOfMonth()).minusMonths(1));
        });

        lastYear.setOnAction(event -> {
            LocalDate today = LocalDate.now();
            startPicker.setValue(today.withMonth(1).withDayOfMonth(1).minusYears(1));
            endPicker.setValue(today.withMonth(12).withDayOfMonth(31).minusYears(1));
        });
    }

    //muudab kuupäeva kujule dd.mm.yyyy
    public void convertDateFormat(DatePicker datePicker){
        String pattern = "dd.MM.yyyy";
        datePicker.setPromptText(pattern.toLowerCase());
        datePicker.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    //näitab statistika valitud perioodi kohta ja koostab sellest diagrammi
    public void calcStat() {
        LocalDate startLocalDate = startPicker.getValue();
        LocalDate endLocalDate = endPicker.getValue();
        Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if (startDate.after(endDate)){
            AlertBox.display("Viga", "Lõpukuupäev peab olema hilisem kui alguskuupäev");
        }

        HashMap<String, Time> results = new HashMap();

        for (Project project : projectList.projectList) {
            for (Entry entry : project.entries.entryList) {
                if (!startDate.after(entry.getDate()) && !endDate.before(entry.getDate())) {
                    String projectName = entry.getProjectName();
                    Time entryTime = entry.getTime();
                    if (results.containsKey(projectName)) {
                        Time oldTime = results.get(projectName);
                        Time newTime = Time.addTimes(oldTime, entryTime);
                        results.put(projectName, newTime);
                    } else {
                        results.put(projectName, entryTime);
                    }
                }
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Project project : projectList.projectList) {
            String projectName = project.getName();
            Time time = results.get(projectName);
            if (time != null) {
                pieChartData.add(new PieChart.Data(projectName, time.getTimeInSeconds()));
            }

        }
        final PieChart chart = new PieChart(pieChartData);

        Label projectLabel = new Label("");
        Label timeLabel = new Label("");
        VBox projectInfo = new VBox();
        projectInfo.setMinWidth(100);
        projectInfo.getChildren().addAll(projectLabel, timeLabel);
        projectInfo.setPadding(new Insets(50, 10, 10, 10));
        statisticsArea.setLeft(projectInfo);

        if (pieChartData.isEmpty()) {
            Label noEntriesLabel = new Label("Valitud perioodil sissekanded puuduvad");
            statisticsArea.setCenter(noEntriesLabel);
        } else {

            for (final PieChart.Data data : chart.getData()) {
                data.getNode().setOnMouseEntered(event -> {
                    projectLabel.setText(data.getName());
                    Time time = new Time(0, 0, (int) data.getPieValue());
                    timeLabel.setText("Aeg: " + time.toString());
                });
                data.getNode().setOnMouseExited(event -> {
                    projectLabel.setText("");
                    timeLabel.setText("");
                });
            }
            statisticsArea.setCenter(chart);
        }
    }
}