package hapless.eagles.server.network;

import hapless.eagles.common.Player;
import hapless.eagles.common.packets.clientbound.PacketChangeMapPixel;
import hapless.eagles.common.packets.serverbound.IServerPacketHandler;
import hapless.eagles.common.packets.serverbound.PacketChangePixel;
import hapless.eagles.common.packets.serverbound.PacketChooseSector;
import hapless.eagles.common.packets.serverbound.PacketSetDirection;
import hapless.eagles.server.ServerInstance;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Handles serverbound packets.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@AllArgsConstructor
public class ServerPacketHandler implements IServerPacketHandler {
    private ServerInstance instance;

    @Override
    public void handleSetDirection(ChannelHandlerContext ctx, Player player, PacketSetDirection packet) {
        if (packet.getDirection() != player.getDirection().getOpposite())
            player.setDirection(packet.getDirection());
    }

    @Override
    public void handleChangePixel(ChannelHandlerContext context, Player player, PacketChangePixel packet) {
        // If we had more time, we might actually put in cheating prevention, but we're running short on time.
        player.getSector().getPixel(packet.getLocalX(), packet.getLocalY()).setColorId(packet.getNewColor());
        instance.getClients().writeAndFlush(new PacketChangeMapPixel(player.getSector().getWorldXStart() + packet.getLocalX(), player.getSector().getWorldYStart() + packet.getLocalY(), packet.getNewColor()));
    }

    @Override
    public void handleChooseSector(ChannelHandlerContext context, Player player, PacketChooseSector packet) {
        player.setSector(instance.getWorld().getSector(packet.getSectorX(), packet.getSectorY()));
        player.getSector().getPlayerQueue().add(player);
    }
}
