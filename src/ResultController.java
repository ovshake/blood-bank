import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    public Label finalResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setLabelText(String text){
        finalResult.setText(text);
    }
}
