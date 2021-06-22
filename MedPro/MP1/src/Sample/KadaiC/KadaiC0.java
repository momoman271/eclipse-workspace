package Sample.KadaiC;
import GImage.GImage;

public class KadaiC0 {

	public static void main(String[] args)

	{
		
		int level0 = 255;	//白
		int level1 = 0;		//黒

		GImage img1 = new GImage(256,256);

		GImage img2 = new GImage(256,256);

		int width1 = img1.getWidth();	//黒画像の幅
		
		int height1 = img1.getHeight();	//黒画像の高さ

		//白画像生成
		for(int i=0;i<width1;i++) {
			for(int j=0;j<height1;j++) {
				img1.pixel[i][j]=level0;
			}
		}

		for(int i=0;i<width1;i++) {
			for(int j=0;j<height1;j++) {
				img2.pixel[i][j]=level0;
			}
		}

		//斜め線
		for(int i=0;i<width1;i++) {
			for(int j=0;j<height1;j++) {
				if(i==j){
					img1.pixel[i][j]=level1;
				}
			}
		}

		String fileName01 = "KadaiC0";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		String fileName02 = "Board";

		String fileType02 = "bmp";

		img2.output(fileName02,fileType02);


		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

