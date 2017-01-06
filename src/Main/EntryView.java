package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by Merike on 12-Nov-16.
 */
public class EntryView {
    ProjectList projectList;

    BorderPane entryArea = new BorderPane();

    Button selfAddButton = new Button("Lisa");
    Button changeButton = new Button("Muuda");
    Button deleteButton = new Button("Kustuta");

    TableView<TableData> entryTable;
    ObservableList<TableData> data = FXCollections.observableArrayList();

    public EntryView(ProjectList pl){
        projectList = pl;

        readData();
        makeTable();
    }

    //loeb kõik sissekanded ObservaleList'i data
    public void readData(){
        for(Project project: projectList.projectList){
            EntryList entryList = project.entries;
            for(Entry entry: entryList.entryList){
                data.add(new TableData(entry.getProjectName(), entry.getDateString(), entry.getTimeString(), entry.getEntryID()));
            }
        }
    }

    //loob sissekannete tabeli
    private void makeTable(){
        entryTable = new TableView<TableData>();
        entryTable.setPlaceholder(new Label("Ühtegi sissekannet pole"));
        entryTable.setMaxWidth(300);

        TableColumn projectColumn = new TableColumn("Projekt");
        projectColumn.setCellValueFactory(
                new PropertyValueFactory<TableData,String>("project")
        );

        TableColumn dateColumn = new TableColumn("Kuupäev");
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<TableData,String>("date")
        );

        TableColumn timeColumn = new TableColumn("Aeg");
        timeColumn.setCellValueFactory(
                new PropertyValueFactory<TableData,String>("time")
        );

        entryTable.setItems(data);
        entryTable.getColumns().addAll(projectColumn, timeColumn, dateColumn);
    }

    //tagastab Pane sissekannete vaatega
    public Pane open(){
        VBox buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(10,50,10,10));
        buttonsVBox.getChildren().addAll(selfAddButton, changeButton, deleteButton);
        entryArea.setRight(buttonsVBox);

        StackPane tablePane = new StackPane();
        tablePane.getChildren().addAll(entryTable);
        entryArea.setCenter(tablePane);

        selfAddButton.setOnAction(event -> addEntryManual());
        changeButton.setOnAction(event -> changeEntry());
        deleteButton.setOnAction(event -> deleteEntry());

        return entryArea;
    }

    //avab akna sissekannete käsitsi lisamiseks
    public void addEntryManual(){
        AddEntryWindow window = new AddEntryWindow(projectList);
        window.setWindow();
        window.blockParentAndShow();
        data.clear();
        readData();
    }

    //avab akna sissekannete muutmiseks
    public void changeEntry(){
        if (entryTable.getSelectionModel().isEmpty()){
            AlertBox.display("Viga", "Vali mõni sissekanne");
        } else {
            ChangeEntryWindow window = new ChangeEntryWindow(projectList, entryTable.getSelectionModel().getSelectedItem(), this);
            window.setWindow();
            window.blockParentAndShow();
        }
        data.clear();
        readData();
    }

    //kustutab valitud sissekande
    public void deleteEntry(){
        if (entryTable.getSelectionModel().isEmpty()){
            AlertBox.display("Viga", "Vali mõni sissekanne");
        } else {
            TableData tableData = entryTable.getSelectionModel().getSelectedItem();
            int id = tableData.getId();
            Entry entry = projectList.findEntryById(id);
            String projectName = entry.getProjectName();
            Project project = projectList.findProjectByName(projectName);
            project.deleteEntry(entry);
            data.remove(tableData);
        }
        data.clear();
        readData();
    }
}