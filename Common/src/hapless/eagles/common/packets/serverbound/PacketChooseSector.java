package hapless.eagles.common.packets.serverbound;

import hapless.eagles.common.Player;
import hapless.eagles.common.packets.ServerboundPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Allows the player to choose a world sector.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketChooseSector extends ServerboundPacket {
    private int sectorX;
    private int sectorY;

    @Override
    protected void handleIncomingPacket(IServerPacketHandler handler, ChannelHandlerContext context, Player player) {
        handler.handleChooseSector(context, player, this);
    }
}
