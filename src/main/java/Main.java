import javafx.application.Application;
import javafx.stage.Stage;
import misc.users.UserMisc;
import misc.utility.ViewMisc;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewMisc.setPrimaryStage(primaryStage);
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }


}
