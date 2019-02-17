package hapless.eagles.client;

import hapless.eagles.client.handler.ClientInitHandler;
import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.packets.clientbound.IClientPacketHandler;
import hapless.eagles.common.ui.GameUIController;
import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.FXUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Controls the game client-side.
 * TODO: Don't forget about the bot release.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
public class ClientGameController {
    private World world;
    private Player player;
    private AnchorPane clientRootPane;
    private IClientPacketHandler packetHandler; //TODO
    private Channel channel;

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
        //TODO: Game loop.
    }
}
