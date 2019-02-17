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
            trail.add(sector.getPixel(Utils.randInt(0, getWorld().getXSectorSize() - 1), Utils.randInt(0, getWorld().getYSectorSize() - 1)));
        }
    }

    /**
     * Move this player's snake.
     */
    public WorldPixel move() {
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