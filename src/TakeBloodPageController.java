import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.sql.ResultSet;

public class TakeBloodPageController {


    public Slider bloodAgeSlider;
    public RadioButton genderMale;
    public RadioButton genderFemale;
    public RangeSlider donorAge;

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


}
