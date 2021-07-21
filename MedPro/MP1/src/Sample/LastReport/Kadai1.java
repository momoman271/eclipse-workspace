package Sample.LastReport;

import  GImage.GImage;

public class Kadai1 {
        public static void main(String[] args)
        {
        int num=100; // 画像ファイルの数
        int fenum=8;    // 特徴の数
        int fenum2=fenum; // 特徴の数
        int resultnum=10; //出力結果の数
        int select = 0; //キーボードから選択された特徴を入れる変数
        int selectpicture; //キー画像の番号を入れる変数
        int[] selectnumber; //選択された特徴を入れる配列
        int[] outputselect = new int[fenum2];
        for(int i=0;i<outputselect.length;i++){
            outputselect[i] = i+1;
        }
        
        select = Featureex.inputkeyboard(0);        //キーボードから選択された特徴を変数に格納
        selectpicture = Featureex.inputkeyboard(1); //キーボードから入力されたキー画像番号を格納
        selectnumber = Featureex.select(select, fenum); //選択された特徴を単一値に変換して配列に格納
        fenum = Featureex.fenumselect(selectnumber);  //選択された特徴の数を変数に格納

        int[] maxes = new int[resultnum];           //キー画像との距離が小さい画像を入れる配列
        double[][] Feature = new double[fenum][num]; //特徴を入れる2次元配列:行=特徴軸,列=画像番号
        double[][] NomalizedFeature = new double[fenum][num]; //正規化された特徴を入れる2次元配列:行=特徴軸,列=画像番号
        double[] inputFeature = new double[fenum];          //キー画像の特徴を入れる配列
        double[] NomalizedinputFeature = new double[fenum]; //キー画像の正規化された特徴を入れる配列
        double[] distance = new double[num];                //キー画像と全ての画像の距離を入れる配列
        double[][] outputFeature = new double[fenum2][resultnum];
        
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

        String inputfileName = "last1data/"+Integer.toString(selectpicture)+".bmp"; //以下はキー画像側の処理
        GImage inputimg= new GImage(inputfileName);
        for(int j=0;j<fenum;j++){   //キー画像の特徴を抽出
            inputFeature[j] =  Featureex.get_Feature(j,inputimg,selectnumber);
        }
        for(int j=0;j<fenum;j++){   //キー画像の特徴を正規化
            NomalizedinputFeature[j] = Featureex.get_nor(inputFeature[j], Featureex.get_scalr(j, num, Feature));
        }

        for(int i=0; i<num; i++){ //全画像とキー画像の距離を取得
            distance[i] = Featureex.get_distance(fenum, NomalizedinputFeature, Featureex.get_fescalr(i, fenum, NomalizedFeature));
        }
        maxes = Featureex.get_maxes(resultnum, distance); //距離が小さい画像をresultnum個取得

        for(int i=0; i<maxes.length; i++){ 
            String fileName = "last1data/" + Integer.toString(maxes[i]) + ".bmp";
            GImage outputimg= new GImage(fileName);
            for(int j=0;j<fenum2;j++){
                outputFeature[j][i] = Featureex.get_Feature(j, outputimg, outputselect);
            }
            String outputfileName = "Lastreportoutput/outputimg/" + Integer.toString(maxes[i]);
            Featureex.imgoutput(outputimg, outputfileName);
        }

        for(int i=0;i<resultnum;i++){
            Featureex.filewrite(maxes,selectnumber,selectpicture,Feature,distance,fenum,outputFeature,fenum2);
        }

        
    }
}