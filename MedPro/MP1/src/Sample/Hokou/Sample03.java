package Sample.Hokou;
import GImage.*;

public class Sample03 {

	/**
	 * This sample program shows how to use GImage class 
	 * 
	 * @author Tagawa
	 * @param args
	 */
	public static void main(String[] args)
	{
	     int num=9; // Number of files
		 double[] Area = new double[num];
		 double[] F1 = new double[num];
		 
		 int k;
		 GImage img1= new GImage("ImageFiles3/28.bmp");
		 for(int i=0; i<num; i++){
			 k=i+20;
	    	 String fileName = "ImageFiles3/" + Integer.toString(k) + ".bmp";
	    	 GImage img= new GImage(fileName);
	    	 Area[i] = get_area(img);	
			 F1[i] = get_F1(img); 
	     }
	     
		 /** Display evaluation results */
		 System.out.println("Average=" + get_ave(Area));
		 System.out.println("課題2=" + get_ave(F1));
		 System.out.println("Deviation=" + get_dev(Area));
		 
	}
	public static double get_F1(GImage img1){
		int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
		double F1 = 0;
		int sum = 0;
		double area = get_area(img1);

		for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
				if(img1.pixel[y][x] == GImage.MIN_GRAY){
					sum++;
				}
			}
		}
		F1 = (double)sum / area;
		return F1;
	}

	/** Calculate area */
	public static double get_area(GImage img1) { //外接矩形の面積を返す
		int width1, height1;
	    width1 = img1.getWidth();
	    height1 = img1.getHeight();
	     
	    /** Decide vertical frame line */
	    boolean flagY = true;
	    int Ymin=0;
	    int Ymax=0;
	    for(int y=0;y<height1;y++){
	    	for(int x=0;x<width1;x++){
	    		if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			Ymax=y;
	    			if(flagY==true){
	    				Ymin=y;
	    				flagY=false;
	    			}
	    		}
	    	 }
	     }
	     
	     /** Decide horizontal frame line */
	     boolean flagX = true;
	     int Xmin=0;
	     int Xmax=0;
	     for(int x=0;x<width1;x++){
	    	 for(int y=0;y<height1;y++){
	    		 if(img1.pixel[y][x]==GImage.MIN_GRAY){
	    			 Xmax=x;
	    			 if(flagX==true){
	    				 Xmin=x;
	    				 flagX=false;
	    			 }
	    		 }
	    	 }
	     }
	     
	     /** Calculate area */
	     double area;
	     area = ((double)(Ymax-Ymin+1))*((double)(Xmax-Xmin+1));
	     return area;
	}
	
	/** Calculate average */
	public static double get_ave(double data[]) { //全ての画像の外接矩形面積の平均値を返す
		double sum = 0;
		double ave;

		for (int i = 0; i < data.length; i++) {
			sum = sum + data[i];
		}
		ave = sum / ((double) data.length);

		return ave;
	}
	
	/** Calculate deviation */
	public static double get_dev(double data[]) { //全ての画像の外接矩形面積の標準偏差を返す
		double sum2 = 0;
		double sum = 0;
		double var, dev;

		for (int i = 0; i < data.length; i++) {
			sum2 = sum2 + data[i]*data[i];
			sum = sum + data[i];
		}
		var = sum2 - (sum*sum) / ((double) data.length);
		var = var / ((double) data.length);
		dev =Math.sqrt(var); 

		return dev;
	}
}
