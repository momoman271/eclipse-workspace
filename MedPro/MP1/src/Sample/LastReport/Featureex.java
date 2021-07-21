package Sample.LastReport;
import  GImage.GImage;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Featureex {
    public static int fenumselect(int[] select) {
        int fenum = 0;
        for(int i=0;i<select.length;i++){
            if(select[i] != 0){
                fenum++;
            }
        }
        return fenum;
    }
    public static int[] select(int select,int fenum){
        int[] selectnumber = new int[fenum];
        int j = fenum - 1;
        int index = 1;
        for(int i = 1 ; i < selectnumber.length ; i++){
            index = index * 10;
        }
        for(int i=index;i>0;i/=10){
            selectnumber[j] = select / i;
            select = select % i;
            j--;
        }
        return selectnumber;
    }
    public static int inputkeyboard(int i){
        if(i == 0){
            System.out.println(
            "検索に利用する特徴を「68341」のように入力してください"+line+
            "1:濃度値"+line+
            "2:縦横比"+line+
            "3:水平方向ラン数平均"+line+
            "4:垂直方向ラン数平均"+line+
            "5:水平方向ラン数標準偏差"+line+
            "6:垂直方向ラン数標準偏差"+line+
            "7:重心のx座標"+line+
            "8:重心のy座標"+line
            );
        }
        if(i == 1){
            System.out.println(
            "検索に利用するキー画像番号を入力してください"+line
            );
        }
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        

        if(i == 0){
            System.out.println("入力された文字は「" + num + "」です");
        }
        if(i == 1){
            System.out.println("選択された画像は「" + num + ".bmp」です");
            scan.close();
        }
        return num;
    }
    public static void filewrite(int[] data,int[] selectnumber,int selectpicture,double[][] Feature,double[] distance,int fenum,double[][] outputFeature,int fenum2){
        String path = "Lastreportoutput\\output.txt";
        
        try{
            File file = new File(path);
            FileWriter writefile = new FileWriter(file);
            writefile.write(
                    "検索に使用した特徴量: "
            );
            for(int j=fenum-1;j>=0;j--){
                switch (selectnumber[j]) {
                    case 1:
                        writefile.write("濃度");
                        break;
                    case 2:
                        writefile.write("縦横比");
                        break;
                    case 3:
                        writefile.write("水平方向ラン数平均");
                        break;
                    case 4:
                        writefile.write("垂直方向ラン数平均");
                        break;
                    case 5:
                        writefile.write("水平方向ラン数標準偏差");
                        break;
                    case 6:
                        writefile.write("垂直方向ラン数標準偏差");
                        break;
                    case 7:
                        writefile.write("重心のx座標");
                        break;
                    case 8:
                        writefile.write("重心のy座標");
                        break;
                    default:
                        break;
                }
                if(j > 0){
                    writefile.write(",　");
                }
            }
            writefile.write(
                line+"検索キーの画像番号: "+selectpicture+".bmp"+line
            );
            for(int i=0;i<data.length;i++){
                String graph = String.valueOf(data[i]);
                String distan = String.valueOf(distance[data[i]-1]);
                writefile.write(
                    "検索結果第"+(i+1)+"位: "+graph+".bmp　キー画像との距離: "+distan+",　"
                );
                for(int j=0;j<fenum2;j++){
                    String feature = String.valueOf(outputFeature[j][i]);
                    switch (j) {
                        case 0:
                            writefile.write("濃度: "+feature);
                            break;
                        case 1:
                            writefile.write("縦横比: "+feature);
                            break;
                        case 2:
                            writefile.write("水平方向ラン数平均: "+feature);
                            break;
                        case 3:
                            writefile.write("垂直方向ラン数平均: "+feature);
                            break;
                        case 4:
                            writefile.write("水平方向ラン数標準偏差: "+feature);
                            break;
                        case 5:
                            writefile.write("垂直方向ラン数標準偏差: "+feature);
                            break;
                        case 6:
                            writefile.write("重心のx座標: "+feature);
                            break;
                        case 7:
                            writefile.write("重心のy座標: "+feature);
                            break;
                        default:
                            break;
                        }
                    if(j < fenum2 -1){
                        writefile.write(",　");
                    }
                    else{
                        writefile.write(line);
                    }
                }
            }

            writefile.close();
          }
        catch(IOException e){
        }
    }
    public static int[] get_maxes(int number,double data[]){
        double[] posedata = new double[data.length];
        int[] maxes = new int[number];
        for(int i=0;i<data.length;i++){
            posedata[i] = data[i];
        }
        
        Arrays.sort(posedata);

        for(int i=0;i<maxes.length;i++){
            for(int j=0;j<posedata.length;j++){
                if(posedata[i + 1] == data[j]){
                    maxes[i] = j + 1;
                }
            }
        }
        return maxes;
    }
    public static double get_distance(int fenum,double[] inputdata,double[] comparedata){
        double distance = 0;
        double total = 0;
        for(int i=0;i<fenum;i++){
            total += (inputdata[i]-comparedata[i]) * (inputdata[i]-comparedata[i]);
        }
        distance = Math.sqrt(total); 
        return distance;
    }
    public static double[] get_fescalr(int fenum,int num,double[][] data){ //1枚の画像の特徴を配列化
        double[] newdata = new double[num];
        for(int i=0;i<num;i++){
            newdata[i] = data[i][fenum];
        }
        return newdata;
    }
    public static double[] get_scalr(int fenum,int num,double[][] data){
        double[] newdata = new double[num];
        for(int i=0;i<num;i++){
            newdata[i] = data[fenum][i];
        }
        return newdata;
    }
    public static double get_Feature(int fenum,GImage img,int[] selectnumber){

        if(selectnumber[fenum] == 1){
            return Featureex.get_F1(img);
        }
        if(selectnumber[fenum] == 2){
            return Featureex.get_F2(img);
        }
        if(selectnumber[fenum] == 3){
            return Featureex.get_F3(img);
        }
        if(selectnumber[fenum] == 4){
            return Featureex.get_F4(img);
        }
        if(selectnumber[fenum] == 5){
            return Featureex.get_F5(img);
        }
        if(selectnumber[fenum] == 6){
            return Featureex.get_F6(img);
        }
        if(selectnumber[fenum] == 7){
            return Featureex.get_F7(img);
        }
        if(selectnumber[fenum] == 8){
            return Featureex.get_F8(img);
        }
        return 0;
    }
	public static double get_F1(GImage img1){ 
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
    public static double get_F2(GImage img1){ 
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
        int Totalnumber = 0;
        double F3 = 0;
        int MinX = get_area(img1, "MinX");
        int MaxX = get_area(img1, "MaxX");
        int MinY = get_area(img1, "MinY");
        int MaxY = get_area(img1, "MaxY");
        double Ynumber = MaxY - MinY + 1;

        for(int y=MinY;y<=MaxY;y++) {
			for(int x=MinX;x<=MaxX;x++) {
				if(img1.pixel[y][x] == GImage.MIN_GRAY && img1.pixel[y][x-1] == GImage.MAX_GRAY)
                {
					Totalnumber++;
                }
			}
		}
		
		F3 = (double)Totalnumber / Ynumber;
        return F3;
    }
    public static double get_F4(GImage img1){
        int Totalnumber = 0;
        double F4 = 0;
        int MinX = get_area(img1, "MinX");
        int MaxX = get_area(img1, "MaxX");
        int MinY = get_area(img1, "MinY");
        int MaxY = get_area(img1, "MaxY");
        double Xnumber = MaxX - MinX + 1;

		for(int x=MinX;x<=MaxX;x++) {
			for(int y=MinY;y<=MaxY;y++) {
				if(img1.pixel[y][x] == GImage.MIN_GRAY && img1.pixel[y-1][x] == GImage.MAX_GRAY)
                {
					Totalnumber++;
                }
			}
		}
		F4 = (double)Totalnumber / Xnumber;
        return F4;
    }
    public static double get_F5(GImage img1){
        double F5 = 0;
        int MinX = get_area(img1, "MinX");
        int MaxX = get_area(img1, "MaxX");
        int MinY = get_area(img1, "MinY");
        int MaxY = get_area(img1, "MaxY");
        double Ynumber = MaxY - MinY + 1;
        double[] data = new double[(int)Ynumber];

        for(int y=MinY;y<=MaxY;y++) {
			for(int x=MinX;x<=MaxX;x++) {
				if(img1.pixel[y][x] == GImage.MIN_GRAY && img1.pixel[y][x-1] == GImage.MAX_GRAY)
                {
					data[y - MinY]++;
                }
			}
		}
		F5 = get_dev(data);
        return F5;
    }
    public static double get_F6(GImage img1){
        double F6 = 0;
        int MinX = get_area(img1, "MinX");
        int MaxX = get_area(img1, "MaxX");
        int MinY = get_area(img1, "MinY");
        int MaxY = get_area(img1, "MaxY");
        double Xnumber = MaxX - MinX + 1;
        double[] data = new double[(int)Xnumber];

		for(int x=MinX;x<=MaxX;x++) {
			for(int y=MinY;y<=MaxY;y++) {
				if(img1.pixel[y][x] == GImage.MIN_GRAY && img1.pixel[y-1][x] == GImage.MAX_GRAY)
                {
                    data[x - MinX]++;
                }
			}
		}
		F6 = get_dev(data);
        return F6;
    }
    public static double get_F7(GImage img1){
        int Totalnumber = 0;
        int xtotal = 0;
        double F7 = 0;
        int MinX = (int)get_area(img1, "MinX");
        int MaxX = (int)get_area(img1, "MaxX");
        int MinY = (int)get_area(img1, "MinY");
        int MaxY = (int)get_area(img1, "MaxY");

        for(int y=MinY;y<=MaxY;y++){
            for(int x=MinX;x<=MaxX;x++){
                if(img1.pixel[y][x] == GImage.MIN_GRAY){
                    xtotal += x-MinX;
                    Totalnumber++;
                }
            }
        }

        F7 = (double)xtotal / (double)Totalnumber;
        return F7;
    }
    public static double get_F8(GImage img1){
        int Totalnumber = 0;
        int ytotal = 0;
        double F8 = 0;
        int MinX = (int)get_area(img1, "MinX");
        int MaxX = (int)get_area(img1, "MaxX");
        int MinY = (int)get_area(img1, "MinY");
        int MaxY = (int)get_area(img1, "MaxY");

        for(int x=MinX;x<=MaxX;x++){
            for(int y=MinY;y<=MaxY;y++){
                if(img1.pixel[y][x] == GImage.MIN_GRAY){
                    ytotal += y-MinY;
                    Totalnumber++;
                }
            }
        }

        F8 = (double)ytotal / (double)Totalnumber;
        return F8;
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
    public static int get_area(GImage img1,String where) { //外接矩形のMinX,MinY,MaxX,MaxYを返す
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
	     switch (where){
            case "MinY":
                return Ymin;
            case "MaxY":
                return Ymax;
            case "MinX":
                return Xmin;
            default:
                return Xmax;
          }
	}
    public static double get_nor(double target,double data[]){
        double normalized;
        normalized = (target - get_ave(data)) / get_dev(data);
        return normalized;
    }
	public static double get_ave(double data[]) { //全てのデータの平均値を返す
		double sum = 0;
		double ave;

		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		ave = sum / ((double) data.length);

		return ave;
	}
	public static double get_dev(double data[]) { //全てのデータの標準偏差を返す
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
    public static void imgoutput(GImage img,String fileName02){ //画像出力
        String fileType02 = "bmp";
        img.output(fileName02,fileType02);
        fileName02 +="." + fileType02;
        System.out.println("Output file:"+fileName02);
    }
    public static final String line = System.getProperty("line.separator");
}
