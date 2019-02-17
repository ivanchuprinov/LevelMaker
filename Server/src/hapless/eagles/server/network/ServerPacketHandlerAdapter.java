package hapless.eagles.server.network;

import hapless.eagles.common.packets.ServerboundPacket;
import hapless.eagles.server.ServerInstance;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Handles packets sent by the client
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@AllArgsConstructor
@ChannelHandler.Sharable
public class ServerPacketHandlerAdapter extends SimpleChannelInboundHandler<ServerboundPacket> {
    private ServerInstance instance;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ServerboundPacket serverboundPacket) throws Exception {
        serverboundPacket.handleIncomingPacket(instance.getPacketHandler(), channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        System.out.println(context.channel().remoteAddress() + " disconnected.");
    }
}
