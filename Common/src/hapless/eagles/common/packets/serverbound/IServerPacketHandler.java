package hapless.eagles.common.packets.serverbound;

import hapless.eagles.common.Player;
import io.netty.channel.ChannelHandlerContext;

/**
 * Handles received packets.
 * Created by Kneesnap on 2/16/2019.
 */
public interface IServerPacketHandler {

    void handleSetDirection(ChannelHandlerContext ctx, Player player, PacketSetDirection packet);

    void handleChangePixel(ChannelHandlerContext context, Player player, PacketChangePixel pixel);

    void handleChooseSector(ChannelHandlerContext context, Player player, PacketChooseSector sector);
}