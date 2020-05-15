import javafx.application.Application;
import javafx.stage.Stage;
import misc.users.UserMisc;
import misc.utility.FileMisc;
import misc.utility.ViewMisc;

import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        UserMisc.readUsers();
        ViewMisc.setPrimaryStage(primaryStage);
        ViewMisc.getPrimaryStage().setTitle("Meds Login");
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }


}
