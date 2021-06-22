package Sample.KadaiAB;

import GImage.GImage;



public class Sample6 {



	/**

	 * This sample program shows how to use GImage class 

	 * 

	 * @author Tagawa

	 * @param args

	 */

	public static void main(String[] args)

	{
	     String fileName = "Lenna.bmp";

	     GImage img= new GImage(fileName);

	     

	     /**

	      * Write a box with level=220.

	      */

	     int level=0;

	     for(int i=0;i<256;i+=10) {

	    	 for(int j=0;j<256;j++) {

	    		 img.pixel[i][j]=level;
	    		 
	    	 }

	     }
	     /**

	      *  Set the file name to "My_Image02". 

	      */

	     String fileName02 = "kadaib0";

	     

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