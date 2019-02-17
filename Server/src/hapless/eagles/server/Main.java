package hapless.eagles.server;

import hapless.eagles.common.utils.Config;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * The main server class.
 * Created by Kneesnap on 2/16/19.
 */
public class Main extends Application {
    private static ServerInstance serverInstance;

    public static void main(String[] args) {
        System.out.println("Server Hello.");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadServer();

        //TODO: Launch GUI.
    }

    private static void loadServer() throws IOException {
        Config config = new Config(new File("servers.cfg"));
        serverInstance = new ServerInstance(config);
        serverInstance.startServer();
    }
}
