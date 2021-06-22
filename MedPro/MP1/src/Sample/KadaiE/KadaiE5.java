package Sample.KadaiE;
import GImage.GImage;

public class KadaiE5 {

	public static void main(String[] args)

	{
		String fileName = "25.bmp";
	    GImage img1= new GImage(fileName);

        int height1 = img1.getHeight();	//黒画像の幅
		int width1 = img1.getWidth();	//黒画像の幅
        int black = 0;
		int white = 255;
        int Totalnumber = 0;
		int[] num = new int[4]; //x1,x2,y1,y2
		int X = 0;
		int Y = 0;
		boolean first = true;
		int ytotal = 0;
		int answer = 0;

		for(int y=0;y<height1;y++) {
			for(int x=0;x<width1;x++) {
				if(first && img1.pixel[y][x] == black){
					num[0] = x;
					num[1] = x;
					num[2] = y;
					num[3] = y;
					first = false;
					Totalnumber++;
				}
				else if(img1.pixel[y][x] == black)
                {
					if(num[0] > x){
						num[0] = x;
					}
					if(num[1] < x){
						num[1] = x;
					}
					if(num[2] > y){
						num[2] = y;
					}
					if(num[3] < y){
						num[3] = y;
					}
					Totalnumber++;
                }
			}
		}
		X = num[1] - (num[0] - 1);
		Y = num[3] - (num[2] - 1);

		for(int y=num[2];y<num[3];y++){
			for(int x=num[0];x<num[1];x++){
				if(img1.pixel[y][x] == black){
					ytotal += x-num[0];
				}
			}
		}

		answer = ytotal / Totalnumber;

		String fileName01 = "KadaiE4";

		String fileType01 = "bmp";

		fileName01 +="." + fileType01;
		System.out.println("画像横"+width1);
		System.out.println("画像縦"+height1);
		System.out.println("x1="+num[0]);
		System.out.println("x2="+num[1]);
		System.out.println("y1="+num[2]);
		System.out.println("y2="+num[3]);
		System.out.println("横"+X);
		System.out.println("縦"+Y);
        System.out.println("黒の合計は"+Totalnumber);
		System.out.println("黒の合計は"+ answer);
		System.out.println("Output file:"+fileName01);
	}
}
