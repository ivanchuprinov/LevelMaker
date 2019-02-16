package hapless.eagles.common;

import java.util.ArrayList;

/**
 * Created by chuprii on 2/16/19.
 */
public class Player {
    public static final int DIR_NORTH = 1;
    public static final int DIR_SOUTH = -1;
    public static final int DIR_EAST = 2;
    public static final int DIR_WEST = -2;

    private int boardID;
    private int playerNum;
    private int direction;
    private Coordinates head;
    private ArrayList<Coordinates> trail;


    /* Constructors */
    public Player(int x, int y, char dir) {
        head = new Coordinates(x, y);
        direction = dir;
        trail = new ArrayList<>();
    }

    /* Getters */
    public Coordinates getHead() {
        return head;
    }

    public int getDirection() {
        return direction;
    }

    public ArrayList<Coordinates> getTrail() {
        return trail;
    }

    public int getBoardID() {
        return boardID;
    }

    public int getPlayerNum() {
        return playerNum;
    }


    /* Setters */

    public void setBoardID(int newBID) {
        boardID = newBID;
    }


    public void setDirection(int newDir) {
        if (newDir != direction && newDir != -direction)
            direction = newDir;
    }

    public void setCoordinates(int x, int y) {
        head = new Coordinates(x, y);
    }

    public void setPlayerNum(int pn) {
        playerNum = pn;
    }

    public void setHead(Coordinates newHead) {
        this.head = newHead;
    }

    /* Add coordinates to trail */
    public void addToTrail(Coordinates c) {
        trail.add(c);
    }

    /* See if coordinates are in the trail */
    public boolean isInTrail(Coordinates c)
    {
        boolean retVal = false;

        for(int i = 0; i<trail.size(); i++)
        {
            retVal = trail.get(i).isA(c);
            if(retVal)
                break;
        }

        return retVal;
    }

    public boolean isInTrail(int x, int y)
    {
        boolean retVal = false;

        for(int i = 0; i<trail.size(); i++)
        {
            retVal = trail.get(i).isA(x,y);
            if(retVal)
                break;
        }

        return retVal;
    }

    /* Move:
     * Take a step in the direction player is currently facing
     */
    public void move()
    {
        int newX = head.getX();
        int newY = head.getY();

        addToTrail(head);

        switch(direction)
        {
            case DIR_NORTH:
                newY++;
                break;
            case DIR_SOUTH:
                newY--;
                break;
            case DIR_EAST:
                newX++;
                break;
            case DIR_WEST:
                newX--;
                break;
        }

        setHead(new Coordinates(newX, newY));
    }
}

class Coordinates
{
    private int x;
    private int y;

    public Coordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX(){ return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean isA(Coordinates c)
    {
        if(this.x == c.x && this.y == c.y)
            return true;
        else
            return false;
    }

    public boolean isA(int x, int y)
    {
        if(this.x == x && this.y == y)
            return true;
        else
            return false;
    }
}