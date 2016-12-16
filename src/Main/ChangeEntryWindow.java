package Main;

import java.time.LocalDate;

import static Main.Main.entryView;

/**
 * Created by Merike on 16-Nov-16.
 */
public class ChangeEntryWindow extends AddEntryWindow {

    //loob sissekannete muutmise akna
    public void display() {
        TableData tableData = entryView.entryTable.getSelectionModel().getSelectedItem();

        //leia praeguse sissekande andmed
        String currentProjectName = tableData.getProject();
        Time currentTime = tableData.getTimeObject();
        int currentHours = currentTime.getHours();
        int currentMinutes = currentTime.getMinutes();
        int currentSeconds = currentTime.getSeconds();
        LocalDate currentDate = LocalDate.of(tableData.getYear(), tableData.getMonth(), tableData.getDay());

        //muuda väljade vaikimisi väärtused praeguse sissekande väärtusteks
        super.display();
        hourField.setText(Integer.toString(currentHours));
        minField.setText(Integer.toString(currentMinutes));
        secField.setText(Integer.toString(currentSeconds));
        datePicker.setValue(currentDate);
        projectDropdown.setValue(currentProjectName);
    }

        //lisa uus sissekanne ja kustuta vana
        public void addEntry(){
            super.addEntry();
            TableData tableData = entryView.entryTable.getSelectionModel().getSelectedItem();
            entryView.deleteEntry();

    }

}
