package Sample.KadaiD;
import GImage.GImage;

public class KadaiD2 {

	public static void main(String[] args)

	{
		String fileName = "Board1.bmp";

	    GImage img1= new GImage(fileName);
		int height3 = 0;
		int width3 = 0;
		boolean first = false;

		for(int i=0;i<120;i++){
			String j = String.valueOf(i);
			String s = "block"+ j +".bmp";
			GImage img2 = new GImage(s);
			int height2 = img2.getHeight();	//黒画像の高さ
			int width2 = img2.getWidth();	//黒画像の幅
			if(i % 10 == 0 && first){
				width3 = 0;
				height3 += height2;
			}
			for(int x=0;x<width2;x++) {
				for(int y=0;y<height2;y++) {
					img1.pixel[y + height3][x + width3]=img2.pixel[y][x];
				}
			}
			width3 += width2;
			first = true;
		}


		String fileName01 = "KadaiD2";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

