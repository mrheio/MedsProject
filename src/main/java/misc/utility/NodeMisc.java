package misc.utility;

import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import model.roles.Doctor;

import javax.swing.text.TableView;
import java.util.List;

public class NodeMisc {

    public static void hideNode(Node... nodes) {
        for (Node x: nodes) {
            x.setVisible(false);
        }
    }

    public static void showNode(Node... nodes) {
        for (Node x: nodes) {
            x.setVisible(true);
        }
    }

    public static void hideShowNode(List<Node> hide, List<Node> show) {
        for (Node x: hide) {
            x.setVisible(false);
        }
        for (Node x: show) {
            x.setVisible(true);
        }
    }

    public static void disableNode(Node... nodes) {
        for (Node x: nodes) {
            x.setDisable(true);
        }
    }

    public static void enableNode(Node... nodes) {
        for (Node x: nodes) {
            x.setDisable(false);
        }
    }

    public static void disableEnableNode(List<Node> disable, List<Node> enable) {
        for (Node x: disable) {
            x.setDisable(true);
        }
        for (Node x: enable) {
            x.setDisable(false);
        }
    }

    public static void hideDisableNode(List<Node> hide, List<Node> disable) {
        for (Node x: hide) {
            x.setVisible(false);
        }
        for (Node x: disable) {
            x.setDisable(true);
        }
    }

    public static void hideEnableNode(List<Node> hide, List<Node> enable) {
        for (Node x: hide) {
            x.setVisible(false);
        }
        for (Node x: enable) {
            x.setDisable(false);
        }
    }

    public static void showDisableNode(List<Node> show, List<Node> disable) {
        for (Node x: show) {
            x.setVisible(true);
        }
        for (Node x: disable) {
            x.setDisable(true);
        }
    }

    public static void showEnableNode(List<Node> show, List<Node> enable) {
        for (Node x: show) {
            x.setVisible(true);
        }
        for (Node x: enable) {
            x.setDisable(false);
        }
    }
}
