package Main;

import java.time.LocalDate;

/**
 * Created by Merike on 16-Nov-16.
 */
public class ChangeEntryWindow extends AddEntryWindow {

    public void display() {
        TableData tableData = EntryView.entryTable.getSelectionModel().getSelectedItem();

        //Leia praeguse sissekande väärtused
        String currentProjectName = tableData.getProject();
        Time currentTime = tableData.getTimeObject();
        int currentHours = currentTime.getHours();
        int currentMinutes = currentTime.getMinutes();
        int currentSeconds = currentTime.getSeconds();
        LocalDate currentDate = LocalDate.of(tableData.getYear(), tableData.getMonth(), tableData.getDay());

        //Muuda väljade vaikimisi väärtused praeguse sissekande väärtusteks
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
            EntryView.DeleteEntry();
    }

}