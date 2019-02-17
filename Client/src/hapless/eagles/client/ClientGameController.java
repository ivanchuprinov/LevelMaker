package hapless.eagles.client;

import hapless.eagles.client.handler.ClientInitHandler;
import hapless.eagles.client.handler.ClientPacketHandler;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldView;
import hapless.eagles.common.packets.clientbound.IClientPacketHandler;
import hapless.eagles.common.ui.GameUIController;
import hapless.eagles.common.utils.FXUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

/**
 * Controls the game client-side.
 * TODO: Don't forget about the bot release.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
public class ClientGameController {
    @Setter private World world;
    private WorldView worldView;
    private IClientPacketHandler packetHandler;
    private Channel channel;
    private Stage mainStage;

    public ClientGameController(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * Creates the gui with a world.
     */
    public void makeGUI() {
        GameUIController gameController = new GameUIController(getWorld());
        AnchorPane root = FXUtil.loadFXMLTemplate(mainStage, FXUtil.CLIENT_INGAME_TEMPLATE, gameController);
        this.worldView = (WorldView) root.getChildren().get(0); // messy, but w/e.
        gameController.postSetup(mainStage);
        mainStage.show();
    }

    /**
     * Connects to the server.
     */
    public void connectToServer() {
        final String SERVER_IP = "127.0.0.1"; //TODO: CONFIG
        final int PORT = 10000;

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap clientBootstrap = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .handler(new ClientInitHandler(this));

        this.packetHandler = new ClientPacketHandler(this);
        this.channel = clientBootstrap.connect(SERVER_IP, PORT).channel();
        this.channel.closeFuture().addListener(evt -> {
            System.out.println("Your connection was unexpectedly terminated.");
            group.shutdownGracefully();
            System.exit(0);
        });
    }

    /**
     * Runs the game loop.
     */
    public void runGameLoop() {
        System.out.println("Game Loop has been called.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), evt -> {
            if (worldView != null)
                worldView.renderWorld();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
