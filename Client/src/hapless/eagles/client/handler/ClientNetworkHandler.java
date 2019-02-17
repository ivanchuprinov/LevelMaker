package hapless.eagles.client.handler;

import hapless.eagles.client.ClientGameController;
import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * Reads clientbound packets.
 * Created by Kneesnap on 2/16/2019.
 */
@AllArgsConstructor
public class ClientNetworkHandler extends SimpleChannelInboundHandler<ClientboundPacket> {
    private ClientGameController clientController;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Server Connection: Successful.");
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println("");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ClientboundPacket packet) throws Exception {
        System.out.println("Packet: " + packet);
        packet.handleIncomingPacket(clientController.getPacketHandler(), channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
