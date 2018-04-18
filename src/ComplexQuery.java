import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ComplexQuery {

    public ComboBox avgPriceBGroup;
    public ComboBox centreComboBox;
    public ComboBox BloodGroupCBox;
    public ComboBox avgAgeRecipientCBox;
    public Label outputBox;

    public void onClickDonorGo(ActionEvent actionEvent) {
    }

    public void onClickCountBloodBank(ActionEvent actionEvent) {
    }

    public void intialise_combo_box(){
        String[] listAll = {"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        String[] hospitalList = {"Hospital A","Hospital B","Hospital C"};
        String[] centres = {"Centre A","Centre B","Centre C"};
        for(String a : centres)
            centreComboBox.getItems().add(a);
        for(String a : avalaibleB){
            avgPriceBGroup.getItems().add(a);
            BloodGroupCBox.getItems().add(a);
        }

        for(String a : hospitalList)
            avgAgeRecipientCBox.getItems().add(a);


    }

    public void onClickGo1(ActionEvent actionEvent) {
    }

    public void onClickGo2(ActionEvent actionEvent) {
    }

    public void onClickGo3(ActionEvent actionEvent) {
    }
}
