import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
		System.out.print("image = "); print(image);
		System.out.println();

	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String cake) {
		In in = new In(cake);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int row=0;row<numRows;row++){
			for(int col=0;col<numCols;col++){
				int r=in.readInt();
				int b=in.readInt();
				int g=in.readInt();
				Color c=new Color(r, g, b);
				image[row][col]=c;
			}
		}
		return null;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int row=0;row<image.length;row++){
			for(int col=0;col<image[row].length;col++){
				System.out.println("("+image[row][col].getRed()+ image[row][col].getBlue()+image[row][col].getGreen()+")");
	}
}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		
	Color[][] flippedImageH = new Color[image.length][image[0].length];
	for (int row=0;row<image.length;row++){
		int indexCol=0;
	   for(int col=image[row].length-1;col>=0;col--){
		   flippedImageH[row][indexCol]=image[row][col];
		   indexCol++;
		   
	   }
   }
   
   return flippedImageH;
}
		
		
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		if(image.length==0){
			return null;
		}
		Color[][] flippedImageV = new Color[image.length][image[0].length];
		int indexRow=0;
		for (int row=image.length-1;row>=0;row--){
		   for(int col=0;col<image[row].length;col++){
			   flippedImageV[indexRow][col]=image[row][col];
			   
		   }
		   indexRow++;
	   }
	   
	   return flippedImageV;
   }
		
		
		
		
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int r=pixel.getRed();
		int b=pixel.getBlue();
		int g= pixel.getGreen();
		int lum=(int)(0.299*r+0.587*g+0.114*b);
		Color lumColor=new Color(lum,lum,lum);

		return lumColor;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		if(image.length==0){
			return null;
		}
		Color[][] grayColored = new Color[image.length][image[0].length];
		 for (int row=0;row<image.length;row++){
			for(int col=0;col<image[row].length;col++){
				Color turnGray=new Color(image[row][col].getRed(), image[row][col].getBlue(),image[row][col].getGreen());
				 turnGray=luminance(turnGray);
				 grayColored[row][col]=turnGray;

			}
			}
		
		return grayColored;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		if(image.length==0){
			return null;
		}
		Color[][] scaledImage = new Color[height][width];
		//int indexRow=0;
		int rangeScaledRow=(int)(image.length/height);
		int rangeScaledCol=(int)(image[0].length/width);
		for (int row=0;row<height;row++){
			//int indexCol=0;
			for(int col=0;col<width;col++){
				scaledImage[row][col]=image[row*rangeScaledRow][col*rangeScaledCol];

			}
		}
		
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		Color newColor=new Color(((int)(alpha*(c1.getRed())+(1-alpha)*(c2.getRed()))),((int)(alpha*(c1.getBlue())+(1-alpha)*(c2.getBlue()))),((int)(alpha*(c1.getGreen())+(1-alpha)*(c2.getGreen()))));
		if(!(newColor.getRed()>=0&&newColor.getRed()<=255)){
		return null;
		}
		if(!(newColor.getBlue()>=0&&newColor.getBlue()<=255)){
		return null;
		}
		if(!(newColor.getGreen()>=0&&newColor.getGreen()<=255)){
		return null;
		}
		return newColor;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		if(image1.length!=image2.length||image1[0].length!=image2[0].length){
			return null;
		}
		Color[][] blendedImage = new Color[image1.length][image1[0].length];
		for (int row=0;row<image1.length;row++){
			for(int col=0;col<image1[row].length;col++){
				blendedImage[row][col]=blend(image1[row][col],image2[row][col],alpha);
			}
		}
		
		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		if(source.length==0||target.length==0){
			System.out.print("not valid input");
		}
		double alpha=n;
		Color[][] morphedImage=new Color[source.length][source[0].length];
		target=scaled(target, source[0].length,source.length);
		
		while(alpha!=0){
			
			for (int row=0;row<morphedImage.length;row++){
			for(int col=0;col<morphedImage[row].length;col++){
				morphedImage[row][col]=blend(source[row][col],target[row][col],alpha);
				print(morphedImage[row][col]);
			}
		}
		for(int i=0;i<n;i++)
		alpha=(double)((n-i)/n);
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

