import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.sql.ResultSet;

public class TakeBloodPageController {

    public RadioButton genderMale;
    public RadioButton genderFemale;
    public CheckBox BP;
    public CheckBox OP;
    public CheckBox AP;
    public CheckBox ABP;
    public CheckBox AN;
    public CheckBox ON;
    public CheckBox BN;
    public CheckBox ABN;
    public CheckBox CentreA;
    public CheckBox CentreB;
    public CheckBox CentreC;
    public Button Submit;
    public TextField daysOld;
    public RadioButton hospitalA;
    public RadioButton hospitalB;
    public RadioButton hospitalC;
    public RadioButton newReciever;
    public RadioButton existingReceiver;
    public TextField receiverID;
    public TextField nameField;


    public void onClickSubmit(ActionEvent actionEvent) {
        CreateDatabase db = new CreateDatabase();


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layout/Result.fxml"));
            Parent root = (Parent) loader.load();
            ResultController resultController = loader.getController();
            resultController.setLabelText("Result from the query");
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Exception is "+e);
        }

    }

    public void initialize_toggle(){
        ToggleGroup g1 = new ToggleGroup();
        ToggleGroup g2 = new ToggleGroup();
        ToggleGroup g3 = new ToggleGroup();
        hospitalA.setToggleGroup(g1);
        hospitalB.setToggleGroup(g1);
        hospitalC.setToggleGroup(g1);
        genderMale.setToggleGroup(g2);
        genderFemale.setToggleGroup(g2);
        newReciever.setToggleGroup(g3);
        existingReceiver.setToggleGroup(g3);
    }


}
