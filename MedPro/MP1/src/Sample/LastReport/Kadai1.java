package Sample.LastReport;

import  GImage.GImage;
import  java.util.Random;
import java.util.concurrent.TimeUnit;	
import Sample.LastReport.Featureex;

public class Kadai1 {
        public static void main(String[] args)
        {
        String outputName = "report2";
        int num=100; // Number of files

        for(int i=1; i<=num; i++){
            String fileName = "last1data/" + Integer.toString(i) + ".bmp";
            GImage img= new GImage(fileName);
        }


        String fileName = "d850429avhrr4.bmp";
        GImage img1= new GImage(fileName);

    }
}