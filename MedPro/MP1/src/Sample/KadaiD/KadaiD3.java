package Sample.KadaiD;
import GImage.GImage;

public class KadaiD3 {

	public static void main(String[] args)

	{
		String fileName = "Lenna.bmp";

	    GImage img1= new GImage(fileName);
		int height2 = img1.getHeight();	//黒画像の高さ
		int width2 = img1.getWidth();	//黒画像の幅

		for(int x=0;x<width2;x++) {
			for(int y=0;y<height2;y++) {
				if(img1.pixel[y][x] < 50){
					img1.pixel[y][x]=0;
				}
				else if(img1.pixel[y][x] < 100){
					img1.pixel[y][x]=50;
				}
				else if(img1.pixel[y][x] < 150){
					img1.pixel[y][x]=100;
				}
				else if(img1.pixel[y][x] < 200){
					img1.pixel[y][x]=150;
				}
				else{
					img1.pixel[y][x]=200;
				}
			}
		}

		String fileName01 = "KadaiD3";

		String fileType01 = "bmp";

		img1.output(fileName01,fileType01);

		fileName01 +="." + fileType01;

		System.out.println("Output file:"+fileName01);

	}
}

