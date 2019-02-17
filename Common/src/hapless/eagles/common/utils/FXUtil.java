package hapless.eagles.common.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contains JavaFX utilities.
 * Created by Kneesnap on 2/16/19.
 */
public class FXUtil {
    public static final String CLIENT_INGAME_TEMPLATE = "client/game.fxml";
    public static final String CLIENT_LOSE_TEMPLATE = "client/lose.fxml";
    public static final String SERVER_VIEW_TEMPLATE = "server/view.fxml";
    public static final String CLIENT_WIN_TEMPLATE = "client/win.fxml";

    /**
     * Gets the FXMLLoader by its name.
     * @param template The template name.
     * @return loader
     */
    public static FXMLLoader getFXMLLoader(String template) {
        return new FXMLLoader(Utils.getResource(template));
    }

    /**
     * Load a FXML template as a new window. It will NOT show the window, this should be done separately.
     * @param template   The name of the template to load. Should not be user-controllable, as there is no path checking.
     * @param title      The title of the window to show.
     * @param controller Makes the window controller.
     */
    public static <T> Stage makeFXMLTemplateWindow(Stage parent, String template, String title, T controller) {
        Stage newStage = new Stage();
        loadFXMLTemplate(newStage, template, controller);
        newStage.setResizable(false);
        newStage.setTitle(title);

        if (parent != null) {
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.setAlwaysOnTop(true);
            newStage.initOwner(parent);
        }

        return newStage;
    }

    /**
     * Load a FXML template into a stage.
     * @param template   The name of the template to load. Should not be user-controllable, as there is no path checking.
     * @param controller Makes the window controller.
     * @return rootNode
     */
    public static <T, N extends Parent> N loadFXMLTemplate(Stage stage, String template, T controller) {
        FXMLLoader loader = getFXMLLoader(template);

        N rootNode;
        try {
            if (controller != null)
                loader.setController(controller);
            rootNode = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load FML Template: " + template + "!", ex);
        }

        // Add the node to the stage.
        if (stage.getScene() != null) {
            stage.getScene().setRoot(rootNode);
        } else {
            stage.setScene(new Scene(rootNode, stage.getWidth(), stage.getHeight()));
        }

        return rootNode;
    }
}
