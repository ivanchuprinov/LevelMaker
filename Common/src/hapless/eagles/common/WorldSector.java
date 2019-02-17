package hapless.eagles.common;

import lombok.Getter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents a section of the world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldSector implements Serializable {
    private World world;
    private int x;
    private int y;
    private CopyOnWriteArrayList<Player> players = new CopyOnWriteArrayList<>();
    private Queue<Player> playerQueue = new LinkedList<>();

    public WorldSector(World parentWorld, int x, int y) {
        this.world = parentWorld;
        this.x = x;
        this.y = y;
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
     * Get the world x coordinate this sector ends at.
     * @return xStart
     */
    public int getWorldXEnd() {
        return getWorldXStart() + getWorld().getXSectorSize();
    }

    /**
     * Get the world y coordinate this sector starts at.
     * @return yStart
     */
    public int getWorldYStart() {
        return (world.getYSectorSize() * getY());
    }

    /**
     * Get the world y coordinate this sector ends at.
     * @return yEnd
     */
    public int getWorldYEnd() {
        return getWorldYStart() + getWorld().getYSectorSize();
    }
}
