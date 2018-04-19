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

    public void onClickDonateBlood(ActionEvent actionEvent)
    {
        String query =  "";
        if(group1.getSelectedToggle().getUserData().toString().compareTo("New User") == 0) //new user
        {
            String name = nameField.getText();
            String age = donorAge.getText();
            String gender = "Male";
            if(group3.getSelectedToggle().getUserData().toString().compareTo("Female") == 0)
                gender = "Female";
            String centreName = centre.getValue().toString();
            query = "INSERT INTO Donor (Age, Name, Gender, Centres) VALUES(" + age + ",'" + name + "', '" + gender + "', '" + centreName + "');";
        }
        else //old user
        {
            String did = donorID.getText();
            String quantity = quantityField.getText();
            String bld = group.getSelectedToggle().getUserData().toString();
            String price = priceField.getText();
            String donDate = Date.getValue().toString();

            query = "INSERT INTO Blood (DonorID, RecipientID, Quantity, BloodGroup, Price, DonationDate, RecievingDate) VALUES("+did+", null, "+quantity+", '"+bld+"', "price", '"+donDate+"', null);";
        }

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

    public void initialize_toggle_button()
    {
        ToggleGroup group  = new ToggleGroup();
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();
        ToggleGroup g4 = new ToggleGroup();
        
        newUser.setToggleGroup(g4);
        existingUser.setToggleGroup(g4);
        
        genderFemale.setToggleGroup(group3);
        genderMale.setToggleGroup(group3);
        
        newUser.setToggleGroup(group1);
        existingUser.setToggleGroup(group1);
        
        RadioButton[] button = {BP,ABN,OP,ABP,AN,ON,BN,AP};
        for(RadioButton a : button)
        {
            a.setToggleGroup(group);
        }
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
