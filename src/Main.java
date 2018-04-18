import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout/LandingPage.fxml"));
        Parent root = (Parent) loader.load();
        LandingPage landingPage = loader.getController();
        landingPage.initialise_toggle();
        primaryStage.setTitle("Hello! Welcome!");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
