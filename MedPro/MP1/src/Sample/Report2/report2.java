package Sample.Report2;

import  GImage.GImage;
import  java.util.Random;
import java.util.concurrent.TimeUnit;	

public class report2 {
        public static void main(String[] args)
        {
        String outputName = "report2";

        String fileName = "d850429avhrr4.bmp";
        GImage img1= new GImage(fileName);
        
        Timemeasure();
    }
    private static void createcontour(GImage img,GImage img2,GImage img3,GImage img4,GImage img5,int range){
        noisegenerator(img);
        paint(img2, img);
        noisedelete(img, range);
        paint(img, img2);
        Smoothing(img2, img);
        paint(img, img2);
        for(int o = 256/16;o <= 256/16*15;o+=256/16){
            paint(img3, img);
            paint(img4, img);
            Binarization(img2,img3,o);
            paint(img4, img3);
            laplacian(img3,img4);
            contour(img5,img4);
        }
    }

    private static void milltime(){
        for(int i=0;i<20;i++){
            long starttime = System.currentTimeMillis();
            
            long endtime = System.currentTimeMillis();
            System.out.println("開始時刻:"+starttime+"ms");
            System.out.println("終了時刻:"+endtime+"ms");
            System.out.println("処理時間:"+(endtime - starttime)+"ms");
        }
    }

    private static void Checkaccuracy(){
        for(int i=3;i>0;i--){
            System.out.println(i+"秒前");
            try {
                Thread.sleep(1000);
            } 
            catch (InterruptedException e) {
            }
        }
        System.out.println("開始");
        long starttime = System.currentTimeMillis();
        try {
            Thread.sleep(20000);
        } 
        catch (InterruptedException e) {
        }
        long endtime = System.currentTimeMillis();
        
        System.out.println("処理時間:"+(endtime - starttime)+"ms");
        System.out.println("終了");
    }

    private static void Timemeasure(){
        String fileName = "d850429avhrr4.bmp";
        GImage inputimg= new GImage(fileName);
        int range = 1;
        int repeat = 20;

        for(int i=0;i<repeat;i++){
            long starttime = System.currentTimeMillis();
        
            noisedelete(inputimg, range);
        
            long endtime = System.currentTimeMillis();
            System.out.println((endtime - starttime));
        }
    }

    private static double Max(int[] hist){
        int number = 0;
        for(int i = 0; i<hist.length;i++){
            if(hist[number] < hist[i]){
                number = i;
            }
        }
        return hist[number];
    }

