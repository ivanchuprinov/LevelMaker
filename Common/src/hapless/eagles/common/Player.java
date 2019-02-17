package hapless.eagles.common;

import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by chuprii on 2/16/19.
 */
@Getter
public class Player {
    public static final int DIR_NORTH = 1;
    public static final int DIR_SOUTH = -1;
    public static final int DIR_EAST = 2;
    public static final int DIR_WEST = -2;

    @Setter private WorldSector sector;
    private int sectorID;
    private int playerID;
    private int direction;
    private World world;
    private WorldPixel head;
    // private ArrayList<WorldPixel> trail;
    private ObservableList trail;
    private Canvas canvas;
    private Path path;
    private boolean alive = true;


    /* Constructors */
    public Player(int ID, WorldSector sector) {
        int playerNum = sector.getPlayerCount();
        playerID = ID;
        world = WorldSector.getWorld();
        // trail = new ArrayList<>();
        path = new Path();
        trail = path.getElements();
        path.setStrokeWidth(10);
        path.setStrokeLineCap(StrokeLineCap.ROUND);
        canvas = new WorldView(world);
        initPlayer(playerNum);

        // fancy town
        Glow glow = new Glow();
        glow.setLevel(1);
        path.setEffect(glow);
    }

    private void initPlayer(int pn) {
        if(pn < 2) {
            direction = DIR_SOUTH;
        }
        else {
            direction = DIR_NORTH;
        }

        int x = world.getXSize();
        int y = world.getYSize();

        switch(pn) {
            case 0:
                head = new WorldPixel(x/4, y/4);
                trail.add(new MoveTo(x/4, y/4));
                path.setStroke(Color.RED);
                break;
            case 1:
                head = new WorldPixel((x/4)*3, y/4);
                trail.add(new MoveTo((x/4)*3, y/4));
                path.setStroke(Color.BLUE);
                break;
            case 2:
                head = new WorldPixel(x/4, (y/4)*3);
                trail.add(new MoveTo(x/4, (y/4)*3));
                path.setStroke(Color.GREEN);
                break;
            case 3:
                head = new WorldPixel((x/4)*3, (y/4)*3);
                trail.add(new MoveTo((x/4)*3, (y/4)*3));
                path.setStroke(Color.PURPLE);
                break;
        }
    }

    /* Getters */
    public WorldPixel getHead() {
        return head;
    }

    public void setHead(WorldPixel newHead) {
        this.head = newHead;
    }

//    public ArrayList<WorldPixel> getTrail() {
//        return trail;
//    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int newDir) {
        if (newDir != direction && newDir != -direction)
            direction = newDir;
    }

    public int getSectorID() {
        return sectorID;
    }


    /* Setters */

    public void setSectorID(int newSID) {
        sectorID = newSID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public boolean isAlive(){
        return alive;
    }

    public void setCoordinates(int x, int y) {
        head = new WorldPixel(x, y);
    }

    public void setDead(){
        alive = false;
    }

    /* Add coordinates to trail */
//    public void addToTrail(WorldPixel c) {
//        trail.add(c);
//    }
//
//    /* See if coordinates are in the trail */
//    public boolean isInTrail(WorldPixel c)
//    {
//        boolean retVal = false;
//
//        for(int i = 0; i<trail.size(); i++)
//        {
//            retVal = trail.get(i).isA(c);
//            if(retVal)
//                break;
//        }
//
//        return retVal;
//    }
//
//    public boolean isInTrail(int x, int y)
//    {
//        boolean retVal = false;
//
//        for(int i = 0; i<trail.size(); i++)
//        {
//            retVal = trail.get(i).isA(x,y);
//            if(retVal)
//                break;
//        }
//
//        return retVal;
//    }

    /* Move:
     * Take a step in the direction player is currently facing
     */
    public void move()
    {
        int newX = head.getX();
        int newY = head.getY();

        //addToTrail(head);

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

        if(canvas.getGraphicsContext2D().isPointInPath(newX, newY)) {
            alive = false;
        }
        else {
            trail.add(new LineTo(newX, newY));
            setHead(new WorldPixel(newX, newY));
        }
    }
}