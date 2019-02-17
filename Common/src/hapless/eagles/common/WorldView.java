package hapless.eagles.common;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import lombok.Getter;

/**
 * Render the world.
 * Created by Kneesnap on 2/16/19.
 */
@Getter
public class WorldView extends Canvas {
    private World world;
    private double centerWorldXTile;
    private double centerWorldYTile;
    private double displayRadius = 10;

    public WorldView(World world) {
        this.world = world;
        setFocusTraversable(true); // Fixes setOnKeyPressed
        updatePosition(world.getXSize() / 2D, world.getYSize() / 2D);

        setOnKeyPressed(evt -> {

            if (evt.getCode() == KeyCode.EQUALS) {
                this.displayRadius--;
            } else if (evt.getCode() == KeyCode.MINUS) {
                this.displayRadius++;
            } else if (evt.getCode() == KeyCode.UP) {
                this.centerWorldYTile -= .5;
            } else if (evt.getCode() == KeyCode.DOWN) {
                this.centerWorldYTile += .5;
            } else if (evt.getCode() == KeyCode.LEFT) {
                this.centerWorldXTile -= .5;
            } else if (evt.getCode() == KeyCode.RIGHT) {
                this.centerWorldXTile += .5;
            } else {
                return;
            }

            renderWorld();
        });
    }

    /**
     * Renders the world.
     */
    public void renderWorld() {
        if (getWidth() == 0 || getHeight() == 0)
            return; // Don't render if our height is zero.

        GraphicsContext graphics = getGraphicsContext2D();
        graphics.clearRect(0, 0, getWidth(), getHeight()); // Clear display.

        double pixelXWidth = getWidth() / (2 * displayRadius);
        double pixelYWidth = getHeight() / (2 * displayRadius);
        double topLeftX = ((centerWorldXTile - displayRadius) * pixelXWidth);
        double topLeftY = ((centerWorldYTile - displayRadius) * pixelYWidth);

        for (int x = 0; x < world.getXSize(); x++) {
            for (int y = 0; y < world.getYSize(); y++) {
                WorldPixel pixel = world.getPixel(x, y);
                graphics.setFill(pixel.hasWall() ? pixel.getWallColor() : pixel.getColor());
                graphics.fillRect((x * pixelXWidth) - topLeftX, (y * pixelYWidth) - topLeftY, pixelXWidth, pixelYWidth);
            }
        }
    }

    /**
     * Set the center view position.
     * @param xTile New view x.
     * @param yTile New view y.
     */
    public void updatePosition(double xTile, double yTile) {
        this.centerWorldXTile = xTile;
        this.centerWorldYTile = yTile;
    }
}
