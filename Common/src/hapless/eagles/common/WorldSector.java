package hapless.eagles.common;

import lombok.Getter;

import java.util.ArrayList;

/**
 * Represents a section of the world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldSector {
    private int x;
    private int y;
    private ArrayList<Player> players;

    @Getter private static World world; // TODO: This shouldn't be static.

    public WorldSector(World parentWorld, int x, int y) {
        world = parentWorld;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the amount of players in this sector.
     * @return playerCount
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Add a player to the list.
     */
    public void addPlayer(Player p) {
        players.add(p);
    }

    /**
     * Remove a player from the list.
     */
    public void removePlayer(Player p) {
        if (!players.remove(p))
            throw new RuntimeException("Didn't find player to be removed.");
    }

    /**
     * Gets a pixel in this sector.
     * @param localX The sector x.
     * @param localY The sector y.
     * @return pixel
     */
    public WorldPixel getPixel(int localX, int localY) {
        return world.getPixel(getWorldXStart() + localX, getWorldYStart() + localY);
    }

    /**
     * Get the world x coordinate this sector starts at.
     * @return xStart
     */
    public int getWorldXStart() {
        return (world.getXSectorSize() * getX());
    }

    /**
     * Get the world y coordinate this sector starts at.
     * @return yStart
     */
    public int getWorldYStart() {
        return (world.getYSectorSize() * getY());
    }
}
