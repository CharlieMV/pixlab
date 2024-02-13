/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.keepOnlyBlue();
    swan.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.negate();
    swan.explore();
  }
  
  /** Method to test negate */
  public static void testGrayscale()
  {
    Picture motorcycle = new Picture("images/redMotorcycle.jpg");
    motorcycle.explore();
    motorcycle.grayscale();
    motorcycle.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan.pixelate(5);
    swan.explore();
    swan.pixelate(20);
    swan.explore();
  }
  
  /** Method to test pixelate */
  public static void testBlur()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
	Picture pixelated = beach.blur(20);
	pixelated.explore();
  }
  
  /** Method to test enhance */
  public static void testEnhance()
  {
    Picture fish = new Picture("images/water.jpg");
    fish.explore();
	Picture enhanced = fish.enhance(7);
	enhanced.explore();
  }
  
  /** Method to test shiftRight */
  public static void testShiftRight()
  {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
	Picture shifted = bike.shiftRight(50);
	shifted.explore();
  }
  
  /** Method to test stairStep */
  public static void testStairStep()
  {
    Picture bike = new Picture("images/redMotorcycle.jpg");
    bike.explore();
	Picture shifted = bike.stairStep(3, 200);
	shifted.explore();
  }
  
  /** Method to test turn90 */
  public static void testTurn90()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
	Picture rotated = swan.turn90();
	rotated.explore();
  }
  
  /** Method to test zoomUpperLeft */
  public static void testZoomUpperLeft()
  {
    Picture arch = new Picture("images/arch.jpg");
    arch.explore();
	Picture zoomed = arch.zoomUpperLeft();
	zoomed.explore();
  }
  
  /** Method to test tileMirror */
  public static void testTileMirror()
  {
    Picture berd = new Picture("images/seagull.jpg");
    berd.explore();
	Picture tiled = berd.tileMirror();
	tiled.explore();
  }
  
  /** Method to test edgeDetectionBelow */
  public static void testEdgeDetectionBelow()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan = swan.edgeDetectionBelow(50);
    swan.explore();
  }
  
   /** Method to test greenscreen */
  public static void testGreenScreen()
  {
	Picture background = new Picture("GreenScreenCatMouse/IndoorJapeneseRoomBackground.jpg");
	background.explore();
    Picture withCats = background.greenScreen();
    withCats.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    //testPixelate();
    //testBlur();
    //testEnhance();
    //testShiftRight();
    //testStairStep();
    //testTurn90();
    //testZoomUpperLeft();
    //testTileMirror();
    //testEdgeDetectionBelow();
    testGreenScreen();
  }
}
