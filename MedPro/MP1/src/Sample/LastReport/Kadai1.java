package Sample.LastReport;

import  GImage.GImage;

public class Kadai1 {
        public static void main(String[] args)
        {
        int num=100; // 画像ファイルの数
        int fenum=8; // 特徴の数
        int resultnum=10; //出力結果の数
        int select = 0;
        int selectpicture;
        int[] selectnumber;
        
        select = Featureex.inputkeyboard(0);
        selectpicture = Featureex.inputkeyboard(1);
        selectnumber = Featureex.select(select, fenum);
        fenum = Featureex.fenumselect(selectnumber);    
        

        int[] maxes = new int[resultnum];
        double[][] Feature = new double[fenum][num];
        double[][] NomalizedFeature = new double[fenum][num];
        double[] inputFeature = new double[fenum];
        double[] NomalizedinputFeature = new double[fenum];
        double[] distance = new double[num];
        
        for(int i=0; i<num; i++){ //全画像データから特徴抽出
            String fileName = "last1data/" + Integer.toString(i+1) + ".bmp";
            GImage img= new GImage(fileName);
            for(int j=0;j<fenum;j++){
                Feature[j][i] =  Featureex.get_Feature(j,img,selectnumber);
            }
        }
        for(int i=0; i<num; i++){ //全画像データの特徴を正規化
            for(int j=0;j<fenum;j++){
                NomalizedFeature[j][i] = Featureex.get_nor(Feature[j][i], Featureex.get_scalr(j, num, Feature));
            }
        }

        String inputfileName = "last1data/"+Integer.toString(selectpicture)+".bmp"; //検索したい画像側の処理
        GImage inputimg= new GImage(inputfileName);
        for(int j=0;j<fenum;j++){   //検索したい画像の特徴を抽出
            inputFeature[j] =  Featureex.get_Feature(j,inputimg,selectnumber);
        }
        for(int j=0;j<fenum;j++){   //検索したい画像の特徴を正規化
            NomalizedinputFeature[j] = Featureex.get_nor(inputFeature[j], Featureex.get_scalr(j, num, Feature));
        }

        for(int i=0; i<num; i++){ //距離を取得
            distance[i] = Featureex.get_distance(fenum, NomalizedinputFeature, Featureex.get_fescalr(i, fenum, NomalizedFeature));
        }
        maxes = Featureex.get_maxes(resultnum, distance); //距離が小さい画像をresultnum個取得
        for(int i=0;i<resultnum;i++){
            System.out.println(maxes[i]);
            Featureex.filewrite(maxes,selectnumber,selectpicture,Feature,distance,fenum);
        }

        for(int i=0; i<maxes.length; i++){ //全画像データから特徴抽出
            String fileName = "last1data/" + Integer.toString(maxes[i]) + ".bmp";
            GImage outputimg= new GImage(fileName);
            String outputfileName = "Lastreportoutput/outputimg/" + Integer.toString(maxes[i]) + ".bmp";
            Featureex.imgoutput(outputimg, outputfileName);
        }
    }
}