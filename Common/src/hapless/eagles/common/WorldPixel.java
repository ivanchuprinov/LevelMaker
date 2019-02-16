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

    public WorldPixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public WorldPixel(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
    }



    public int getX(){ return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public boolean isA(WorldPixel c)
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
