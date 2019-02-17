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
    private static World world;
    private int playerCount;

    public WorldSector(World parentWorld, int x, int y) {
        world = parentWorld;
        this.x = x;
        this.y = y;
    }

    /**
     * Add a player to the list.
     */
    public void addPlayer(){
        ++playerCount;
    }

    /**
     * Remove a player from the list.
     */
    public void removePlayer() {
        --playerCount;
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

    public static World getWorld() {
        return world;
    }
}
