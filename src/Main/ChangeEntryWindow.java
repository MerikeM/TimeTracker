package Main;

import java.time.LocalDate;

/**
 * Created by Merike on 16-Nov-16.
 */
public class ChangeEntryWindow extends AddEntryWindow {
    TableData tableData;
    EntryView entryView;


    public ChangeEntryWindow(ProjectList pl, TableData td, EntryView ev){
        super(pl);
        tableData = td;
        entryView = ev;
    }


    //loob sissekannete muutmise akna
    public void setWindow() {
        //leia praeguse sissekande andmed
        String currentProjectName = tableData.getProject();
        Time currentTime = tableData.getTimeObject();
        int currentHours = currentTime.getHours();
        int currentMinutes = currentTime.getMinutes();
        int currentSeconds = currentTime.getSeconds();
        LocalDate currentDate = LocalDate.of(tableData.getYear(), tableData.getMonth(), tableData.getDay());

        //muuda väljade vaikimisi väärtused praeguse sissekande väärtusteks
        super.setWindow();
        hourField.setText(Integer.toString(currentHours));
        minField.setText(Integer.toString(currentMinutes));
        secField.setText(Integer.toString(currentSeconds));
        datePicker.setValue(currentDate);
        projectDropdown.setValue(currentProjectName);

    }

        //lisa uus sissekanne ja kustuta vana
        public void addEntry(){
            entryView.deleteEntry();
            super.addEntry();


    }

}
