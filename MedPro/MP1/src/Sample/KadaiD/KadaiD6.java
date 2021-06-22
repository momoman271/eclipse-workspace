package Sample.KadaiD;
import GImage.GImage;

public class KadaiD6 {

	public static void main(String[] args)

	{
		String fileName = "Lenna.bmp";

	    GImage img1= new GImage(fileName);
		int height2 = img1.getHeight();	//黒画像の高さ
		int width2 = img1.getWidth();	//黒画像の幅
		int [] pixelnumber = new int[255];
		int i = 0;
		int Mediannumber = 0;
		int Totalnumber = 0;
		int Th = 0;
		boolean first = true;
		
		for(int x=0;x<width2;x++) {
			for(int y=0;y<height2;y++) {	
				i = img1.pixel[y][x];
				pixelnumber[i]++;
				Totalnumber++;
			}
		}

		for(int j=0;j < pixelnumber.length;j++){
			Mediannumber += pixelnumber[j];
			if(Mediannumber >= Totalnumber / 2 && first){
				Th = j;
				first = false;
			}
		}

		for(int x=0;x<width2;x++) {
			for(int y=0;y<height2;y++) {	
				if(img1.pixel[y][x] < Th){
					img1.pixel[y][x] = GImage.MIN_GRAY;
				}
				else{
					img1.pixel[y][x] = GImage.MAX_GRAY;
				}
			}
		}

		String fileName01 = "KadaiD6";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

