package hapless.eagles.common.packets.serverbound;

import hapless.eagles.common.MoveDirection;
import hapless.eagles.common.Player;
import hapless.eagles.common.packets.ServerboundPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Set the direction the player is moving.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketSetDirection extends ServerboundPacket {
    private MoveDirection direction;

    @Override
    protected void handleIncomingPacket(IServerPacketHandler handler, ChannelHandlerContext context, Player player) {
        handler.handleSetDirection(context, player, this);
    }
}
