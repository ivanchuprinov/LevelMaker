package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldPixel;
import hapless.eagles.common.WorldSector;
import hapless.eagles.common.packets.ClientboundPacket;
import hapless.eagles.common.packets.clientbound.*;
import hapless.eagles.common.utils.Utils;
import javafx.scene.paint.Color;

/**
 * Performs server-side game operations.
 */
public class ServerGameController {
    private ServerInstance instance;

    public ServerGameController(ServerInstance instance) {
        this.instance = instance;
    }

    /**
     * Called at an interval, a server tick runs all server update logic.
     */
    public void runServerTick() {
        if (instance.getUiController() != null)
            instance.getUiController().getWorldView().renderWorld();

        World world = instance.getWorld();
        for (WorldSector sector : world.getAllSectors()) {
            for (Player player : sector.getPlayers()) {
                WorldPixel newPos = player.move();

                // Check if player died.
                if (newPos == null || newPos.hasWall()) {
                    killPlayer(player);
                    continue;
                }

                // Set Wall.
                newPos.setWallColorId(Utils.toRGB(Color.BLACK)); //TODO: COLOR.
                instance.getClients().writeAndFlush(new PacketSetWallState(newPos));
                sendPacket(player, new PacketSnakePosition(newPos.getX(), newPos.getY()));
            }


            // Start game if ready.
            if (sector.getPlayers().isEmpty() && sector.getPlayerQueue().size() >= instance.getMinPlayers()) {
                sector.getPlayers().addAll(sector.getPlayerQueue());

                PacketStartGame psg = new PacketStartGame();
                for (Player pl : sector.getPlayerQueue())
                    sendPacket(pl, psg);

                sector.getPlayerQueue().clear();
            }
        }
    }

    /**
     * Send a packet to a player.
     * @param player The player to send the packet to.
     * @param packet The packet to send.
     */
    public void sendPacket(Player player, ClientboundPacket packet) {
        player.getChannel().writeAndFlush(packet);
    }

    /**
     * Kill a player, and remove their snake parts.
     */
    public void killPlayer(Player player) {
        WorldSector sector = player.getSector();
        sector.removePlayer(player);
        player.setSector(null);

        for (WorldPixel pixel : player.getTrail()) {
            pixel.setWallColorId(-1);
            instance.getClients().writeAndFlush(new PacketSetWallState(pixel));
        }

        sendPacket(player, new PacketGameOver());

        // Declare Winner!
        if (sector.getPlayers().size() == 1) {
            Player winner = sector.getPlayers().remove(0);
            winner.setSector(null);
            sendPacket(winner, new PacketWinGame(sector));
        }

    }
}
