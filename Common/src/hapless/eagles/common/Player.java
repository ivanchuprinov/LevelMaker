package hapless.eagles.common;

import hapless.eagles.common.utils.Utils;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * Created by chuprii on 2/16/19.
 */
@Getter
public class Player implements Serializable {
    private World world;
    private WorldSector sector;

    @Setter private MoveDirection direction;
    private LinkedList<WorldPixel> trail = new LinkedList<>();
    @Setter private transient Channel channel; // Server-Only.
    @Setter private transient MoveDirection lastMoveDirection;

    public Player(World world) {
        this.world = world;
    }

    /**
     * Sets the world sector.
     * @param sector The new sector.
     */
    public void setSector(WorldSector sector) {
        this.sector = sector;

        //TODO: Better placement
        if (sector != null) {
            this.direction = Utils.randElement(MoveDirection.values());

            int xMax = getWorld().getXSectorSize() - 1;
            int yMax = getWorld().getYSectorSize() - 1;
            int xPos = Utils.randInt(0, xMax);
            int yPos = Utils.randInt(0, yMax);
            double xPercent = (double) xPos / (double) xMax;
            double yPercent = (double) yPos / (double) yMax;
            if (yPercent < .25)
                this.direction = MoveDirection.DOWN;
            if (xPercent < .25)
                this.direction = MoveDirection.RIGHT;
            if (xPercent > .75)
                this.direction = MoveDirection.LEFT;
            if (yPercent > .75)
                this.direction = MoveDirection.UP;

            trail.add(sector.getPixel(xPos, yPos));
        }
    }

    /**
     * Move this player's snake.
     */
    public WorldPixel move() {
        this.lastMoveDirection = getDirection();

        WorldPixel head = getTrail().peekLast();
        int oldX = head.getX();
        int oldY = head.getY();

        int newX = oldX + getDirection().getXDelta();
        int newY = oldY + getDirection().getYDelta();
        if (newX < getSector().getWorldXStart() || newX >= getSector().getWorldXEnd()
                || newY < getSector().getWorldYStart() || newY >= getSector().getWorldYEnd())
            return null;

        WorldPixel newPos = getWorld().getPixel(newX, newY);
        getTrail().addLast(newPos);
        return newPos;
    }
}