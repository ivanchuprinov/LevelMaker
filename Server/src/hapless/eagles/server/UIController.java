package hapless.eagles.server;

import hapless.eagles.common.WorldView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Kneesnap on 2/17/19.
 */
@Getter
public class UIController implements Initializable {
    private ServerInstance instance;
    private WorldView worldView;

    @FXML private AnchorPane rootPane;

    public UIController(ServerInstance instance) {
        this.instance = instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        worldView = new WorldView(instance.getWorld());

        this.worldView.setWidth(1000);
        this.worldView.setHeight(1000);
        rootPane.getChildren().add(this.worldView);
        this.worldView.renderWorld();
    }
}
