package Sample.Kadai3;

import GImage.GImage;

public class Kadai1{
	public static void main(String[] args)
	{
	     String fileName = "house.bmp";
	     GImage img= new GImage(fileName);
         GImage img2= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         int range = 1;
         double totalnumber = 0;
         int count =2;
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    totalnumber = 0;
                    totalnumber += img.pixel[y][x - range];
                    totalnumber -= img.pixel[y][x + range];
                    totalnumber /= count;
                    totalnumber *= 3;
                    if(totalnumber > 255){
                        totalnumber = 255;
                    }
                    if(totalnumber < 0){
                        totalnumber = 0;
                    }
                    img2.pixel[y][x] = (int)totalnumber;
                }
                else{
                    img2.pixel[y][x] = 0;
               }
            }
        }

	     String fileName02 = "kadai3-1";
	     String fileType02 = "bmp";
	     img2.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}