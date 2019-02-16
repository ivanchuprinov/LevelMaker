package hapless.eagles.common.ui;

import hapless.eagles.common.World;
import hapless.eagles.common.WorldView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Manages the in-game interface.
 * Created by Kneesnap on 2/16/2019.
 */
public class GameController implements Initializable {
    private World world;

    @FXML private AnchorPane rootPane;
    private WorldView worldView;
    private Stage parentStage;

    public GameController(World world, Stage parentStage) {
        this.world = world;
        this.parentStage = parentStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label temp = new Label("This is a test."); //TODO: Remove
        AnchorPane.setLeftAnchor(temp, 1D);
        AnchorPane.setBottomAnchor(temp, 1D);
        rootPane.getChildren().add(temp);

        this.worldView = new WorldView(world);

        parentStage.widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.worldView.setWidth(newValue.doubleValue());
            this.worldView.renderWorld();
        }));

        parentStage.heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.worldView.setHeight(newValue.doubleValue());
            this.worldView.renderWorld();
        }));

        rootPane.getChildren().add(this.worldView);
        this.worldView.renderWorld();

        rootPane.setOnKeyPressed(evt -> {
            world.randomizeBoard();
            this.worldView.renderWorld();
        });
    }
}
