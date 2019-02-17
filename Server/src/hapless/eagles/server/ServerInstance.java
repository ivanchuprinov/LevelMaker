package hapless.eagles.server;

import hapless.eagles.common.World;
import hapless.eagles.common.WorldSector;
import hapless.eagles.common.utils.Config;
import hapless.eagles.server.network.ServerInitHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Map;

/**
 * Represents a game server instance.
 * Created by Kneesnap on 2/16/2019.
 */
public class ServerInstance {
    private Map<WorldSector, ChannelGroup> channelGroups;
    private World world;
    private int port;

    public ServerInstance(Config config) {
        this.load(config);
    }

    /**
     * Load this instance from a config.
     * @param config config
     */
    public void load(Config config) {
        this.port = config.getInt("port");
    }

    /**
     * Start listening for connections.
     */
    public void startServer() {
        EventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ServerInitHandler());

        bootstrap.bind(this.port).channel().closeFuture().addListener(evt -> {
            group.shutdownGracefully();
            System.out.println("Server on port " + this.port + " shutting down.");
        });

        System.out.println("Started server instance on port " + this.port + ".");
    }

}
