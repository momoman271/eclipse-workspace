package Sample.KadaiC;
import GImage.GImage;

public class KadaiC1 {

	public static void main(String[] args)

	{
		String fileName = "Board.bmp";

		int level1 = 0;		//黒
		int j = 0;

		GImage img= new GImage(fileName);

		int width1 = img.getWidth();	//黒画像の幅

		//斜め線
		for(int i=width1-1;i>=0;i--) {
			img.pixel[i][j]=level1;
			j++;
		}

		String fileName01 = "KadaiC1";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

