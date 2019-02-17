package hapless.eagles.client.handler;

import hapless.eagles.client.ClientGameController;
import hapless.eagles.client.LoseController;
import hapless.eagles.common.packets.clientbound.*;
import hapless.eagles.common.packets.serverbound.PacketChooseSector;
import hapless.eagles.common.utils.FXUtil;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Handles clientbound packets.
 * Created by Kneesnap on 2/16/2019.
 */
@Getter
@AllArgsConstructor
public class ClientPacketHandler implements IClientPacketHandler {
    private ClientGameController controller;

    @Override
    public void handleLoadWorld(ChannelHandlerContext context, PacketLoadWorld packet) {
        controller.setWorld(packet.getWorld());
        context.writeAndFlush(new PacketChooseSector(0, 0)); //TODO: Allow selecting sector.
        System.out.println("Waiting for enough players...");
    }

    @Override
    public void handleSnakePosition(ChannelHandlerContext context, PacketSnakePosition packet) {
        getController().getWorldView().updatePosition(packet.getWorldX(), packet.getWorldY());
    }

    @Override
    public void handleWallState(ChannelHandlerContext context, PacketSetWallState packet) {
        getController().getWorld().getPixel(packet.getWorldX(), packet.getWorldY()).setWallColorId(packet.getColorId());
    }

    @Override
    public void handlePixelChange(ChannelHandlerContext context, PacketChangeMapPixel packet) {
        getController().getWorld().getPixel(packet.getWorldX(), packet.getWorldY()).setColorId(packet.getNewColor());
    }

    @Override
    public void handleStartGame(ChannelHandlerContext context) {
        Platform.runLater(controller::makeGUI);
    }

    @Override
    public void handleGameOver(ChannelHandlerContext context) {
        Platform.runLater(() ->
                FXUtil.makeFXMLTemplateWindow(null, FXUtil.CLIENT_LOSE_TEMPLATE, "Game Over", new LoseController()).showAndWait());
        //TODO
    }

    @Override
    public void handleWin(ChannelHandlerContext context, PacketWinGame packet) {
        //TODO
    }
}
