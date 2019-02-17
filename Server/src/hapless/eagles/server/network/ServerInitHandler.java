package hapless.eagles.server.network;

import hapless.eagles.common.packets.clientbound.PacketLoadWorld;
import hapless.eagles.server.ServerInstance;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
        //TODO: Let the user pick the match to join. For now it's random.
        System.out.println(ctx.channel().remoteAddress().toString() + " connected.");
        ctx.writeAndFlush(new PacketLoadWorld(instance.getWorld()));

        /*
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        ctx.writeAndFlush(time).addListener((ChannelFutureListener) future -> ctx.close());
        */
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
