package hapless.eagles.client;

import hapless.eagles.common.World;
import hapless.eagles.common.ui.GameUIController;
import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.FXUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

/**
 * The main client class.
 * Created by Kneesnap on 2/16/19.
 */
public class Main extends Application {
    private static ClientGameController gameController;

    public static void main(String[] args) throws Exception {
        System.out.println("Client Start");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameController = new ClientGameController();
        gameController.makeGUI(stage);
    }
}
