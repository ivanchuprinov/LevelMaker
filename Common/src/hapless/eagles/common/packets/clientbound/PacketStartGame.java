package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * Starts the game.
 * Created by Kneesnap on 2/16/2019.
 */
public class PacketStartGame extends ClientboundPacket {
    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handleStartGame(context);
    }
}
