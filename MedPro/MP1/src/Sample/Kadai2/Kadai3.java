package Sample.Kadai2;

import GImage.GImage;

public class Kadai3{
	public static void main(String[] args)
	{
	     String fileName = "lenna_wblip.bmp";
	     GImage img= new GImage(fileName);
         GImage img2= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         int range = 1;
         double totalnumber = 0;
         int count = 0;
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 12;
                    totalnumber = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            if(i == 0 && j == 0){
                                totalnumber += 4*img.pixel[y + i][x + j];
                            }
                            else{
                                totalnumber += img.pixel[y + i][x + j];
                            }
                        }
                    }
                    totalnumber /= count;
                    img2.pixel[y][x] = (int)totalnumber;
                }
                else{
                    img2.pixel[y][x] = 0;
                }
            }
        }

	     String fileName02 = "kadai2-4";
	     String fileType02 = "bmp";
	     img2.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}