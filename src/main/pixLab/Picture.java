package pixLab;

import com.jhlabs.image.EdgeFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.SharpenFilter;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture()
    {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     *
     * @param height the height of the desired picture
     * @param width  the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a
     * copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() +
                " height " + getHeight()
                + " width " + getWidth();
        return output;

    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Method to set everything except blue to 0
     */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
                pixelObj.setGreen(0);
            }
        }
    }

    /**
     * Method to negate all the pixels in a picture
     */
    public void negate()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }

    /**
     * Method to turn the picture into shades of gray
     */
    public void grayscale()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                int color = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
                pixelObj.setRed(color);
                pixelObj.setGreen(color);
                pixelObj.setBlue(color);
            }
        }
    }

    /**
     * Method to modify the pixel colors to make the fish easier to see
     */
    public void fixUnderwater()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                //pixelObj.setBlue(pixelObj.getBlue() / 2);
                //pixelObj.setRed(pixelObj.getRed() * 3);
                pixelObj.setRed(pixelObj.getRed() * 2);
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * vertical mirror in the center of the picture
     * from left to right
     */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * vertical mirror in the center of the picture
     * from right to left
     */
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel, rightPixel;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * horizontal mirror in the center of the picture
     * from top to bottom
     */
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel, bottomPixel;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a
     * horizontal mirror in the center of the picture
     * from bottom to top
     */
    public void mirrorHorizontalBottomToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel, bottomPixel;
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture
     * diagonally
     */
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel bottomLeftPixel, topRightPixel;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < row; col++)
            {
                bottomLeftPixel = pixels[row][col];
                topRightPixel = pixels[col][row];
                topRightPixel.setColor(bottomLeftPixel.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                ++count;
            }
        }
        System.out.println(count);
    }

    /**
     * Mirror the arms on the snowman
     */
    public void mirrorArms()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel, bottomPixel;
        int mirrorPoint = 192;
        for (int row = 155; row < mirrorPoint; row++)
        {
            for (int col = 100; col < 291; col++)
            {
                if (col < 170 || col > 238)
                {
                    topPixel = pixels[row][col];
                    bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                    bottomPixel.setColor(topPixel.getColor());
                }
            }
        }
    }

    /**
     * Mirror the seagull to make two seagulls
     */
    public void mirrorGull()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel, rightPixel;
        int width = 116;
        int left = 235;
        for (int row = 233; row < 326; row++)
        {
            for (int col = 0; col < width; col++)
            {
                leftPixel = pixels[row][left + col];
                rightPixel = pixels[row][left + width * 2 - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     *
     * @param fromPic  the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(
            Picture fromPic,
            int startRow, int startCol)
    {
        Pixel[][] pixels = fromPic.getPixels2D();
        copy(fromPic, startRow, startCol, 0, 0, pixels.length, pixels[0].length);
    }

    /**
     * copy from the passed fromPic between
     * fromStartRow/Col a and fromEndRow/Col to
     * the specified toStartRow and startCol in
     * the current picture
     *
     * @param fromPic      the picture to copy from
     * @param toStartRow   the start row to copy to
     * @param toStartCol   the start col to copy to
     * @param fromStartRow the start row to copy from
     * @param fromStartCol the start col to copy from
     * @param fromEndRow   the end row to copy from
     * @param fromEndCol   the end col to copy from
     */
    public void copy(
            Picture fromPic, int toStartRow, int toStartCol, int fromStartRow, int fromStartCol, int fromEndRow, int
            fromEndCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = fromStartRow, toRow = toStartRow;
             fromRow < fromEndRow &&
                     toRow < toPixels.length;
             fromRow++, toRow++)
        {
            for (int fromCol = fromStartCol, toCol = toStartCol;
                 fromCol < fromEndCol &&
                         toCol < toPixels[0].length;
                 fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * Method to create a collage of several pictures
     */
    public void createCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        this.copy(flower1, 0, 0);
        this.copy(flower2, 100, 0);
        this.copy(flower1, 200, 0);
        Picture flowerNoBlue = new Picture(flower2);
        flowerNoBlue.zeroBlue();
        this.copy(flowerNoBlue, 300, 0);
        this.copy(flower1, 400, 0);
        this.copy(flower2, 500, 0);
        this.mirrorVertical();
        this.write("collage.jpg");
    }

    /**
     * Method to create a different collage of several pictures
     */
    public void myCollage()
    {
        Picture flower1 = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        Picture flower3 = new Picture(flower2);
        flower3.mirrorDiagonal();
        copy(flower1, 15, 10, 15, 10, 55, 50);
        copy(flower2, 70, 20);
        copy(flower3, 300, 203);
        mirrorVertical();
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel, rightPixel;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0;
                 col < pixels[0].length - 1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) >
                        edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /**
     * Better method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection2(int edgeDist)
    {
        Pixel topLeftPixel, rightPixel, bottomPixel;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor, bottomColor;
        for (int row = 0; row < pixels.length - 1; row++)
        {
            for (int col = 0;
                 col < pixels[0].length - 1; col++)
            {
                topLeftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                bottomPixel = pixels[row + 1][col];
                rightColor = rightPixel.getColor();
                bottomColor = bottomPixel.getColor();
                if (topLeftPixel.colorDistance(rightColor) >
                        edgeDist || topLeftPixel.colorDistance(bottomColor) > edgeDist)
                    topLeftPixel.setColor(Color.BLACK);
                else
                    topLeftPixel.setColor(Color.WHITE);
            }
        }
    }

    /**
     * Real edge detection
     *
     * @param blurRadius amount to blur before detecting edges
     */
    public void edgeDetection3(int blurRadius)
    {
        GaussianFilter gaussianFilter = new GaussianFilter(blurRadius);
        EdgeFilter edgeFilter = new EdgeFilter();
        SharpenFilter sharpenFilter = new SharpenFilter();
        sharpenFilter.filter(edgeFilter.filter(gaussianFilter.filter(getBufferedImage(), getBufferedImage()),
                                               getBufferedImage()), getBufferedImage());
        grayscale();
        negate();
    }


    /**
     * Main method for testing - each class in Java can have a main
     * method
     */
    public static void main(String[] args)
    {
        //Picture beach = new Picture("beach.jpg");
        //beach.explore();
        //beach.zeroBlue();
        //beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
