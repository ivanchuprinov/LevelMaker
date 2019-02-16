package hapless.eagles.common;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single world pixel.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldPixel {
    @Setter private Color color;
    private World world;
    private int x;
    private int y;

    public WorldPixel(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }
}
