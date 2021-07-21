package Sample.LastReport;

import  GImage.GImage;

public class Kadai2 {
    public static void main(String[] args)
    {
        final int normalnum = 3;
        final int abnormalnum = 3;
        identification[] normal = new identification[normalnum];
        identification[] abnormal = new identification[abnormalnum];

        for(int i=0; i<normalnum+abnormalnum; i++){ //全画像データから特徴抽出
            String fileName;
            if(i<normalnum){
                fileName = "last2data/normal/n" + Integer.toString(i+1) + ".bmp";
                GImage img= new GImage(fileName);
                normal[i] = new identification(img);
                
            }
            else {
                fileName = "last2data/abnormal/ab" + Integer.toString(i+1-normalnum) + ".bmp";
                GImage img= new GImage(fileName);
                abnormal[i - normalnum] = new identification(img);
            }
        }
    }
}

class identification {
    
    

    GImage img;
    final int width;
    final int height;

    identification(GImage img){ //コンストラクタ
        width = img.getWidth();
        height = img.getHeight();
        this.img = img;
        setkernel();
    }
    
    //TODO 判別分析法で閾値
    
    void Binarization(int Threshold){
        for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				if(img.pixel[y][x] < Threshold){
					img.pixel[y][x]=GImage.MIN_GRAY;
				}
				else{
					img.pixel[y][x]=GImage.MAX_GRAY;
				}
			}
		}
    }

    private static final int[][][] INTS = new int[343][3][3];
    
    void setkernel(){
        int count = 0;
        for(int x1=0;x1<7;x1++){
            

            for(int x2=0;x2<7;x2++){
                for(int x3=0;x3<7;x3++){


                    count++;
                }
            }
        }
    }

    void setbox(int x,int count){
        switch (x) {
            case 0:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 1:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 2:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 3:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 4:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 5:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            case 6:
                INTS[count][0][0] = 0;
                INTS[count][0][1] = 0;
                INTS[count][0][2] = 0;
                break;
            default:
                break;
        }
    }



}
