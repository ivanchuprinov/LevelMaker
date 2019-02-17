package hapless.eagles.server;

import hapless.eagles.common.Player;
import hapless.eagles.common.World;
import hapless.eagles.common.WorldPixel;
import hapless.eagles.common.WorldSector;
import hapless.eagles.common.packets.ClientboundPacket;
import hapless.eagles.common.packets.clientbound.PacketSetWallState;
import hapless.eagles.common.packets.clientbound.PacketSnakePosition;
import hapless.eagles.common.packets.clientbound.PacketStartGame;
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
        //TODO: Process packets?

        World world = instance.getWorld();
        for (WorldSector sector : world.getAllSectors()) {
            for (Player player : sector.getPlayers()) {
                WorldPixel newPos = player.move();

                // Check if player died.
                int xStart = sector.getWorldXStart();
                int yStart = sector.getWorldYStart();
                if (newPos.hasWall() || newPos.getX() < xStart || newPos.getX() >= xStart + world.getXSectorSize()
                        || newPos.getY() < yStart || newPos.getY() >= yStart + world.getYSectorSize()) {
                    killPlayer(player);
                    continue;
                }

                // Set Wall.
                newPos.setWallColorId(Utils.toRGB(Color.BLACK)); //TODO: COLOR.
                instance.getClients().writeAndFlush(new PacketSetWallState(newPos));
                sendPacket(player, new PacketSnakePosition(newPos.getX() - player.getSector().getWorldXStart(), newPos.getY() - player.getSector().getWorldYStart()));
            }


            // Start game if ready.
            if (sector.getPlayerQueue().size() >= instance.getMinPlayers()) {
                sector.getPlayers().addAll(sector.getPlayerQueue());

                PacketStartGame psg = new PacketStartGame();
                for (Player pl : sector.getPlayerQueue())
                    sendPacket(pl, psg);

                sector.getPlayerQueue().clear();
            }
        }
    }

    public void sendPacket(Player player, ClientboundPacket packet) {
        //TODO!!!
    }

    /**
     * Kill a player, and remove their snake parts.
     */
    public void killPlayer(Player player) {
        player.getSector().removePlayer(player);
        player.setSector(null);

        for (WorldPixel pixel : player.getTrail()) {
            pixel.setWallColorId(0);
            instance.getClients().writeAndFlush(new PacketSetWallState(pixel));
        }

        //TODO: Tell player they are dead.

        //TODO: End game if complete, let winner select tile.
    }
}
