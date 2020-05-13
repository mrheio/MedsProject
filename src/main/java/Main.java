import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import misc.utility.ViewMisc;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewMisc.setPrimaryStage(primaryStage);
        ViewMisc.getPrimaryStage().setTitle("Meds Login");
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
