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

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    private World world;
    private int playerCnt;



    public WorldSector(World parentWorld, int x, int y) {
        this.world = parentWorld;
        this.x = x;
        this.y = y;
        playerCnt = 0;
    }

    /**
     * Get player count.
     * @return number of players in the sector.
     */
    public int getPlayerCnt() {return playerCnt;}

    /**
     * Add a player to the list.
     */
    public void addPlayer(){
        ++playerCnt;
    }



    /**
     * Remove a player from the list.
     */
    public void removePlayer()
    {
        --playerCnt;
    }



    /**
     * Gets a pixel in this sector.
     * @param localX The sector x.
     * @param localY The sector y.
     * @return pixel
     */
    public WorldPixel getPixel(int localX, int localY) {
        return world.getPixel((world.getXSectorSize() * getX()) + localX, (world.getYSectorSize() * getY()) + localY);
    }

}
