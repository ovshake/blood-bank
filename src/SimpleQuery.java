import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SimpleQuery {
    public TextField dontionYear;
    public ComboBox listOfAll;
    public ComboBox avalaibleBlood;
    public ComboBox hospital;
    public Label result;


    public void onClickSubmit(ActionEvent actionEvent) {
    }

    public void intialise_combo_box(){
        String[] listAll = {"Donor","Recipient","Centre","Hospital"};
        String[] avalaibleB = {"A+","B+","AB-","AB+","O+","O-","B-","A-"};
        for(String a : listAll)
            listOfAll.getItems().add(a);
        for(String a : avalaibleB)
            avalaibleBlood.getItems().add(a);

    }
}
