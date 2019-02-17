package hapless.eagles.common;

import hapless.eagles.common.utils.Utils;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Represents a single world pixel.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldPixel implements Serializable {
    @Setter private int colorId;
    @Setter private int wallColorId = -1;
    private World world;
    private int x;
    private int y;

    public WorldPixel(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the color of this pixel.
     * @return color
     */
    public Color getColor() {
        return Utils.fromRGB(this.colorId);
    }

    /**
     * Is there a wall currently on this pixel?
     * @return hasWall
     */
    public boolean hasWall() {
        return this.wallColorId >= 0;
    }

    /**
     * Gets the wall pixel color id.
     * @return wallColor
     */
    public Color getWallColor() {
        return Utils.fromRGB(this.wallColorId);
    }
}
