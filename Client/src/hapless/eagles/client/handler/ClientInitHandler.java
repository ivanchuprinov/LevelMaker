package hapless.eagles.client.handler;

import hapless.eagles.client.ClientGameController;
import hapless.eagles.common.packets.ClientboundPacket;
import hapless.eagles.common.packets.NetworkUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * Reads clientbound packets.
 * Created by Kneesnap on 2/16/2019.
 */
@AllArgsConstructor
public class ClientInitHandler extends SimpleChannelInboundHandler {
    private ClientGameController clientController;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Server Connection: Successful.");
        ctx.pipeline().remove(this);
        NetworkUtil.setup(ctx.pipeline(), new DefaultEventLoop(), new ClientGameHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("Received Packet?");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
