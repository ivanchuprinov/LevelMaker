package hapless.eagles.common;

import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.Utils;
import lombok.Getter;

/**
 * Represents the entire world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class World {
    private int xSectorSize;
    private int ySectorSize;
    private WorldPixel[][] pixels;
    private WorldSector[][] sectors;

    /**
     * Load world information from a config.
     * @param config The config to load from.
     */
    public void load(Config config) {
        int xSize = config.getInt("xSize");
        int ySize = config.getInt("ySize");
        int xSectors = config.getInt("xSectors");
        int ySectors = config.getInt("ySectors");

        this.xSectorSize = (xSize / xSectors);
        this.ySectorSize = (ySize / ySectors);
        Utils.verify(xSize % this.xSectorSize == 0, "Cannot split %d pixels equally between %d sectors.", this.xSectorSize, this.xSectorSize);
        Utils.verify(ySize % this.ySectorSize == 0, "Cannot split %d pixels equally between %d sectors.", this.ySectorSize, this.ySectorSize);

        this.pixels = new WorldPixel[ySize][xSize];
        this.sectors = new WorldSector[ySize / this.ySectorSize][xSectors / this.xSectorSize];

        //TODO
    }

    /**
     * Gets the X pixel count.
     * @return xSize
     */
    public int getXSize() {
        return pixels[0].length;
    }

    /**
     * Gets the Y pixel count.
     * @return ySize
     */
    public int getYSize() {
        return pixels.length;
    }

    /**
     * Gets the X sector count.
     * @return xSectorCount
     */
    public int getXSectorCount() {
        return this.sectors[0].length;
    }

    /**
     * Gets the Y sector count.
     * @return ySectorCount
     */
    public int getYSectorCount() {
        return this.sectors.length;
    }

    /**
     * Gets a pixel in the world.
     * @param x The x of the target pixel.
     * @param y The y of the target pixel.
     * @return pixel
     */
    public WorldPixel getPixel(int x, int y) {
        if (x < 0 || x >= getXSize())
            throw new RuntimeException("X=" + x + " is outside the bounds of the world!");

        if (y < 0 || y >= getYSize())
            throw new RuntimeException("X=" + x + " is outside the bounds of the world!");
        return pixels[y][x];
    }
}
