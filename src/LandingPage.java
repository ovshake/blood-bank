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
    public RadioButton simpleQuery;
    public RadioButton complexQuery;

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
        else if(simpleQuery.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/SimpleQuery.fxml"));
            Parent root = (Parent) loader.load();
            SimpleQuery simpleQuery = loader.getController();
            simpleQuery.intialise_combo_box();
            stageTheLabelBelongs.setTitle("Simple Query");
            stageTheLabelBelongs.setScene(new Scene(root, 1000, 800));
            stageTheLabelBelongs.show();
        }
        else if(complexQuery.isSelected()){
            Stage stageTheLabelBelongs = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/ComplexQuery.fxml"));
            Parent root = (Parent) loader.load();
            ComplexQuery complexQuery = loader.getController();
            complexQuery.intialise_combo_box();
            stageTheLabelBelongs.setTitle("Complex Query");
            stageTheLabelBelongs.setScene(new Scene(root, 1000, 800));
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
        simpleQuery.setToggleGroup(g);
        complexQuery.setToggleGroup(g);
    }
}
