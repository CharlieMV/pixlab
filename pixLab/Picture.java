import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

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
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
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
  
  /** Method to set the blue to 0 */
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
  
  /** Method to set the red and green to 0 */
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
  
  /** Method to set all pixels to 255 - current value*/
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
  
  /** Method to set all pixels to 255 - current value*/
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
		int average = (pixelObj.getRed() + pixelObj.getGreen() +
				pixelObj.getBlue()) / 3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }
  
  /** To pixelate by dividing area into size x size.
   *  @param size Side length of square area to pixelate.
   */
  public void pixelate(int size)
  {
    Pixel[][] pixels = this.getPixels2D();
    int pixelWidth = (int)Math.ceil((float)pixels[0].length / size);
    int pixelLength = (int)Math.ceil((float)pixels.length / size);
    for (int i = 0; i < pixelLength; i++) {
		for (int j = 0; j < pixelWidth; j++) {
			//	blur region
			int totalRed = 0;
			int totalGreen = 0;
			int totalBlue = 0;
			int totalPixels = 0;
			for (int k = i * size; k < (i + 1) * size; k++) {
				for (int l = j * size; l < (j + 1) * size; l++) {
					if (k < pixels.length && k >= 0 && l < pixels[0].length
						&& l >= 0) {
						totalRed += pixels[k][l].getRed();
						totalGreen += pixels[k][l].getGreen();
						totalBlue += pixels[k][l].getBlue();
						totalPixels ++;
					}
				}
			}
			totalRed /= totalPixels;
			totalGreen /= totalPixels;
			totalBlue /= totalPixels;
			for (int k = i * size; k < (i + 1) * size; k++) {
				for (int l = j * size; l < (j + 1) * size; l++) {
					if (k < pixels.length && k >= 0 && l < pixels[0].length
						&& l >= 0) {
						pixels[k][l].setRed(totalRed);
						pixels[k][l].setGreen(totalGreen);
						pixels[k][l].setBlue(totalBlue);
					}
				}
			}
		}
	}
  }
  
  /** Method that blurs the picture
   * @param size Blur size, greater is more blur
   * @return Blurred picture
   */
   public Picture blur(int size)
   {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	for (int i = 0; i < pixels.length; i++) {
		for (int j = 0; j < pixels[i].length; j++) {
			//	find average
			int totalRed = 0;
			int totalGreen = 0;
			int totalBlue = 0;
			int totalPixels = 0;
			for (int k = i - size / 2; k <= i + size / 2; k++) {
				for (int l = j - size / 2; l <= j + size / 2; l++) {
					if (k < pixels.length && k >= 0 && l < pixels[0].length
						&& l >= 0) {
						totalRed += pixels[k][l].getRed();
						totalGreen += pixels[k][l].getGreen();
						totalBlue += pixels[k][l].getBlue();
						totalPixels ++;
					}
				}
			}
			totalRed /= totalPixels;
			totalGreen /= totalPixels;
			totalBlue /= totalPixels;
			resultPixels[i][j].setRed(totalRed);
			resultPixels[i][j].setGreen(totalGreen);
			resultPixels[i][j].setBlue(totalBlue);
		}
	 }
	 return result;
   }
  
	/** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
	public Picture enhance(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				//	find average
				int totalRed = 0;
				int totalGreen = 0;
				int totalBlue = 0;
				int totalPixels = 0;
				for (int k = i - size / 2; k <= i + size / 2; k++) {
					for (int l = j - size / 2; l <= j + size / 2; l++) {
						if (k < pixels.length && k >= 0 && l < pixels[0].length
							&& l >= 0) {
							totalRed += pixels[k][l].getRed();
							totalGreen += pixels[k][l].getGreen();
							totalBlue += pixels[k][l].getBlue();
							totalPixels ++;
						}
					}
				}
				totalRed /= totalPixels;
				totalGreen /= totalPixels;
				totalBlue /= totalPixels;
				int finalRed = 2 * pixels[i][j].getRed() - totalRed;
				int finalGreen = 2 * pixels[i][j].getGreen() - totalGreen;
				int finalBlue = 2 * pixels[i][j].getBlue() - totalBlue;
				resultPixels[i][j].setRed(finalRed);
				resultPixels[i][j].setGreen(finalGreen);
				resultPixels[i][j].setBlue(finalBlue);
			}
		 }
		 return result;
	}
  /** Method that shifts the entire image to the right and wraps around
   *  the left side.
   *  @param	int			percent to shift the image
   *  @return	Picture		shifted image
   */
  public Picture shiftRight(int percent) {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	for (int i = 0; i < pixels.length; i++) {
		for (int j = 0; j < pixels[i].length; j++) {
			int newCol = j;
			newCol -= percent * pixels[i].length / 100;
			if (newCol < 0) newCol += pixels[i].length;
			resultPixels[i][j].setRed(pixels[i][newCol].getRed());
			resultPixels[i][j].setGreen(pixels[i][newCol].getGreen());
			resultPixels[i][j].setBlue(pixels[i][newCol].getBlue());
		}
	}
	return result;
  }
  
  /** Staricase shift the image
   *  @param	int		num of pixels to shift
   *  @param	int		number of steps
   *  @return	Picture	shifted picture
   */
  public Picture stairStep(int shiftCount, int steps) {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	//	Vertical chunk
	for (int i = 0; i < steps; i++) {
		int shiftPixels = shiftCount * i;
		//	Down the vertical chunk
		for (int j = pixels.length / steps * i; j < pixels.length / 
												steps * (i + 1); j++) {
			//	Across the row
			for (int k = 0; k < pixels[0].length; k++) {
				int newCol = k;
				newCol -= shiftPixels;
				while (newCol < 0) newCol += pixels[0].length;
				resultPixels[j][k].setRed(pixels[j][newCol].getRed());
				resultPixels[j][k].setGreen(pixels[j][newCol].getGreen());
				resultPixels[j][k].setBlue(pixels[j][newCol].getBlue());
			}
		}
	}
	return result;
  }
  
  /** Method to rotate the image 90 degrees
   *  @return	Picture		rotated picture
   */
  public Picture turn90() {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels[0].length, pixels.length);
	Pixel[][] resultPixels = result.getPixels2D();
	for (int i = 0; i < pixels.length; i++) {
		for (int j = 0; j < pixels[i].length; j++) {
			resultPixels[j][i].setRed(pixels[pixels.length - i - 1][j].getRed());
			resultPixels[j][i].setGreen(pixels[pixels.length - i - 1][j].getGreen());
			resultPixels[j][i].setBlue(pixels[pixels.length - i - 1][j].getBlue());
		}
	}
	return result;
  }
  
  /** Method to zoom into the upper left of the image
   *  @return	Picture		rotated picture
   */
  public Picture zoomUpperLeft() {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	for (int i = 0; i < pixels.length; i++) {
		for (int j = 0; j < pixels[i].length; j++) {
			resultPixels[i][j].setRed(pixels[i / 2][j / 2].getRed());
			resultPixels[i][j].setGreen(pixels[i / 2][j / 2].getGreen());
			resultPixels[i][j].setBlue(pixels[i / 2][j / 2].getBlue());
		}
	}
	return result;
  }
  
  /** Method to zoom into the upper left of the image
   *  @return	Picture		rotated picture
   */
  public Picture tileMirror() {
	Pixel[][] pixels = this.getPixels2D();
	//	Mini version of image to tile. Scaled to 1/2 length and width
	Picture mini = new Picture(pixels.length / 2, pixels[0].length / 2);
	Pixel[][] miniPixels = mini.getPixels2D();
	//	Output
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	
	//	To use when averaging pixels
	int[][] check = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	//	Create mini
	for (int i = 0; i < miniPixels.length; i++) {
		for (int j = 0; j < miniPixels[i].length; j++) {
			//	Calculate average red green and blue of 2x2 area on
			//	pixels corresponding to pixel on mini
			int red = 0;
			int green = 0;
			int blue = 0;
			int total = 0;
			for (int[] k: check) {
				if (2 * i + k[1] < pixels.length && 2 * j + k[0] < pixels[0].length) {
					red += pixels[2 * i + k[1]][2 * j + k[0]].getRed();
					green += pixels[2 * i + k[1]][2 * j + k[0]].getGreen();
					blue += pixels[2 * i + k[1]][2 * j + k[0]].getBlue();
					total ++;
				}
			}
			miniPixels[i][j].setRed(red/total);
			miniPixels[i][j].setGreen(green/total);
			miniPixels[i][j].setBlue(blue/total);
		}
	}
	//return mini;
	//	Copy over
	for (int i = 0; i < miniPixels.length; i++) {
		for (int j = 0; j < miniPixels[i].length; j++) {
			//	Top left
			resultPixels[i][j].setRed(miniPixels[i][j].getRed());
			resultPixels[i][j].setGreen(miniPixels[i][j].getGreen());
			resultPixels[i][j].setBlue(miniPixels[i][j].getBlue());
			//	Top right
			resultPixels[i][pixels[0].length - j - 1].
									setRed(miniPixels[i][j].getRed());
			resultPixels[i][pixels[0].length - j - 1].
									setGreen(miniPixels[i][j].getGreen());
			resultPixels[i][pixels[0].length - j - 1].
									setBlue(miniPixels[i][j].getBlue());
			//	Bottom left
			resultPixels[pixels.length - i - 1][j].
									setRed(miniPixels[i][j].getRed());
			resultPixels[pixels.length - i - 1][j].
									setGreen(miniPixels[i][j].getGreen());
			resultPixels[pixels.length - i - 1][j].
									setBlue(miniPixels[i][j].getBlue());
			//	Bottom right
			resultPixels[pixels.length - i - 1][pixels[0].length - j - 1].
									setRed(miniPixels[i][j].getRed());
			resultPixels[pixels.length - i - 1][pixels[0].length - j - 1].
									setGreen(miniPixels[i][j].getGreen());
			resultPixels[pixels.length - i - 1][pixels[0].length - j - 1].
									setBlue(miniPixels[i][j].getBlue());
		}
	}
	
	return result;
  }
  
  /** Method to detect edges of an image
   *  @param	int		how much color difference to consider an edge
   *  @return	Picture	output (white for no edge, black for edge)
   */
  public Picture edgeDetectionBelow(int threshold) {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  for (int r = 0; r < pixels.length - 1; r++) {
		  for (int c = 0; c < pixels[0].length; c++) {
			  double diff = pixels[r][c].colorDistance(pixels[r + 1][c].getColor());
			  if (diff > threshold) resultPixels[r][c].setColor(new Color(0, 0, 0));
			  else resultPixels[r][c].setColor(new Color(255, 255, 255));
		  }
	  }
	  //	Last row
	  for (int c = 0; c < pixels[0].length; c++) 
		resultPixels[resultPixels.length - 1][c].setColor(new Color(255, 255, 255));
	  return result;
  }
  
  /** Method to detect edges of an image
   *  @return	Picture	output (white for no edge, black for edge)
   */
  public Picture greenScreen() {
	//	Position of cat1
	int cat1Row = 300;
	int cat1Col = 150;
	int cat1Shrink = 2;
	//	Position of cat2
	int cat2Row = 400;
	int cat2Col = 500;
	int cat2Shrink = 3;
	//	To use when averaging pixels
	int[][] check = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	int[][] check3 = new int[][]{{0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1},
			{1, 2}, {2, 0}, {2, 1}, {2, 2}};
	
	//	Initialize pictures and output
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	
	//	Copy over starting picture to result
	for (int r = 0; r < pixels.length; r++)
		for (int c = 0; c < pixels[0].length; c++)
			resultPixels[r][c].setColor(pixels[r][c].getColor());
	
	//	Add first cat
	Picture cat1 = new Picture("GreenScreenCatMouse/kitten1GreenScreen.jpg");
	Pixel[][] cat1Pixels = cat1.getPixels2D();
	//	First cat but all green pixels are made completely green
	Picture noGreenCat1 = new Picture(cat1Pixels.length, cat1Pixels[0].length);
	Pixel[][] noGreenCat1Pixels = noGreenCat1.getPixels2D();
	//	Set greens
	for (int r = 0; r < cat1Pixels.length; r++) {
		for (int c = 0; c < cat1Pixels[0].length; c++) {
			//	Get color info
			int red = cat1Pixels[r][c].getRed();
			int green = cat1Pixels[r][c].getGreen();
			int blue = cat1Pixels[r][c].getBlue();
			double pixColorDistance = Pixel.colorDistance(new Color(red, 
					green, blue), new Color(51, 204, 51));
			//	Set greens to 0, 255, 0
			if (pixColorDistance < 100) {
				noGreenCat1Pixels[r][c].setColor(new Color(0, 255, 0));
			} //	Transfer other pictures regularly
			else {
				noGreenCat1Pixels[r][c].setColor(cat1Pixels[r][c].getColor());
			}
		}
	}
	//	Scale down by factor of cat1Shrink on each axis and get rid of green
	for (int r = 0; r < cat1Pixels.length / cat1Shrink; r++) {
		for (int c = 0; c < cat1Pixels[0].length / cat1Shrink; c++) {
			//	Find averages of 2x2 pixel areas
			int red = 0;
			int green = 0;
			int blue = 0;
			int total = 0;
			for (int[] k: check) {
				//	Dont count greens in the average
				int tempRed = noGreenCat1Pixels[cat1Shrink * r + k[1]]
								[cat1Shrink * c + k[0]].getRed();
				int tempGreen = noGreenCat1Pixels[cat1Shrink * r + k[1]]
								[cat1Shrink * c + k[0]].getGreen();
				int tempBlue = noGreenCat1Pixels[cat1Shrink * r + k[1]]
								[cat1Shrink * c + k[0]].getBlue();
				double pixColorDistance = Pixel.colorDistance(new Color(tempRed, 
					tempGreen, tempBlue), new Color(0, 255, 0));
				if (cat1Shrink * r + k[1] < cat1Pixels.length && cat1Shrink * c + k[0] < 
						cat1Pixels[0].length && pixColorDistance > 150) {
					red += tempRed;
					green += tempGreen;
					blue += tempBlue;
					total ++;
				}
			}
			if (total != 0) {
				red /= total;
				green /= total;
				blue /= total;
				double pixColorDistance = Pixel.colorDistance(new Color(red, 
						green, blue), new Color(0, 255, 0));
				if (pixColorDistance >= 100){
					if (pixColorDistance <= 100) green -= 50;
					resultPixels[r + cat1Row][c + cat1Col].setRed(red);
					resultPixels[r + cat1Row][c + cat1Col].setGreen(green);
					resultPixels[r + cat1Row][c + cat1Col].setBlue(blue);
				}
			}
		}
	}
	
	//	Add second cat
	Picture cat2 = new Picture("GreenScreenCatMouse/kitten2GreenScreen.jpg");
	Pixel[][] cat2Pixels = cat2.getPixels2D();
	//	First cat but all green pixels are made completely green
	Picture noGreenCat2 = new Picture(cat2Pixels.length, cat2Pixels[0].length);
	Pixel[][] noGreenCat2Pixels = noGreenCat2.getPixels2D();
	//	Set greens
	for (int r = 0; r < cat2Pixels.length; r++) {
		for (int c = 0; c < cat2Pixels[0].length; c++) {
			//	Get color info
			int red = cat2Pixels[r][c].getRed();
			int green = cat2Pixels[r][c].getGreen();
			int blue = cat2Pixels[r][c].getBlue();
			double pixColorDistance = Pixel.colorDistance(new Color(red, 
					green, blue), new Color(51, 204, 51));
			//	Set greens to 0, 255, 0
			if (pixColorDistance < 100) {
				noGreenCat2Pixels[r][c].setColor(new Color(0, 255, 0));
			} //	Transfer other pictures regularly
			else {
				noGreenCat2Pixels[r][c].setColor(cat2Pixels[r][c].getColor());
			}
		}
	}
	//	Scale down by factor of cat2Shrink on each axis and get rid of green
	for (int r = 0; r < cat2Pixels.length / cat2Shrink; r++) {
		for (int c = 0; c < cat2Pixels[0].length / cat2Shrink; c++) {
			//	Find averages of 2x2 pixel areas
			int red = 0;
			int green = 0;
			int blue = 0;
			int total = 0;
			for (int[] k: check) {
				//	Dont count greens in the average
				int tempRed = noGreenCat2Pixels[cat2Shrink * r + k[1]]
								[cat2Shrink * c + k[0]].getRed();
				int tempGreen = noGreenCat2Pixels[cat2Shrink * r + k[1]]
								[cat2Shrink * c + k[0]].getGreen();
				int tempBlue = noGreenCat2Pixels[cat2Shrink * r + k[1]]
								[cat2Shrink * c + k[0]].getBlue();
				double pixColorDistance = Pixel.colorDistance(new Color(tempRed, 
					tempGreen, tempBlue), new Color(0, 255, 0));
				if (cat2Shrink * r + k[1] < cat2Pixels.length && cat2Shrink * c + k[0] < 
						cat2Pixels[0].length && pixColorDistance > 150) {
					red += tempRed;
					green += tempGreen;
					blue += tempBlue;
					total ++;
				}
			}
			if (total != 0) {
				red /= total;
				green /= total;
				blue /= total;
				double pixColorDistance = Pixel.colorDistance(new Color(red, 
						green, blue), new Color(0, 255, 0));
				if (pixColorDistance >= 100){
					if (pixColorDistance <= 100) green -= 50;
					resultPixels[r + cat2Row][c + cat2Col].setRed(red);
					resultPixels[r + cat2Row][c + cat2Col].setGreen(green);
					resultPixels[r + cat2Row][c + cat2Col].setBlue(blue);
				}
			}
		}
	}
	return result;
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
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
  
  /** Mirror just part of a picture of a temple */
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
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
