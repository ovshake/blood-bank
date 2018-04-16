import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.*;

public class LandingPage {
    public RadioButton donateBlood;
    public RadioButton receiveBlood;
    public Button submit;

    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        if(donateBlood.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Layout/DonateBloodPage.fxml"));
            stageTheLabelBelongs.setTitle("Student Menu");
            stageTheLabelBelongs.setScene(new Scene(root, 700, 500));
            stageTheLabelBelongs.show();
        }
        else if(receiveBlood.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Layout/TakeBloodPage.fxml"));
            stageTheLabelBelongs.setTitle("Student Menu");
            stageTheLabelBelongs.setScene(new Scene(root, 700, 500));
            stageTheLabelBelongs.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select A Option!",ButtonType.OK);
            alert.showAndWait();
        }
    }
}
