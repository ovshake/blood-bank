import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class DonateBloodPage {
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public DatePicker Date;
    public ComboBox centre;
    public RadioButton BP;
    public RadioButton ABN;
    public RadioButton OP;
    public RadioButton ABP;
    public RadioButton AN;
    public RadioButton ON;
    public RadioButton BN;
    public RadioButton AP;
    public TextField nameField;
    public TextField priceField;
    public RadioButton newUser;
    public RadioButton existingUser;
    public TextField donorID;
    public TextField donorAge;
    public TextField quantityField;

    public void onClickDonateBlood(ActionEvent actionEvent) {

    }

    public void initialize_toggle_button(){
        ToggleGroup group  = new ToggleGroup();
        ToggleGroup group1 = new ToggleGroup();
        newUser.setToggleGroup(group1);
        existingUser.setToggleGroup(group1);
        RadioButton[] button = {BP,ABN,OP,ABP,AN,ON,BN,AP};
        for(RadioButton a : button){
            a.setToggleGroup(group);
        }
    }
}
