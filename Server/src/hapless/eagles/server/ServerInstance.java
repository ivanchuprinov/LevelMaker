package hapless.eagles.server;

import hapless.eagles.common.World;
import hapless.eagles.common.packets.serverbound.IServerPacketHandler;
import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.FXUtil;
import hapless.eagles.server.network.ServerInitHandler;
import hapless.eagles.server.network.ServerPacketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.DefaultEventExecutor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;

/**
 * Represents a game server instance.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
public class ServerInstance {
    private ChannelGroup clients;
    private IServerPacketHandler packetHandler;
    private ServerGameController gameController;
    private UIController uiController;
    private World world;
    private String name;
    private int port;
    private int minPlayers;

    public ServerInstance(Config config) {
        this.load(config);
    }

    /**
     * Load this instance from a config.
     * @param config config
     */
    public void load(Config config) {
        this.name = config.getString("name");
        this.port = config.getInt("port");
        this.minPlayers = config.getInt("requiredPlayers");

        // Load the world from the same config.
        this.world = new World(config);
        this.gameController = new ServerGameController(this);
    }

    /**
     * Start listening for connections.
     */
    public void startServer() {
        EventLoopGroup group = new NioEventLoopGroup();
        clients = new DefaultChannelGroup(new DefaultEventExecutor());

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ObjectEncoder())
                .childHandler(new ObjectDecoder(ClassResolvers.weakCachingResolver(getClass().getClassLoader())))
                .childHandler(new ServerInitHandler(this));

        this.packetHandler = new ServerPacketHandler(this);
        bootstrap.bind(this.port).channel().closeFuture().addListener(evt -> {
            group.shutdownGracefully();
            System.out.println(getName() + " is shutting down.");
        });

        System.out.println(getName() + " started on port " + getPort() + ".");
    }

    /**
     * Sets up the game loop.
     */
    public void startGameLoop() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> getGameController().runServerTick()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Setup the MapViewer GUI.
     * @param stage stage
     */
    public void startGUI(Stage stage) {
        stage.setResizable(false);
        FXUtil.loadFXMLTemplate(stage, FXUtil.SERVER_VIEW_TEMPLATE, this.uiController = new UIController(this));
        stage.show();
    }
}
