package Sample.Kadai2;

import GImage.GImage;

public class Kadai1{
	public static void main(String[] args)
	{
	     String fileName = "lenna_wblip.bmp";
	     GImage img= new GImage(fileName);
         GImage img2= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         int range = 2;
         double totalnumber = 0;
         int count =0;
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    totalnumber = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            totalnumber += img.pixel[y + i][x + j];
                            count++;
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

	     String fileName02 = "kadai2-2";
	     String fileType02 = "bmp";
	     img2.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}