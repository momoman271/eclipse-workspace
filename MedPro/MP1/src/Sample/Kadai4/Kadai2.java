package Sample.Kadai4;

import GImage.GImage;

public class Kadai2 {
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
        boolean mk = false;
        double[][] filter = new double[][]{
            { 0, w, 0 },
            { w, 0, w },
            { 0, w, 0 }
        };
        double[][] filter2 = new double[][]{
            { w, w, w },
            { w, 0, w },
            { w, w, w }
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

        String fileName02 = "kadai4-2";
        String fileType02 = "bmp";
        img3.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }
}
