package hapless.eagles.client;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldView;
import hapless.eagles.common.packets.clientbound.IClientPacketHandler;
import hapless.eagles.common.ui.GameUIController;
import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.FXUtil;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Controls the game client-side.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
public class ClientGameController {
    private World world;
    private Player player;
    private AnchorPane clientRootPane;
    private IClientPacketHandler packetHandler; //TODO

    /**
     * Creates the gui.
     * @param primaryStage The stage to make the gui for.
     */
    @SneakyThrows
    public void makeGUI(Stage primaryStage) {
        this.world = new World(); //TODO
        world.load(new Config(new File("debug.cfg")));
        System.out.println("Loaded World Config.");

        GameUIController gameController = new GameUIController(world);
        this.clientRootPane = FXUtil.loadFXMLTemplate(primaryStage, FXUtil.CLIENT_INGAME_TEMPLATE, gameController);
        gameController.postSetup(primaryStage);
        primaryStage.show();
    }
}
