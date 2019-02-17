package hapless.eagles.common.packets.serverbound;

import hapless.eagles.common.Player;
import hapless.eagles.common.packets.ServerboundPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Allows the winner to change a pixel.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketChangePixel extends ServerboundPacket {
    private int localX;
    private int localY;
    private int newColor;

    @Override
    protected void handleIncomingPacket(IServerPacketHandler handler, ChannelHandlerContext context, Player player) {
        handler.handleChangePixel(context, player, this);
    }
}