    private static void Contrastadjust(GImage img1,double minA,double maxB){
        int width = img1.getWidth();
        int height = img1.getHeight();
        double minC = 0;
        double maxD = 255;
        double i = 0;

        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                i = minC + ((maxD-minC) / (maxB-minA)) * ((double)img1.pixel[y][x] - minA);
                if(i < 0){
                    img1.pixel[y][x] = 0;
                }
                else if(i >= 256){
                    img1.pixel[y][x] = 255;
                }
                else{
                    img1.pixel[y][x] = (int)i;
                }
            }
        }

    }

    private static void createhist(GImage img1,String output){
    //濃度値0以下と255以上を無視する
        GImage board = new GImage(256,256);
        int hist[] = new int[256];
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = board.getWidth();
        int height2 = board.getHeight();

        for (int val=0; val<256; val++){
            hist [val]=0;
        } 
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                if(img1.pixel[y][x] != 0 && img1.pixel[y][x] != 255)
                hist[img1.pixel[y][x]] += 1;
            }
        }
        for(int y=0; y<height2; y++){
            for(int x=0; x<width2; x++){
                board.pixel[y][x] = 0;
            }
        }
        //横軸ヒストグラム
        /*
        for(int y=0; y<height2; y++){
            for(double x=0; x<hist[y] * (256/Max(hist));x++){
                if(x>=0 && x<width2 && y>=0 && y<height2)
                board.pixel[y][(int)x] = 255;
            }
        }
        */
        //縦軸ヒストグラム
        for(int x=0; x<width2; x++){
            for(int y=height2 - 1; y>height2 - 1 - (hist[x]  * (256/Max(hist)));y--){
                if(x>=0 && x<width2 && y>=0 && y<height2)
                board.pixel[y][x] = 255;
            }
        }
        //imgoutput(board,output + /*"-21-0801"*/"-histgram");
    }

    private static void imgoutput(GImage img,String fileName02){
        String fileType02 = "bmp";
        img.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    private static void contour(GImage img1,GImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(img2.pixel[i][j] != GImage.MIN_GRAY){
                    img1.pixel[i][j] = GImage.MIN_GRAY;
                }
            }
        }
    }

    private static void Binarization(GImage img1,GImage img2,int Threshold){
        int width = img1.getWidth();
        int height = img1.getHeight();
        for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				if(img1.pixel[y][x] < Threshold){
					img2.pixel[y][x]=GImage.MIN_GRAY;
				}
				else{
					img2.pixel[y][x]=GImage.MAX_GRAY;
				}
			}
		}
    }

    private static void laplacian(GImage img1,GImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();
        double totalnumber = 0;
         int range = 1; //filterの範囲と同期する
         double[][] filter = new double[][]{
            {  0,  1,  0 },
            {  1, -4,  1 },
            {  0,  1,  0 }
        };
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    totalnumber = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            totalnumber += img1.pixel[y + i][x + j] * filter[i + range][j + range];
                        }
                    }
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
        String fileName02 = "kakunin";
        String fileType02 = "bmp";
        img2.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    private static void Smoothing(GImage img1,GImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();
        int range = 1;
        double totalnumber = 0;
        int count =0;
        
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    totalnumber = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            totalnumber += img2.pixel[y + i][x + j];
                            count++;
                        }
                    }
                    totalnumber /= count;
                    img1.pixel[y][x] = (int)totalnumber;
                }
                else{
                    img1.pixel[y][x] = 0;
                }
            }
        }
    }

    private static GImage noisedelete(GImage inputimg,int range){   //rangeは原点からフィルタにする範囲
        long importstart = System.currentTimeMillis();
        int width = inputimg.getWidth();
        int height = inputimg.getHeight();
        GImage outputimg = new GImage(height,width);    //出力用画像を生成
        int[] median = new int[(int)Math.pow(2*range+1 , 2)];//入力された範囲からフィルタ用の配列を生成
        int count;  //フィルタに画素値を挿入していくためのカウント用変数
        int exchange;   //ソートで使う交換用変数
        long importend = System.currentTimeMillis();
        long processstart = System.currentTimeMillis();
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            median[count] = inputimg.pixel[y + i][x + j];
                            count++;
                            if(count == median.length){
                                for(int a=0;a<median.length-1;a++){
                                    for(int b=median.length-1;b>a;b--){
                                        if(median[b] > median[b-1]){
                                            exchange = median[b];
                                            median[b] = median[b-1];
                                            median[b-1] = exchange;
                                        }
                                    }
                                }
                                outputimg.pixel[y][x] = median[(median.length-1)/2];
                            }
                        }
                    }
                }
                else{
                    outputimg.pixel[y][x] = GImage.MIN_GRAY;
                }
            }
        }
        long processend = System.currentTimeMillis();
        System.out.println("変数作成時間"+(importend - importstart));
        System.out.println("処理時間"+(processend - processstart));
        return outputimg;
    }
/*        
    
    quickSort(median);
*/
    private static void paint(GImage img1,GImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                img1.pixel[i][j] = img2.pixel[i][j];
            }
        }
    }

    private static void noisegenerator(GImage img1){
        final	int	randomSeed	=	1;							//	乱数種を１（ロボットTAは１で設定のため，変更しないこと）
		Random	rand	=	new	Random(randomSeed); //	乱数ジェネレータを作る
        for	(int i=0; i<500; i++)	{	 							//	500個のノイズを作成
                int	y	=	rand.nextInt(img1.getHeight()	);		//	y座標計算:0〜input.getHeight()-1の乱数生成
                int	x	=	rand.nextInt(img1.getWidth	()	);		//	x座標計算:0〜input.getWidth()	-1の乱数生成
                int	value;	
                if(rand.nextBoolean()) 		//	true	か	false	を	1/2	の確率で生成
                            value	=	GImage.MIN_GRAY;	
                else	
                            value	=	GImage.MAX_GRAY;	
                img1.pixel[y][x]	=	value;	
		}
        String fileName02 = "noise";
        String fileType02 = "bmp";
        img1.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    static int pivot(int[] a,int i,int j){
        int k=i+1;
        while(k<=j && a[i]==a[k]) k++;
        if(k>j) return -1;
        if(a[i]>=a[k]) return i;
        return k;
    }
    static int partition(int[] a,int i,int j,int x){
        int l=i,r=j;
        while(l<=r){
            while(l<=j && a[l]<x)  l++;
            while(r>=i && a[r]>=x) r--;
            if(l>r) break;
            int t=a[l];
            a[l]=a[r];
            a[r]=t;
            l++; r--;
        }
        return l;
    }
    public static void quickSort(int[] a,int i,int j){
        if(i==j) return;
        int p=pivot(a,i,j);
        if(p!=-1){
            int k=partition(a,i,j,a[p]);
            quickSort(a,i,k-1);
            quickSort(a,k,j);
        }
    }
    public static void quickSort(int[] array){ //呼び出し用
        quickSort(array,0,array.length-1);
    }
}