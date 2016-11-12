package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Created by Merike on 12-Nov-16.
 */
public class EntryView {
    Button selfAddButton;
    Button changeButton;
    Button deleteButton;

    TableView<TableData> entryTable;
    static ObservableList<TableData> data = FXCollections.observableArrayList();

    public EntryView(){
        selfAddButton = new Button("Lisa");
        changeButton = new Button("Muuda");
        deleteButton = new Button("Kustuta");

        entryTable = new TableView<TableData>();

        makeTable();
    }

    private void makeTable(){
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

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<TableData,Double>("id")
        );

        entryTable.setItems(data);
        entryTable.getColumns().addAll(projectColumn, dateColumn, timeColumn, idColumn);
    }

    public void open(){
        VBox buttonsVBox = new VBox();
        buttonsVBox.setPadding(new Insets(10,10,10,10));
        buttonsVBox.getChildren().addAll(selfAddButton, changeButton, deleteButton);
        MainWindow.pane.setRight(buttonsVBox);

        StackPane tablePane = new StackPane();
        tablePane.getChildren().addAll(entryTable);
        MainWindow.pane.setCenter(tablePane);
    }

    public static void AddEntryManual(){
        AddEntryWindow window = new AddEntryWindow();
        window.display();
    }
}
