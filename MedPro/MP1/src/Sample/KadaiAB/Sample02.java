package Sample.KadaiAB;
import GImage.*;

public class Sample02 {

	/**
	 * This sample program shows how to use GImage class 
	 * 
	 * @author Tagawa
	 * @param args
	 */
	public static void main(String[] args)
	{
		/**
		 *  Show the range of graduation. 
		 */
	     System.out.println("GRAY_LEVEL="+GImage.GRAY_LEVEL);
	     
	     /**
		  *  Show the maximum value of graduation. 
		  */
	     System.out.println("MAX_GRAY="+GImage.MAX_GRAY);
	     
	     /**
		  *  Show the minimum value of graduation. 
		  */
	     System.out.println("MIN_GRAY="+GImage.MIN_GRAY);
	     
         /**
          *  Make a black image-file. 
          */
	     GImage img= new GImage(200,150);
	     
	     /**
	      * Write a box with level=220.
	      */
	     int level=220;
	     img.pixel[100][100]=level;
	     img.pixel[101][100]=level;
	     img.pixel[102][100]=level;
	     img.pixel[103][100]=level;
	     img.pixel[100][101]=level;
	     img.pixel[101][101]=level;
	     img.pixel[102][101]=level;
	     img.pixel[103][101]=level;
	     img.pixel[100][102]=level;
	     img.pixel[101][102]=level;
	     img.pixel[102][102]=level;
	     img.pixel[103][102]=level;
	     img.pixel[100][103]=level;
	     img.pixel[101][103]=level;
	     img.pixel[102][103]=level;
	     img.pixel[103][103]=level;

	     /**
	      *  Set the file name to "My_Image02". 
	      */
	     String fileName02 = "My_Image02";
	     
	     /**
	      *  Set the file type to "bmp". 
	      */
	     String fileType02 = "bmp";
	     
	     /**
	      * Output the image-file "My_Image02.bmp".
	      */
	     img.output(fileName02,fileType02);
	     
	     /**
	      * Set the file name to "My_Image02.bmp".
	      */
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}
