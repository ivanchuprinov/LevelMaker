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
    private World world;
    private int numPlayers;


    public WorldSector(World parentWorld, int x, int y) {
        this.world = parentWorld;
        this.x = x;
        this.y = y;
        numPlayers = 0;
    }

    /**
     * Add a player to the list.
     * @param p The player to be added.
     */
    public void addPlayer(){
        ++numPlayers;
    }

    public void setCoord() {

    }

    /**
     * Remove a player from the list.
     * @param p The player to be removed.
     */
    public void removePlayer(Player p)
    {
        --numPlayers;
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
