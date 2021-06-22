package Sample.Kadai3;

import GImage.GImage;

public class Kadai7{
	public static void main(String[] args)
	{
	     String fileName = "house.bmp";
	     GImage img= new GImage(fileName);
         GImage img2= new GImage(fileName);

	     int width = img.getWidth();
	     int height = img.getHeight();
         double totalnumber = 0;
         int range = 1; //filterの範囲と同期する
         boolean mk = false;
         boolean mr = false;
         double[][] filter = new double[][]{
            {  0, -1,  0 },
            { -1,  4, -1 },
            {  0, -1,  0 }
        };
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    System.out.println("(x,y)=("+x+","+y+")");
                    totalnumber = 0;
                    mk = true;
                    mr = true;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            totalnumber += img.pixel[y + i][x + j] * filter[i + range][j + range];
                            if(mk){
                                System.out.println("house.bmp");
                                mk = false;
                            }
                            if(j < range){
                                System.out.print(img.pixel[y + i][x + j]+"　");
                            }
                            else{
                                System.out.println(img.pixel[y + i][x + j]);
                            }
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
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            if(mr){
                                System.out.println("Kadai3-7.bmp");
                                mr = false;
                            }
                            if(j < range){
                                System.out.print(img2.pixel[y + i][x + j]+"　");
                            }
                            else{
                                System.out.println(img2.pixel[y + i][x + j]);
                            }
                        }
                    }
                }
                else{
                    img2.pixel[y][x] = 0;
               }
            }
        }

	     String fileName02 = "kadai3-7";
	     String fileType02 = "bmp";
	     img2.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}