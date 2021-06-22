package Sample.KadaiAB;
import GImage.*;

public class Sample01 {

	/**
	 * This sample program shows how to use GImage class 
	 * 
	 * @author Tagawa
	 * @param args
	 */
	public static void main(String[] args)
	{
         /**
          * 1. Read an image-file "Lenna.bmp". 
          * 2. Convert the image-file to 
          *    the instance of GImage class.
          */
		 String fileName = "Lenna.bmp";
	     GImage img= new GImage(fileName);
	     
	     /**
	      * Get the width of the image-file. 
	      */
	     int width = img.getWidth();
	     System.out.println("width="+width);
	     
	     /**
	      * Get the height of the image-file. 
	      */
	     int height = img.getHeight();
	     System.out.println("height="+height);
	     
	     /**
	      *  Set the file name to "My_Image01". 
	      */
	     String fileName01 = "jikken1";
	     
	     /**
	      *  Set the file type to "bmp". 
	      */
	     String fileType01 = "bmp";
	     
	     /**
	      * Output the image-file "My_Image01.bmp".
	      */
	     img.output(fileName01,fileType01);
	     
	     /**
	      * Set the file name to "My_Image01.bmp".
	      */
	     fileName01 +="." + fileType01;
	     System.out.println("Output file:"+fileName01);
	}
}
