package Sample.KadaiC;
import GImage.GImage;

public class KadaiC4 {

	public static void main(String[] args)

	{
		String fileName = "Board1.bmp";

		int level1 = 0;		//黒
		int j = 0;
		int x = 0;
		int y = 0;

		GImage img= new GImage(fileName);

		//斜め線
		for(int b=0;b<8;b++){
			x = 40 + (30 * b);
			y = 30 + (40 * b);
			for(int a=0;a<2;a++){
				j=0;
				for(int i=0;i<30;i++) {
					img.pixel[i+y][j+x]=level1;
					j++;
				}
				x -= 29;
				y += 29;
			}
			x = 40 + (30 * b);
			y = 30 + (40 * b);
			for(int a=0;a<2;a++){
				j=0;
				for(int i=0;i>-30;i--) {
					img.pixel[j+y][i+x]=level1;
					j++;
				}
				x += 29;
				y += 29;
			}
		}
		
		String fileName01 = "KadaiC4";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

