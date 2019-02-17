package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * You lose.
 * Created by Kneesnap on 2/17/19.
 */
public class PacketGameOver extends ClientboundPacket {
    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handleGameOver(context);
    }
}
