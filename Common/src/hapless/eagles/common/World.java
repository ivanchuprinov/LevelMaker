package hapless.eagles.common;

import hapless.eagles.common.utils.Config;
import hapless.eagles.common.utils.Utils;
import javafx.scene.paint.Color;
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

    private static final Color[] COLORS = {Color.YELLOW, Color.GREEN, Color.HOTPINK, Color.RED, Color.BLUE, Color.GRAY};

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

        // Create empty pixels.
        for (int y = 0; y < this.pixels.length; y++)
            for (int x = 0; x < this.pixels[y].length; x++)
                this.pixels[y][x] = new WorldPixel(this, x, y);

        // Create empty sectors.
        for (int y = 0; y < this.sectors.length; y++)
            for (int x = 0; x < this.sectors[y].length; x++)
                this.sectors[y][x] = new WorldSector(this, x, y);

        randomizeBoard();
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
            throw new RuntimeException("Y=" + y + " is outside the bounds of the world!");
        return pixels[y][x];
    }

    /**
     * Gets a sector based on the sector position.
     * @param sectorX [0, xSectorCount)
     * @param sectorY [0, ySectorCount)
     * @return worldSector
     */
    public WorldSector getSector(int sectorX, int sectorY) {
        if (sectorX < 0 || sectorY >= getXSectorCount())
            throw new RuntimeException("X=" + sectorX + " is outside the bounds of the world!");

        if (sectorY < 0 || sectorY >= getYSectorCount())
            throw new RuntimeException("Y=" + sectorY + " is outside the bounds of the world!");
        return sectors[sectorY][sectorX];
    }

    /**
     * Randomize the colors of the board.
     */
    public void randomizeBoard() {
        for (WorldPixel[] array : pixels)
            for (WorldPixel pixel : array)
                pixel.setColor(Utils.randElement(COLORS));
    }
}
