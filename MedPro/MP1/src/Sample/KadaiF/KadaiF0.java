package Sample.KadaiF;

import GImage.GImage;

public class KadaiF0 {
	public static void main(String[] args)
	{
	     String fileName = "Board1.bmp";
	     GImage img= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         int y0 = 200;
         int x0 = 150;
         double R = 40*Math.sqrt(2);
         double b = 0.0;
         double c = 0.0;
         double r = 0;
         r = R / 180;

        /*for(int i=0;i<300;i++) {
            b=R*Math.sin(Math.toRadians(i));
        
            img.pixel[(int)b + y0][i]=0;
        }*/
              
        for(int i=0;i<360;i++) {
            b=(R)*Math.sin(Math.toRadians(i));
            c=(R)*Math.cos(Math.toRadians(i));
            int Y = (int)b + y0;
            int X = (int)c + x0;
            img.pixel[Y][X] = 0;
        }

	     String fileName02 = "kadaiF-0";
	     String fileType02 = "bmp";
	     img.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}