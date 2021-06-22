package Sample.KadaiC;
import GImage.GImage;

public class KadaiC5 {

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
			for(int a=0;a<30;a++){
				j=0;
				for(int i=0;i<30;i++) {
					img.pixel[i+y][j+x]=level1;
					j++;
				}
				x -= 1;
				y += 1;
			}
			x = 40 + (30 * b);
			y = 30 + (40 * b);
			for(int a=0;a<29;a++){
				j=0;
				for(int i=1;i<30;i++) {
					img.pixel[i+y][j+x]=level1;
					j++;	
				}
				x -= 1;
				y += 1;
			}
		}
		
		String fileName01 = "KadaiC5";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

