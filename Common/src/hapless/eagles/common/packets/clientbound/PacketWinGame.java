package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.WorldSector;
import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * Handles winning the game.
 * TODO
 * Created by Kneesnap on 2/17/19.
 */
public class PacketWinGame extends ClientboundPacket {

    public PacketWinGame(WorldSector sector) {
        //TODO
    }

    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handleWin(context, this);
    }
}
