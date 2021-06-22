package Sample.KadaiE;
import GImage.GImage;

public class KadaiE1 {

	public static void main(String[] args)

	{
		String fileName = "25.bmp";
	    GImage img1= new GImage(fileName);

        int height1 = img1.getHeight();	//黒画像の幅
		int width1 = img1.getWidth();	//黒画像の幅
        int black = 0;
		int white = 255;
        int Totalnumber = 0;
		boolean first = true;

		for(int y=0;y<height1;y++) {
			for(int x=0;x<width1;x++) {
				if(img1.pixel[y][x] == black && img1.pixel[y][x-1] == white && x >= 1)
                {
					Totalnumber++;
                }
			}
		}
		String fileName01 = "KadaiE1";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;
        System.out.println("黒の合計は"+Totalnumber);
		System.out.println("Output file:"+fileName01);
	}
}
