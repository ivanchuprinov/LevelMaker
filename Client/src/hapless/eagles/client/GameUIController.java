package hapless.eagles.client;

import hapless.eagles.common.MoveDirection;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldView;
import hapless.eagles.common.packets.serverbound.PacketSetDirection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Manages the in-game interface.
 * Created by Kneesnap on 2/16/2019.
 */
public class GameUIController implements Initializable {
    private ClientGameController clientController;
    private World world;

    @FXML private AnchorPane rootPane;
    private WorldView worldView;

    public GameUIController(ClientGameController clientController) {
        this.clientController = clientController;
        this.world = clientController.getWorld();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.worldView = new WorldView(world);
        rootPane.getChildren().add(this.worldView);
        this.worldView.renderWorld();

        this.worldView.setOnKeyPressed(evt -> {
            for (MoveDirection direction : MoveDirection.values()) {
                if (direction.getKeyCode() != evt.getCode())
                    continue;

                clientController.getChannel().writeAndFlush(new PacketSetDirection(direction));
                this.worldView.renderWorld();
                return;
            }
        });
    }

    /**
     * Called after the stage is setup.
     * @param stage The stage setup.
     */
    public void postSetup(Stage stage) {
        stage.getScene().widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.worldView.setWidth(newValue.doubleValue());
            this.worldView.renderWorld();
        }));

        stage.getScene().heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.worldView.setHeight(newValue.doubleValue());
            this.worldView.renderWorld();
        }));
    }
}
