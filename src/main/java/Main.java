import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import misc.users.UserMisc;
import misc.utility.ViewMisc;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image("images/PNG/appIcon-02.png"));
        primaryStage.setTitle("Meds");
        ViewMisc.setPrimaryStage(primaryStage);
        ViewMisc.showStage("/view/menuView/loginView.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }


}
