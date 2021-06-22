package Sample.KadaiC;
import GImage.GImage;

public class KadaiC3 {

	public static void main(String[] args)

	{
		String fileName = "Board.bmp";

		int level1 = 0;		//黒
		int j = 0;
		int x = 140;
		int y = 160;

		GImage img= new GImage(fileName);

		//斜め線
		for(int a=0;a<2;a++){
			j=0;
			for(int i=0;i<30;i++) {
				img.pixel[i+y][j+x]=level1;
				j++;
			}
			x -= 29;
			y += 29;
		}

		x = 140;
		y = 160;

		for(int a=0;a<2;a++){
			j=0;
			for(int i=0;i>-30;i--) {
				img.pixel[j+y][i+x]=level1;
				j++;
			}
			x += 29;
			y += 29;
		}
		
		String fileName01 = "KadaiC3";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

