package hapless.eagles.common;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a section of the world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldSector implements Serializable {
    @Getter
    private static World world; // TODO: This shouldn't be static.
    private int x;
    private int y;
    private ArrayList<Player> players = new ArrayList<>();
    private Queue<Player> playerQueue = new LinkedList<>();
    public boolean gameReady = false;

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
        if(!gameReady)
            playerQueue.add(p);
        else
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
     * Removes all players from the list.
     */
    public void removeAllPlayers()
    {
        while(players.size() != 0)
        {
            players.remove(0);
        }
    }

    /**
     * Loads the next batch of players from the queue.
     */
    public void loadQueuedPlayers()
    {
        for(int i = 0; i<4; i++)
        {
            if(!playerQueue.isEmpty())
                players.add(playerQueue.remove());
        }
        if(players.size() == 4) {
            gameReady = true;
        }
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
