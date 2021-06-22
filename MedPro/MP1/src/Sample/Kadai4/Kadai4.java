package Sample.Kadai4;

import GImage.GImage;

public class Kadai4 {
    public static void main(String[] args)
    {
        String fileName = "a_wnoises.bmp";
        GImage img= new GImage(fileName);
        GImage img2= new GImage(fileName);
        GImage img3= new GImage(fileName);

        int width = img.getWidth();
        int height = img.getHeight();
        int w = GImage.MAX_GRAY;
        int range = 1; //filterの範囲と同期する
        int count = 0;
        
        double[][] filter = new double[][]{
            { 0, w, 0 },
            { w, 0, w },
            { 0, w, 0 }
        };
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            if(filter[i + range][j + range] == w && w == img.pixel[y + i][x + j]){
                                count++;
                            }
                            if(count == 4){
                                img2.pixel[y][x] = w;
                                img3.pixel[y][x] = w;
                            }
                        }
                    }
                }
                else{
                    img2.pixel[y][x] = w;
                    img3.pixel[y][x] = w;
                }
            }
        }
        boutyou(width,height,range,img2,img3);
        syuusyuku(width,height,range,img2,img3,w);
        syuusyuku(width,height,range,img2,img3,w);
        boutyou(width,height,range,img2,img3);

        String fileName02 = "kadai4-4";
        String fileType02 = "bmp";
        img3.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }
    private static void boutyou(int width,int height,int range,GImage img2,GImage img3){
        boolean mk = false;
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    mk = false;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            if(0 == img2.pixel[y + i][x + j]){
                                mk = true;
                            }
                        }
                    }
                    if(mk){
                        img3.pixel[y][x] = 0;
                    }
                }
            }
        }
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                img2.pixel[y][x] = img3.pixel[y][x];
            }
        }
    }
    private static void syuusyuku(int width,int height,int range,GImage img2,GImage img3,int w){
        boolean mk = false;
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    mk = false;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            if(w == img2.pixel[y + i][x + j]){
                                mk = true;
                            }
                        }
                    }
                    if(mk){
                        img3.pixel[y][x] = w;
                    }
                }
            }
        }
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                img2.pixel[y][x] = img3.pixel[y][x];
            }
        }
    }
}
