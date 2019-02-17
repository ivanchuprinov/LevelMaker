package hapless.eagles.common.packets;

import hapless.eagles.common.packets.clientbound.IClientPacketHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * A packet sent from the server to the client.
 * Created by Kneesnap on 2/16/2019.
 */
public abstract class ClientboundPacket {

    /**
     * Handles an incoming packet.
     * @param handler The packet handler to consumer the packet.
     * @param context The channel handler context.
     */
    public abstract void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context);
}
