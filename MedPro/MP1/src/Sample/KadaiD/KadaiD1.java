package Sample.KadaiD;
import GImage.GImage;

public class KadaiD1 {

	public static void main(String[] args)

	{
		String fileName = "Board1.bmp";

	    GImage img1= new GImage(fileName);

		int height3 = 0;

		for(int i=0;i<18;i++){
			String j = String.valueOf(i);
			String s = "file"+ j +".bmp";
			GImage img2 = new GImage(s);
			int height2 = img2.getHeight();	//黒画像の高さ
			int width2 = img2.getWidth();	//黒画像の幅
			for(int x=0;x<width2;x++) {
				for(int y=0;y<height2;y++) {
					img1.pixel[y + height3][x]=img2.pixel[y][x];
				}
			}
			height3 += height2;
		}


		String fileName01 = "KadaiD1";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

