package Sample.LastReport;

import  GImage.GImage;
import  java.util.Random;
import java.util.concurrent.TimeUnit;	
import Sample.LastReport.Featureex;

public class Kadai1 {
        public static void main(String[] args)
        {
        String outputName = "lastreport";
        int num=100; // 画像ファイルの数
        int fenum=8; // 特徴の数
        int resultnum=10; //出力結果の数
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
                Feature[j][i] =  Featureex.get_Feature(j,img);
                System.out.println( Feature[j][i]);
            }
        }
        for(int i=0; i<num; i++){ //全画像データの特徴を正規化
            for(int j=0;j<fenum;j++){
                NomalizedFeature[j][i] = Featureex.get_nor(Feature[j][i], Featureex.get_scalr(j, num, Feature));
            }
        }

        String inputfileName = "last1data/2.bmp";
        GImage inputimg= new GImage(inputfileName);
        for(int j=0;j<fenum;j++){
            inputFeature[j] =  Featureex.get_Feature(j,inputimg);
        }
        for(int j=0;j<fenum;j++){
            NomalizedinputFeature[j] = Featureex.get_nor(inputFeature[j], Featureex.get_scalr(j, num, Feature));
        }

        for(int i=0; i<num; i++){
            for(int j=0;j<fenum;j++){
                distance[i] = Featureex.get_distance(j, NomalizedinputFeature, Featureex.get_fescalr(i, fenum, NomalizedFeature));
            }
        }
        maxes = Featureex.get_maxes(11, distance);
        /*for(int i=0;i<resultnum;i++){
            System.out.println((int)maxes[i]);
        }*/
    }
}