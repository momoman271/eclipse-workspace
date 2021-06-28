package Sample.Kadai5;

import  GImage.GImage;
import  java.util.Random;	

public class Kadai2 {
        public static void main(String[] args)
        {
        String fileName = "d850429avhrr4.bmp";
        GImage img= new GImage(fileName);
        GImage img2= new GImage(fileName);
        GImage img3= new GImage(fileName);

        int width = img.getWidth();
        int height = img.getHeight();
        int range = 2; //filterの範囲と同期する

        noisegenerator(img3);
        noisepaint(img2,img3,height,width);
        noisedelete(img, img2, img3, height, width, range);
        
        String fileName02 = "5-2-21-0801";
        String fileType02 = "bmp";
        img2.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
        
    }

    private static void noisedelete(GImage img,GImage img2,GImage img3,int height,int width,int range){
        int[] median = new int[25];
        int count = 0;
        int c = 0;
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            median[count] = img3.pixel[y + i][x + j];
                            count++;
                            if(count == median.length){
                                for(int a=0;a<median.length;a++){
                                    for(int b=median.length-1;b>=0;b--){
                                        if(median[a] > median[b]){
                                            c = median[a];
                                            median[a] = median[b];
                                            median[b] = c;
                                        }
                                    }
                                }
                                img2.pixel[y][x] = median[(median.length-1)/2];
                            }
                        }
                    }
                }
                else{
                    img2.pixel[y][x] = GImage.MIN_GRAY;
                }
            }
        }
    }

    private static void noisepaint(GImage img2,GImage img3,int height ,int width){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                img2.pixel[i][j] = img3.pixel[i][j];
            }
        }
        String fileName02 = "kakunin";
        String fileType02 = "bmp";
        img2.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    private static void noisegenerator(GImage img3){
        final	int	randomSeed	=	1;							//	乱数種を１（ロボットTAは１で設定のため，変更しないこと）
		Random	rand	=	new	Random(randomSeed); //	乱数ジェネレータを作る
        for	(int i=0; i<500; i++)	{	 							//	500個のノイズを作成
                int	y	=	rand.nextInt(img3.getHeight()	);		//	y座標計算:0〜input.getHeight()-1の乱数生成
                int	x	=	rand.nextInt(img3.getWidth	()	);		//	x座標計算:0〜input.getWidth()	-1の乱数生成
                int	value;	
                if(rand.nextBoolean()) 		//	true	か	false	を	1/2	の確率で生成
                            value	=	GImage.MIN_GRAY;	
                else	
                            value	=	GImage.MAX_GRAY;	
                img3.pixel[y][x]	=	value;	
		}
        String fileName02 = "noise";
        String fileType02 = "bmp";
        img3.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }
}