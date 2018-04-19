import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.sql.ResultSet;

public class TakeBloodPageController {

    // public CheckBox BP;
    // public CheckBox OP;
    // public CheckBox AP;
    // public CheckBox ABP;
    // public CheckBox AN;
    // public CheckBox ON;
    // public CheckBox BN;
    // public CheckBox ABN;
    
    // public CheckBox CentreA;
    // public CheckBox CentreB;
    // public CheckBox CentreC; 
    
    // public TextField daysOld;
    // public RadioButton newReciever;
    // public RadioButton existingReceiver;
    // public TextField receiverID;

    public RadioButton hospitalA;
    public RadioButton hospitalB;

    public RadioButton genderMale;
    public RadioButton genderFemale;
    public TextField recieverAge;        
    public TextField nameField;
    public Button Submit;

    public void onClickSubmit(ActionEvent actionEvent)
    {
        String query =  "";
        
        String name = nameField.getText();
        String age = recieverAge.getText();
        String gender = "Male";
        if(g2.getSelectedToggle().getUserData().toString().compareTo("Female") == 0)
            gender = "Female";
        
        String hid = "1";
        if(g1.getSelectedToggle().getUserData().toString().compareTo("Max Hospital") == 0)
            hid = "2";
        
        query = "INSERT INTO Recipient (HospitalID, Age, Name, Gender) VALUES("+hid+", "+age+", '"+name+"', '"+gender+"');";

        String message  = "Your Details were Submitted Successfully.";
        try
        {
            finalFun(query);
        }
        catch(Exception e)
        {
            message = "Please check the details and try again.";   
        }
        showDialogueBox(message);
    }

    public void initialize_toggle(){
        ToggleGroup g1 = new ToggleGroup();
        ToggleGroup g2 = new ToggleGroup();

        hospitalA.setToggleGroup(g1);
        hospitalB.setToggleGroup(g1);

        genderMale.setToggleGroup(g2);
        genderFemale.setToggleGroup(g2);
        
    }

    public void finalFun(String query) throws SQLException
    {
        ResultSet rs = db.getDataFromDB(query);
    }

        public void showDialogueBox(String message)
    {
        // TODO
    }
}
