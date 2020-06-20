package misc.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewMisc {

    private static Stage primaryStage;
    private static Parent rootLayout;
    private static double xOffset = 0;
    private static double yOffset = 0;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        ViewMisc.primaryStage = primaryStage;
    }

    public Parent getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(Pane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public static void makeStageMove() {
        rootLayout.setOnMousePressed(e -> {
            xOffset = ViewMisc.getPrimaryStage().getX() - e.getScreenX();
            yOffset = ViewMisc.getPrimaryStage().getY() - e.getScreenY();
        });
        rootLayout.setOnMouseDragged(e -> {
            ViewMisc.getPrimaryStage().setX(e.getScreenX() + xOffset);
            ViewMisc.getPrimaryStage().setY(e.getScreenY() + yOffset);
        });
    }

    public static void showStage(String address) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewMisc.class.getResource(address));
            rootLayout = (Parent)loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLogin() {
        ViewMisc.showStage("/view/entry/loginView.fxml");
    }

    public static void showRegister() {
        ViewMisc.showStage("/view/entry/registerView.fxml");
    }

    public static void showPasswordReset() {
        ViewMisc.showStage("/view/other/passwordResetView.fxml");
    }

    public static void showPatientMenu() {
        ViewMisc.showStage("/view/account/menus/patientMenuView.fxml");
    }

    public static void showPatientSettings() {
        ViewMisc.showStage("/view/account/settings/patientAccSettingsView.fxml");
    }

    public static void showRequestHelp() {
        ViewMisc.showStage("/view/other/requestHelpView.fxml");
    }

    public static void showDoctorMenu() {
        ViewMisc.showStage("/view/account/menus/doctorMenuView.fxml");
    }

    public static void showDoctorSettings() {
        ViewMisc.showStage("/view/account/settings/docAccSettingsView.fxml");
    }

}
