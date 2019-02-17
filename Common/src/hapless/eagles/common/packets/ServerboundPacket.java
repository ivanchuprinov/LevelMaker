package hapless.eagles.common.packets;

import hapless.eagles.common.Player;
import hapless.eagles.common.packets.serverbound.IServerPacketHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * A network packet sent from the client to the server.
 * Created by Kneesnap on 2/16/2019.
 */
public abstract class ServerboundPacket {
    public static final AttributeKey<Player> PLAYER = AttributeKey.newInstance("PLAYER");

    /**
     * Handles an incoming packet.
     * @param handler The packet handler to handle the packet with.
     * @param context The channel handler context.
     */
    public void handleIncomingPacket(IServerPacketHandler handler, ChannelHandlerContext context) {
        handleIncomingPacket(handler, context, context.channel().attr(PLAYER).get());
    }

    /**
     * Packet-specific packet handling.
     */
    protected abstract void handleIncomingPacket(IServerPacketHandler handler, ChannelHandlerContext context, Player player);
}
