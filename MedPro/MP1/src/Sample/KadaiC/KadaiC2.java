package Sample.KadaiC;
import GImage.GImage;

public class KadaiC2 {

	public static void main(String[] args)

	{
		String fileName = "Board.bmp";

		int level1 = 0;		//黒
		int j = 0;

		GImage img= new GImage(fileName);

		int width1 = img.getWidth();	//黒画像の幅
		
		int height1 = img.getHeight();	//黒画像の高さ

		//斜め線
		for(int y=0;y<height1;y+=20){
			for(int x=0;x<width1;x+=20){
				j=0;
				for(int i=0;i<width1;i++) {
					if(i+x < width1){
						if(j+y < height1){
							img.pixel[i+y][j+x]=level1;
						}
					}
					j++;
				}
			}
		}
		
		String fileName01 = "KadaiC2";

		String fileType01 = "bmp";

		img.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

