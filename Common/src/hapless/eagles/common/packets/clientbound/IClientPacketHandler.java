package hapless.eagles.common.packets.clientbound;

import io.netty.channel.ChannelHandlerContext;

/**
 * Handles packets the client receives.
 * Created by Kneesnap on 2/16/2019.
 */
public interface IClientPacketHandler {

    void handleLoadWorld(ChannelHandlerContext context, PacketLoadWorld packet);

    void handleSnakePosition(ChannelHandlerContext context, PacketSnakePosition packet);

    void handleWallState(ChannelHandlerContext context, PacketSetWallState packet);

    void handlePixelChange(ChannelHandlerContext context, PacketChangeMapPixel packet);

    void handleStartGame(ChannelHandlerContext context);
}
