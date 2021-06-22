package Sample.Kadai3;

import GImage.GImage;

public class Kadai6{
	public static void main(String[] args)
	{
	     String fileName = "house.bmp";
	     GImage img= new GImage(fileName);
         GImage img2= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         double totalnumber = 0;
         int range = 1; //filterの範囲と同期する
         double[][] filter = new double[][]{
            { -1, -1, -1 },
            { -1,  8, -1 },
            { -1, -1, -1 }
        };
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    totalnumber = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            totalnumber += img.pixel[y + i][x + j] * filter[i + range][j + range];
                        }
                    }
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

	     String fileName02 = "kadai3-6";
	     String fileType02 = "bmp";
	     img2.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}