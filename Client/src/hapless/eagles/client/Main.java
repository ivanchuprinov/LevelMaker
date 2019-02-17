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

    public static void main(String[] args) throws Exception {
        System.out.println("Client Start");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        World world = new World();
        world.load(new Config(new File("debug.cfg")));
        System.out.println("Loaded World Config.");

        GameUIController gameController = new GameUIController(world);
        FXUtil.loadFXMLTemplate(stage, FXUtil.CLIENT_INGAME_TEMPLATE, gameController);
        gameController.postSetup(stage);
        stage.show();
    }
}
