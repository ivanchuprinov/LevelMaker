package hapless.eagles.common.utils;

import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Holds static utilities.
 * Created by Kneesnap on 2/16/2019.
 */
public class Utils {
    private static Map<Integer, Color> colorCacheMap = new HashMap<>();

    /**
     * Verify a condition is true, otherwise throw an exception.
     * @param condition The condition to verify is true.
     * @param error     The error message if false.
     */
    public static void verify(boolean condition, String error) {
        if (!condition)
            throw new RuntimeException(error);
    }

    /**
     * Verify a condition is true, otherwise throw an exception.
     * @param condition  The condition to verify is true.
     * @param error      The error message if false.
     * @param formatting Formatting to apply to the error message.
     */
    public static void verify(boolean condition, String error, Object... formatting) {
        if (!condition)
            throw new RuntimeException(formatting.length > 0 ? String.format(error, formatting) : error);
    }

    /**
     * Create a new array with less elements than the supplied one. (Cut from the left side)
     * @param array    The array to take elements from.
     * @param cutCount The amount of elements to cut.
     * @return newArray
     */
    public static <T> T[] cutElements(T[] array, int cutCount) {
        return Arrays.copyOfRange(array, cutCount, array.length);
    }

    /**
     * Capitalize every letter after a space.
     * @param sentence The sentence to capitalize.
     * @return capitalized
     */
    public static String capitalize(String sentence) {
        String[] split = sentence.replaceAll("_", " ").split(" ");
        List<String> out = new ArrayList<>();
        for (String s : split)
            out.add(s.length() > 0 ? s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() : "");
        return String.join(" ", out);
    }

    /**
     * Is the input string a valid integer or decimal number?
     * @param input The input to test.
     * @return isNumber
     */
    public static boolean isNumber(String input) {
        boolean hasDecimal = false;
        for (int i = 0; i < input.length(); i++) {
            char test = input.charAt(i);
            if (test == '-' && i == 0)
                continue; // Allow negative indicator.

            if (test == '.') {
                if (!hasDecimal) {
                    hasDecimal = true;
                    continue;
                } else {
                    return false; // Multiple decimal = invalid number.
                }
            }

            if (!Character.isDigit(test))
                return false; // Character isn't a digit, so it can't be a number.
        }

        return true;
    }

    /**
     * Resizes an image.
     * @param img    The image to resize.
     * @param width  The new width.
     * @param height The new height.
     * @return resized
     */
    public static BufferedImage resizeImage(BufferedImage img, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, img.getType());
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        graphics.dispose();

        return newImage;
    }

    /**
     * Get a resource in the JAR.
     * @param resourceName The resource name.
     * @return resourceURL
     */
    public static URL getResource(String resourceName) {
        return Utils.class.getClassLoader().getResource(resourceName);
    }

    /**
     * Get a JAR resource as a stream.
     * @param resourceName The name of the resource to load.
     * @return resourceStream
     */
    public static InputStream getResourceStream(String resourceName) {
        return Utils.class.getClassLoader().getResourceAsStream(resourceName);
    }

    /**
     * Gets a random integer between two numbers.
     * @param min The minimum value this might return.
     * @param max The maximum value this might return.
     * @return randValue
     */
    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Gets a random element from an array.
     * @param array The array to get values from.
     * @return randElement
     */
    public static <T> T randElement(T[] array) {
        if (array.length == 0)
            throw new RuntimeException("Cannot get random element from empty array.");

        return array[randInt(0, array.length - 1)];
    }

    /**
     * Get a Color object from an integer.
     * @param rgb The integer to get the color from.
     * @return color
     */
    public static Color fromRGB(int rgb) {
        return colorCacheMap.computeIfAbsent(rgb, key -> Color.rgb((key >> 16) & 0xFF, (key >> 8) & 0xFF, key & 0xFF));
    }

    /**
     * Get a integer from a color object.
     * @param color The color to turn into rgb.
     * @return rgbInt
     */
    public static int toRGB(Color color) {
        int result = (int) (color.getRed() * 0xFF);
        result = (result << 8) + (int) (color.getGreen() * 0xFF);
        result = (result << 8) + (int) (color.getBlue() * 0xFF);
        return result;
    }
}
