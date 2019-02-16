package hapless.eagles.common;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Render the world.
 * TODO: Zoom + Camera
 * Created by Kneesnap on 2/16/19.
 */
public class WorldView extends Canvas {
    private World world;

    public WorldView(World world) {
        this.world = world;
    }

    /**
     * Renders the world.
     */
    public void renderWorld() {
        System.out.println("Rendering World: [" + getWidth() + ", " + getHeight() + "]");
        GraphicsContext graphics = getGraphicsContext2D();
        graphics.clearRect(0, 0, getWidth(), getHeight()); // Clear display.

        double pixelXWidth = getWidth() / world.getXSize();
        double pixelYWidth = getHeight() / world.getYSize();

        for (int x = 0; x < world.getXSize(); x++) {
            for (int y = 0; y < world.getYSize(); y++) {
                WorldPixel pixel = world.getPixel(x, y);
                graphics.setFill(pixel.getColor() != null ? pixel.getColor() : Color.PINK);
                graphics.fillRect((x * pixelXWidth), (y * pixelYWidth), pixelXWidth, pixelYWidth);
            }
        }
    }
}
