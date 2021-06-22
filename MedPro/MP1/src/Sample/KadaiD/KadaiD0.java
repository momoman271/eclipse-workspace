package Sample.KadaiD;
import GImage.GImage;

public class KadaiD0 {

	public static void main(String[] args)

	{


		String fileName = "Lenna.bmp";
	    GImage img1= new GImage(fileName);

		GImage img2 = new GImage(fileName);

		int width1 = img1.getWidth();	//黒画像の幅

		for(int i=100;i<130;i++) {
			for(int j=0;j<width1;j++) {
				img1.pixel[i][j]=img2.pixel[i+30][j];
			}
		}
		for(int i=130;i<160;i++) {
			for(int j=0;j<width1;j++) {
				img1.pixel[i][j]=img2.pixel[i-30][j];
			}
		}

		String fileName01 = "KadaiD0";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

