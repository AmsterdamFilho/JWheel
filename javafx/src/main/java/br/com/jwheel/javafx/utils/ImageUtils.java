package br.com.jwheel.javafx.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Lima Filho, A. L. - amsterdam@luvva.com.br
 */
public class ImageUtils
{
    private ImageUtils ()
    {
    }

    public static WritableImage clone (Image image)
    {
        PixelReader pixelReader = image.getPixelReader();

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // copy from source to destination pixel by pixel
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                Color color = pixelReader.getColor(x, y);
                pixelWriter.setColor(x, y, color);
            }
        }

        return writableImage;
    }

    public static void saveToFile (Path path, Image image) throws IOException
    {
        String pathString = path.toString();
        String extension = pathString.substring(pathString.lastIndexOf(".") + 1);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), extension, path.toFile());
    }
}
