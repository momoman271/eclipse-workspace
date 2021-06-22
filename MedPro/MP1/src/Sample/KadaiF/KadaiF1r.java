package Sample.KadaiF;

import GImage.GImage;

public class KadaiF1r {
	public static void main(String[] args)
	{
        String fileName = "Board1.bmp";
        GImage img= new GImage(fileName);

        int width = img.getWidth();
        int height = img.getHeight();
        int y0 = 200;
        int x0 = 150;
        double R = 40*Math.sqrt(2);
        double b = 0.0;
        double c = 0.0;
        double r = 0;
        int[] enX = new int[360];
        int[] enY = new int[360];
         r = 1 / R;


        /*for(double j=0;j<=R;j+=r){
            for(int i=0;i<360;i++) {
                b=(j)*Math.sin(Math.toRadians(i));
                c=(j)*Math.cos(Math.toRadians(i));
                int Y = (int)b + y0;
                int X = (int)c + x0;
                img.pixel[Y][X] = 0;
            }
        }*/
        for(int i=0;i<360;i++) {
            b=(R)*Math.sin(Math.toRadians(i));
            c=(R)*Math.cos(Math.toRadians(i));
            int Y = (int)b + y0;
            int X = (int)c + x0;
            img.pixel[Y][X] = 0;
            enX[i] = X;
            enY[i] = Y;
            if(i == 359){
                for(int j=180;j>90;j--){
                    for(int p = enX[j]+1;p<enX[180-j];p++){
                        img.pixel[enY[j]][p] = 0;
                        img.pixel[enY[j]][enX[j]] = 0;
                        img.pixel[enY[j]][enX[180-j]] = 0;
                    }
                }
                for(int j=181;j<270;j++){
                    for(int p = enX[j]+1;p<enX[540-j];p++){
                        img.pixel[enY[j]][p] = 0;
                        img.pixel[enY[j]][enX[j]] = 0;
                        img.pixel[enY[j]][enX[540-j]] = 0;
                    }
                }
                img.pixel[enY[90]][enX[90]] = 0;
                img.pixel[enY[270]][enX[270]] = 0;
            }
        }
        

	     String fileName02 = "kadaiF-1";
	     String fileType02 = "bmp";
	     img.output(fileName02,fileType02);
	     fileName02 +="." + fileType02;
	     System.out.println("Output file:"+fileName02);
	}
}