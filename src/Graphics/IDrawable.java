package Graphics;

import java.awt.*;
/**
 * The {@code IDrawable} interface defines methods for drawing objects
 * in a graphical context. Classes implementing this interface should
 * provide the functionality to load images and render them on a
 * graphical component.
 */
public interface IDrawable {
    /**
     * The path to the picture or image resource.
     * This is a constant string that represents the location of the image file.
     */

    public final static String PICTURE_PATH = "…";
    /**
     * Loads the images required for drawing the object.
     * The method should handle the loading of images from the provided path.
     *
     * @param nm The name or path of the image file to be loaded.
     */
        public void loadImages(String nm);
    /**
     * Draws the object on the provided {@code Graphics} context.
     * Implementations should define how the object is rendered using
     * the provided {@code Graphics} object.
     *
     * @param g The {@code Graphics} context used for drawing the object.
     */
        public void drawObject (Graphics g);
    }

