import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.*;

public class LandingPage {
    public RadioButton donateBlood;
    public RadioButton receiveBlood;
    public Button submit;
    public RadioButton adminButton;

    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        if(donateBlood.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/DonateBloodPage.fxml"));
            Parent root = (Parent) loader.load();
            DonateBloodPage donateBloodPage = loader.getController();
            donateBloodPage.initialize_toggle_button();
            stageTheLabelBelongs.setTitle("Donation Page");
            stageTheLabelBelongs.setScene(new Scene(root, 700, 500));
            stageTheLabelBelongs.show();
        }
        else if(receiveBlood.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/TakeBloodPage.fxml"));
            Parent root = (Parent) loader.load();
            TakeBloodPageController takeBloodPageController = loader.getController();
            takeBloodPageController.initialize_toggle();
            stageTheLabelBelongs.setTitle("Receiver's Page");
            stageTheLabelBelongs.setScene(new Scene(root, 700, 500));
            stageTheLabelBelongs.show();
        }
        else if(adminButton.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/Admin.fxml"));
            Parent root = (Parent) loader.load();
            Admin admin = loader.getController();
            admin.initialise_combo_box();
            stageTheLabelBelongs.setTitle("Admin's Page");
            stageTheLabelBelongs.setScene(new Scene(root, 700, 500));
            stageTheLabelBelongs.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Select A Option!",ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void initialise_toggle(){
        ToggleGroup g = new ToggleGroup();
        donateBlood.setToggleGroup(g);
        receiveBlood.setToggleGroup(g);
        adminButton.setToggleGroup(g);
    }
}
