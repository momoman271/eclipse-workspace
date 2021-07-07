package Sample.LastReport;
import  GImage.GImage;
import  java.util.Random;
import java.util.concurrent.TimeUnit;	

public class Featureex {
    
	public static double get_F1(GImage img1){ //黒の割合(濃度)を返す
		int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
		double F1 = 0;
		int sum = 0;
		double area = get_area(img1);

		for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
				if(img1.pixel[y][x] == GImage.MIN_GRAY){
					sum++;
				}
			}
		}
		F1 = (double)sum / area;
		return F1;
	}
    public static double get_F2(GImage img1){ //外接矩形比を返す
        int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
		double F2 = 0;
		boolean flagY = true;
	    int Ymin=0;
	    int Ymax=0;
	    for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
	    		if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			Ymax=y;
	    			if(flagY==true){
	    				Ymin=y;
	    				flagY=false;
	    			}
	    		}
	    	 }
	     }
	     
	     boolean flagX = true;
	     int Xmin=0;
	     int Xmax=0;
	     for(int x=0;x<width1;x++){
	    	 for(int y=0;y<height1;y++){
	    		 if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			 Xmax=x;
	    			 if(flagX==true){
	    				 Xmin=x;
	    				 flagX=false;
	    			 }
	    		 }
	    	 }
	     }
         F2 = ((double)(Ymax-Ymin+1))/((double)(Xmax-Xmin+1));
	     return F2;
    }
    public static double get_F3(GImage img1){
        int height1 = img1.getHeight();	//黒画像の幅
		int width1 = img1.getWidth();	//黒画像の幅
        int black = 0;
		int white = 255;
        int Totalnumber = 0;
        double F3 = 0;
        int X = 0;

		for(int y=0;y<height1;y++) {
			for(int x=0;x<width1;x++) {
				if(img1.pixel[y][x] == black && img1.pixel[y][x-1] == white && x >= 1)
                {
					Totalnumber++;
                }
			}
		}
		F3 = (double)Totalnumber / X;
        return F3;
    }

    public static double get_XY(GImage img1) { //外接矩形のYかXの長さを返す
		int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
	     
	    /** Decide vertical frame line */
	    boolean flagY = true;
	    int Ymin=0;
	    int Ymax=0;
	    for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
	    		if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			Ymax=y;
	    			if(flagY==true){
	    				Ymin=y;
	    				flagY=false;
	    			}
	    		}
	    	 }
	     }
	     
	     boolean flagX = true;
	     int Xmin=0;
	     int Xmax=0;
	     for(int x=0;x<width1;x++){
	    	 for(int y=0;y<height1;y++){
	    		 if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			 Xmax=x;
	    			 if(flagX==true){
	    				 Xmin=x;
	    				 flagX=false;
	    			 }
	    		 }
	    	 }
	     }
	     
	     double area;
	     area = ((double)(Ymax-Ymin+1))*((double)(Xmax-Xmin+1));
	     return area;
	}

	public static double get_area(GImage img1) { //外接矩形の面積を返す
		int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
	     
	    /** Decide vertical frame line */
	    boolean flagY = true;
	    int Ymin=0;
	    int Ymax=0;
	    for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
	    		if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			Ymax=y;
	    			if(flagY==true){
	    				Ymin=y;
	    				flagY=false;
	    			}
	    		}
	    	 }
	     }
	     
	     boolean flagX = true;
	     int Xmin=0;
	     int Xmax=0;
	     for(int x=0;x<width1;x++){
	    	 for(int y=0;y<height1;y++){
	    		 if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			 Xmax=x;
	    			 if(flagX==true){
	    				 Xmin=x;
	    				 flagX=false;
	    			 }
	    		 }
	    	 }
	     }
	     
	     double area;
	     area = ((double)(Ymax-Ymin+1))*((double)(Xmax-Xmin+1));
	     return area;
	}

	public static double get_ave(double data[]) { //全ての画像の平均値を返す
		double sum = 0;
		double ave;

		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		ave = sum / ((double) data.length);

		return ave;
	}
	
	public static double get_dev(double data[]) { //全ての画像の標準偏差を返す
		double sum2 = 0;
		double sum = 0;
		double var, dev;

		for (int i = 0; i < data.length; i++) {
			sum2 = sum2 + data[i]*data[i];
			sum = sum + data[i];
		}
		var = sum2 - (sum*sum) / ((double) data.length);
		var = var / ((double) data.length);
		dev =Math.sqrt(var); 

		return dev;
	}

    public static double Max(int[] hist){ //配列の中で最大値が入っている配列の番号を返す
        int number = 0;
        for(int i = 0; i<hist.length;i++){
            if(hist[number] < hist[i]){
                number = i;
            }
        }
        return hist[number];
    }

    public static void Contrastadjust(GImage img1,double minA,double maxB){ //濃度変換
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

    public static void createhist(GImage img1,String output){ //ヒストグラム作成
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

    public static void imgoutput(GImage img,String fileName02){ //画像出力
        String fileType02 = "bmp";
        img.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    public static void contour(GImage img1,GImage img2){ //等高線用
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

    public static void Binarization(GImage img1,GImage img2,int Threshold){
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

    public static void laplacian(GImage img1,GImage img2){
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

    public static void Smoothing(GImage img1,GImage img2){
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

    public static GImage noisedelete(GImage inputimg,int range){   //rangeは原点からフィルタにする範囲
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

    public static void paint(GImage img1,GImage img2){
        int width = img1.getWidth();
        int height = img1.getHeight();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                img1.pixel[i][j] = img2.pixel[i][j];
            }
        }
    }

    public static void noisegenerator(GImage img1){
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
