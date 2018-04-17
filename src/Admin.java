import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class Admin {

    public ComboBox fieldDropdown;
    public ComboBox Operation;
    public ComboBox BloodGroup;


    public void onClickSubmit(ActionEvent actionEvent) {
    }

    public void initialise_combo_box(){
        fieldDropdown.getItems().add("PRICE");
        fieldDropdown.getItems().add("QUANTITY");
        fieldDropdown.getItems().add("AGE");

        String[] operations = {"AVERAGE","COUNT","MIN","MAX","SUM"};
        String[] bloodGroups = {"A+","B+","O+","AB+","AB-","A-","B-","AB-"};
        for(String a : operations)
            Operation.getItems().add(a);
        for(String a : bloodGroups)
            BloodGroup.getItems().add(a);

    }
}
