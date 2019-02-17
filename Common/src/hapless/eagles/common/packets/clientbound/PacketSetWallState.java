package hapless.eagles.common.packets.clientbound;

import hapless.eagles.common.WorldPixel;
import hapless.eagles.common.packets.ClientboundPacket;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Sets whether a wall is at a certain position or not.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PacketSetWallState extends ClientboundPacket {
    private int worldX;
    private int worldY;
    private int colorId;

    public PacketSetWallState(WorldPixel pixel) {
        this(pixel.getX(), pixel.getY(), pixel.getWallColorId());
    }

    @Override
    public void handleIncomingPacket(IClientPacketHandler handler, ChannelHandlerContext context) {
        handler.handleWallState(context, this);
    }
}
