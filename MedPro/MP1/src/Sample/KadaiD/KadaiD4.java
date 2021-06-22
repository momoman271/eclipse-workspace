package Sample.KadaiD;
import GImage.GImage;

public class KadaiD4 {

	public static void main(String[] args)

	{
		String fileName = "milkdrop.bmp";

	    GImage img1= new GImage(fileName);
		int height2 = img1.getHeight();	//黒画像の高さ
		int width2 = img1.getWidth();	//黒画像の幅
		int Sumpixel = 0;
		int p = 0;
		double Th = 0.0;

		for(int x=0;x<width2;x++) {
			for(int y=0;y<height2;y++) {	
				Sumpixel += img1.pixel[y][x];
			}
		}
		
		Th = (double)Sumpixel / ((width2)*(height2));
		p = (int)Th;

		for(int x=0;x<width2;x++) {
			for(int y=0;y<height2;y++) {	
				if(img1.pixel[y][x] < p){
					img1.pixel[y][x] = GImage.MIN_GRAY;
				}
				else{
					img1.pixel[y][x] = GImage.MAX_GRAY;
				}
			}
		}

		String fileName01 = "2110370801";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

