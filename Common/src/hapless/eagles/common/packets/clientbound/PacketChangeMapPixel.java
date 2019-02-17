package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Broadcast a pixel change.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketChangeMapPixel extends ClientboundPacket {
    private int worldX;
    private int worldY;
    private int newColor;

    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handlePixelChange(context, this);
    }
}
