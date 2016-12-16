package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static Main.Main.mainWindow;
import static Main.Main.projectList;
import static Main.Main.timerView;


/**
 * Created by Merike on 12-Nov-16.
 */
public class EntryView {
    Button selfAddButton;
    Button changeButton;
    Button deleteButton;

    TableView<TableData> entryTable;
    ObservableList<TableData> data = FXCollections.observableArrayList();

    public EntryView(){
        selfAddButton = new Button("Lisa");
        changeButton = new Button("Muuda");
        deleteButton = new Button("Kustuta");

        makeTable();
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

    //avab sissekannete vaate
    public void open(){
        VBox buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(10,50,10,10));
        buttonsVBox.getChildren().addAll(selfAddButton, changeButton, deleteButton);
        mainWindow.pane.setRight(buttonsVBox);

        StackPane tablePane = new StackPane();
        tablePane.getChildren().addAll(entryTable);
        mainWindow.pane.setCenter(tablePane);
    }

    //avab akna sissekannete käsitsi lisamiseks
    public void AddEntryManual(){
        AddEntryWindow window = new AddEntryWindow();
        window.display();
    }

    //avab akna sissekannete muutmiseks
    public void changeEntry(){
        if (entryTable.getSelectionModel().isEmpty()){
            AlertBox.display("Viga", "Vali mõni sissekanne");
        } else {
            ChangeEntryWindow window = new ChangeEntryWindow();
            window.display();
        }
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
    }
}
