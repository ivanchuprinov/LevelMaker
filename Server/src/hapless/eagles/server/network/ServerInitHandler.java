package hapless.eagles.server.network;

import hapless.eagles.common.Player;
import hapless.eagles.common.packets.NetworkUtil;
import hapless.eagles.common.packets.ServerboundPacket;
import hapless.eagles.common.packets.clientbound.PacketLoadWorld;
import hapless.eagles.server.ServerInstance;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.AllArgsConstructor;

/**
 * The first channel handler to process data from a connecting client. Still WIP
 * Created by Kneesnap on 2/16/2019.
 */
@ChannelHandler.Sharable
@AllArgsConstructor
public class ServerInitHandler extends ChannelInboundHandlerAdapter {
    private ServerInstance instance;

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress().toString() + " connected.");
        ctx.pipeline().remove(this);
        instance.getClients().add(ctx.channel());

        Player player = new Player(instance.getWorld());
        player.setChannel(ctx.channel());
        ctx.channel().attr(ServerboundPacket.PLAYER).set(player);

        NetworkUtil.setup(ctx.pipeline(), new DefaultEventExecutorGroup(10), new ServerPacketHandlerAdapter(instance));
        ctx.channel().writeAndFlush(new PacketLoadWorld(instance.getWorld()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
