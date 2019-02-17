package hapless.eagles.client.handler;

import hapless.eagles.client.ClientGameController;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Kneesnap on 2/16/19.
 */
public class ClientGameHandler extends SimpleChannelInboundHandler<String> {
    private ClientGameController clientController;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Server Connection: Successful.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String packet) throws Exception {
        System.out.println("Packet: " + packet);
        //packet.handleIncomingPacket(clientController.getPacketHandler(), channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
