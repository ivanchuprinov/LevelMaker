package hapless.eagles.common;

import lombok.Getter;

/**
 * Represents a section of the world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldSector {
    private int x;
    private int y;
    private World world;

    public WorldSector(World parentWorld, int x, int y) {
        this.world = parentWorld;
        this.x = x;
        this.y = y;
    }

}
