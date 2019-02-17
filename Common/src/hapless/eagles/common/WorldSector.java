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
    private ArrayList<Player> players;



    public WorldSector(World parentWorld, int x, int y) {
        this.world = parentWorld;
        this.x = x;
        this.y = y;
        players = new ArrayList<>();
    }

    /**
     * Add a player to the list.
     * @param p The player to be added.
     */
    public void addPlayer(Player p){
        players.add(p);
    }

    /**
     * Remove a player from the list.
     * @param p The player to be removed.
     */
    public void removePlayer(Player p)
    {
        players.remove(p);
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
