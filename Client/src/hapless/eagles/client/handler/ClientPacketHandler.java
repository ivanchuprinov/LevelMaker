package hapless.eagles.client.handler;

import hapless.eagles.client.ClientGameController;
import hapless.eagles.common.packets.clientbound.*;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Handles clientbound packets.
 * TODO: Handle packets!
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@AllArgsConstructor
public class ClientPacketHandler implements IClientPacketHandler {
    private ClientGameController controller;

    @Override
    public void handleLoadWorld(ChannelHandlerContext context, PacketLoadWorld packet) {
        Platform.runLater(() -> controller.makeGUI(packet.getWorld()));
    }

    @Override
    public void handleSnakePosition(ChannelHandlerContext context, PacketSnakePosition packet) {

    }

    @Override
    public void handleWallState(ChannelHandlerContext context, PacketSetWallState packet) {

    }

    @Override
    public void handlePixelChange(ChannelHandlerContext context, PacketChangeMapPixel packet) {

    }

    @Override
    public void handleStartGame(ChannelHandlerContext context) {

    }
}
