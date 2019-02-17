package hapless.eagles.client;

import javafx.application.Application;
import javafx.stage.Stage;

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
        gameController = new ClientGameController(stage);
        gameController.connectToServer();
        gameController.runGameLoop();
    }
}
