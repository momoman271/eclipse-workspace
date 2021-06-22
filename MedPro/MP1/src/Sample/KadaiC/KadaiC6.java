package Sample.KadaiC;
import GImage.GImage;

public class KadaiC6 {

	public static void main(String[] args)

	{
		String fileName = "Board1.bmp";

		int level1 = 0;		//黒
		int j = 0;
		int x = 0;
		int y = 0;

		GImage img= new GImage(fileName);

		int width1 = img.getWidth();	//黒画像の幅
		
		int height1 = img.getHeight();	//黒画像の高さ

		//斜め線
		for(int b=0;b<15;b++){
			x = 5 + (15 * b);
			y = 30 + (25 * b);
			for(int a=0;a<2;a++){
				j=0;
				for(int i=0;i<30;i++) {
					if(i+y >= 0 && i+y < height1 && j+x > 0 && j+x < width1){
						img.pixel[i+y][j+x]=level1;
					}
					j++;
				}
				x -= 29;
				y += 29;
			}
			x = 5 + (15 * b);
			y = 30 + (25 * b);
			for(int a=0;a<2;a++){
				j=0;
				for(int i=0;i>-30;i--) {
					if(j+y >= 0 && j+y < height1 && i+x > 0 && i+x < width1){
						img.pixel[j+y][i+x]=level1;
					}
					j++;
				}
				x += 29;
				y += 29;
			}
		}
		
		String fileName01 = "KadaiC6";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

