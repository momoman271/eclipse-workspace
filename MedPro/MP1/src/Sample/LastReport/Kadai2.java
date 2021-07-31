package Sample.LastReport;

import  GImage.GImage;

import java.util.Arrays;

public class Kadai2 {
    public static void main(String[] args)
    {
        final int normalnum = 3;
        final int abnormalnum = 3;
        identification[] normal = new identification[normalnum];
        identification[] abnormal = new identification[abnormalnum];

        for(int i=0; i<normalnum+abnormalnum; i++){ //全画像データから特徴抽出
            String fileName;
            if(i<normalnum){
                fileName = "last2data/normal/n" + Integer.toString(i+1) + ".bmp";
                GImage img= new GImage(fileName);
                normal[i] = new identification(img);
                
            }
            else {
                fileName = "last2data/abnormal/ab" + Integer.toString(i+1-normalnum) + ".bmp";
                GImage img= new GImage(fileName);
                abnormal[i - normalnum] = new identification(img);
            }
        }

        double[][] f = new double[6][16];
        int count=16;

        for(int i=0;i<3;i++){
            int[] num = normal[i].evaluation();
            count = num.length;
            for(int j=0;j<num.length;j++){
                f[i][j] = num[j];
            }
        }
        for(int i=0;i<3;i++){
            int[] num = abnormal[i].evaluation();
            for(int j=0;j<num.length;j++){
                f[i+3][j] = num[j];
            }
        }

        double[] dist = new double[6];
        double total = 0;
        int select = 6;
        
        for(int i=0;i<normalnum+abnormalnum;i++){
            total = 0;
            for(int j=0;j<count;j++){
                total += (f[select-1][j] - f[i][j]) * (f[select-1][j] - f[i][j]);
            }
            dist[i] = Math.sqrt(total);
        }
        double[] rank = new double[dist.length];
        for(int i=0;i<rank.length;i++){
            rank[i] = dist[i];
        }
        Arrays.sort(dist);
        int[] result = new int[normalnum+abnormalnum];
        
        for(int i=0;i<normalnum+abnormalnum;i++){
            for(int j=0;j<normalnum+abnormalnum;j++){
                if(dist[i] == rank[j]){
                    result[i] = j+1;
                }
            }
        }
        for(int i=0;i<result.length;i++){
            if(result[i] <= 3){
                System.out.println("normal:"+result[i]);
            }
            else{
                System.out.println("abnormal:"+result[i]);
            }
            System.out.println("距離:"+dist[i]);
        }
    }
}

class identification {
    
    GImage img;
    final int width;
    final int height;

    identification(GImage img){ //コンストラクタ
        width = img.getWidth();
        height = img.getHeight();
        this.img = img;
    }
    int[] evaluation(){
        int[][] filter = new int[3][3];
        int range = 1;
        int[] pm = new int[16];
        int x_count;
        int y_count;

        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            filter[i+range][j+range] = this.img.pixel[y + i][x + j];
                        }
                    }
                    x_count = 0;
                    y_count = 0;
                    for(int i=0;i<8;i++){
                        if(i == 3 || i == 5){
                            y_count++;
                            x_count = 0;
                        }
                        
                        if(filter[y_count][x_count] > filter[1][1]){
                            pm[i]++;
                        }
                        else if(filter[y_count][x_count] < filter[1][1]){
                            pm[i + 8]++;
                        }
                        if(i == 3){
                            x_count += 2;
                        }
                        else{
                            x_count++;
                        }
                    }
                }
            }
        }
        return pm;
    }

    

    void imgoutput(String fileName02){ //画像出力
        String fileType02 = "bmp";
        img.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }

    void contour(GImage img,GImage outputimg){
        int width = img.getWidth();
        int height = img.getHeight();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(img.pixel[i][j] != GImage.MIN_GRAY){
                    outputimg.pixel[i][j] = GImage.MIN_GRAY;
                }
            }
        }
    }

    GImage Binarization(int Threshold){
        GImage outputimg = this.img;
        for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				if(this.img.pixel[y][x] < Threshold){
					outputimg.pixel[y][x]=GImage.MIN_GRAY;
				}
				else{
					outputimg.pixel[y][x]=GImage.MAX_GRAY;
				}
			}
		}
        return outputimg;
    }

    GImage laplacian(){
        GImage img = this.img;
        int width = img.getWidth();
        int height = img.getHeight();
        GImage outputimg = img;
        double totalnumber = 0;
         int range = 1; //filterの範囲と同期する
         double[][] filter = new double[][]{
            {  1,  1,  1 },
            {  1, -8,  1 },
            {  1,  1,  1 }
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
                    if(totalnumber > 255){
                        totalnumber = 255;
                    }
                    if(totalnumber < 0){
                        totalnumber = 0;
                    }
                    outputimg.pixel[y][x] = (int)totalnumber;
                }
                else{
                    outputimg.pixel[y][x] = 0;
               }
            }
        }
        outputimg.output("outputimg","bmp");
        return outputimg;
    }

    GImage Smoothing(GImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        GImage outputimg = img;
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
                            totalnumber += img.pixel[y + i][x + j];
                            count++;
                        }
                    }
                    totalnumber /= count;
                    outputimg.pixel[y][x] = (int)totalnumber;
                }
                else{
                    outputimg.pixel[y][x] = 0;
                }
            }
        }
        return outputimg;
    }

    GImage noisedelete(GImage img,int range){   //rangeは原点からフィルタにする範囲
        int width = img.getWidth();
        int height = img.getHeight();
        GImage outputimg = img;    //出力用画像を生成
        int[] median = new int[(int)Math.pow(2*range+1 , 2)];//入力された範囲からフィルタ用の配列を生成
        int count;  //フィルタに画素値を挿入していくためのカウント用変数
        int exchange;   //ソートで使う交換用変数
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                if(y-range >= 0 && y+range < height && x-range >= 0 && x+range < width){
                    count = 0;
                    for(int i=-range;i <= range;i++){
                        for(int j=-range;j <= range;j++){
                            median[count] = img.pixel[y + i][x + j];
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
        return outputimg;
    }

    GImage Contrastadjust(double minA,double maxB){
        GImage img1 = this.img;
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
        this.img = img1;
        return img1;
    }
    void createhist(){
            GImage board = new GImage(256,256);
            int hist[] = new int[256];
            int width2 = board.getWidth();
            int height2 = board.getHeight();
    
            for (int val=0; val<256; val++){
                hist [val]=0;
            } 
            for(int y=0; y<height; y++){
                for(int x=0; x<width; x++){
                    if(img.pixel[y][x] > 20 && img.pixel[y][x] != 255)
                    hist[img.pixel[y][x]] += 1;
                }
            }
            for(int y=0; y<height2; y++){
                for(int x=0; x<width2; x++){
                    board.pixel[y][x] = 0;
                }
            }
            for(int y=0; y<height2; y++){
                for(double x=0; x<hist[y] * (256/Max(hist));x++){
                    if(x>=0 && x<width2 && y>=0 && y<height2)
                    board.pixel[y][(int)x] = 255;
                }
            }
            board.output("imgoutput/-hist","bmp");
        }
        double Max(int[] hist){
            int number = 0;
            for(int i = 0; i<hist.length;i++){
                if(hist[number] < hist[i]){
                    number = i;
                }
            }
            return hist[number];
        }
}
