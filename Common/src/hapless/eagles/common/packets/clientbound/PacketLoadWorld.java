package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.packets.ClientboundPacket;
import hapless.eagles.common.packets.ServerboundPacket;
import hapless.eagles.common.packets.serverbound.IServerPacketHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Gives the client world information.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketLoadWorld extends ClientboundPacket {
    private World world;

    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handleLoadWorld(context, this);
    }
}
