package Sample.KadaiAB;



import GImage.GImage;



public class Sample8 {

	public static void main(String[] args)

	{

         /**

          * 1. Read an image-file "Lenna.bmp".

          * 2. Convert the image-file to

          *    the instance of GImage class.

          */

		 String fileName = "Lenna.bmp";

	     GImage img= new GImage(fileName);

	     GImage img1 = new GImage(256,256);



	     for(int i=0;i<256;i++) {

	    	 for(int j=0;j<256;j++) {

	    		 img1.pixel[i][j]=img.pixel[i][255-j];

	    	 }

	     }



	     /**

	      *  Set the file name to "sample".

	      */

	     String fileName01 = "Sample8";



	     /**

	      *  Set the file type to "bmp".

	      */

	     String fileType01 = "bmp";



	     /**

	      * Output the image-file "My_Image01.bmp".

	      */

	     img1.output(fileName01,fileType01);



	     /**

	      * Set the file name to "My_Image01.bmp".

	      */

	     fileName01 +="." + fileType01;

	     System.out.println("Output file:"+fileName01);

	}





}
