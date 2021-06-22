package Sample.KadaiF;

import GImage.GImage;

public class KadaiF2r {
	public static void main(String[] args)
	{
        String fileName = "Board1.bmp";     //読み込む画像のファイル名を指定
        GImage img= new GImage(fileName);   //Board.bmpの読み込み

        int width = img.getWidth();     //Board.bmpの幅を取得
        int height = img.getHeight();   //Board.bmpの高さを取得
        
        int Hankeiupper = 50;   //半径の上限値
        int Hankeilower = 5;    //半径の下限値
        int[] enX = new int[360];   //円周の座標xを入れるための変数
        int[] enY = new int[360];   //円周の座標yを入れるための変数
        int black = 0;              //黒丸の生成数を入れるための変数
        int white = 0;              //白丸の生成数を入れるための変数

        while(white < 10 || black < 10){    //黒丸と白丸をそれぞれ10個以上生成するまでループ
            double b;                       //円を生成するときに使う座標yを入れるための変数
            double c;                       //円を生成するときに使う座標xを入れるための変数
            int y0 = (int)(Math.random()*height);   //円の座標yを指定するための変数(Board.bmpの高さの範囲でランダムに指定される)
            int x0 = (int)(Math.random()*width);    //円の座標xを指定するための変数(Board.bmpの幅の範囲でランダムに指定される)
            int borw = (int)(Math.random()*2);      //黒丸か白丸のどちらを生成するか決める変数(ランダムに決まる)
            double R = (Math.random()*Hankeiupper + Hankeilower);   //円の半径を決めるための変数(指定した上限値と下限値の中でランダムに決まる)
            if(borw == 0){                                          //白丸を生成する
                for(int i=0;i<360;i++) {                            //円を作成するために角度360度分、反復する
                    b=(R)*Math.sin(Math.toRadians(i));      //0度から359度までをラジアンに変えて、Math.sinメソッドに入れることでその角度のsinを求める
                    c=(R)*Math.cos(Math.toRadians(i));      //0度から359度までをラジアンに変えて、Math.Cosメソッドに入れることでその角度のcosを求める
                    int Y = (int)b + y0;            //求めたsinと指定した円の座標yを足して整数化した変数Y
                    int X = (int)c + x0;            //求めたcosと指定した円の座標xを足して整数化した変数X
                    if(Y>0 && Y<height && X>0 && X<width)   //以下の1文を画像の範囲内の時だけ実行
                        img.pixel[Y][X] = 0;        //座標(変数Y,変数X)の画素値を0にする。(円を作る)
                    enX[i] = X;     //変数X(円周の座標x)を配列に格納する
                    enY[i] = Y;     //変数Y(円周の座標y)を配列に格納する
                    if(i == 359){   //ループの最後に1度だけ実行
                        for(int j=180;j>90;j--){    //円の下半分を円周を残して白くする
                            for(int p = enX[j]+1;p<enX[180-j];p++){     //左側の円周から右側の円周に向かって、円の中身を白くしていく
                                if(enY[j]>0 && enY[j]<height && p>0 && p<width) //Board.bmpの範囲内の時実行
                                    img.pixel[enY[j]][p] = 255;
                                if(enY[j]>0 && enY[j]<height && enX[j]>0 && enX[j]<width)   //異なる円周が同じy座標に存在した時のために、円周を黒くする
                                    img.pixel[enY[j]][enX[j]] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[180-j]>0 && enX[180-j]<width)
                                    img.pixel[enY[j]][enX[180-j]] = 0;
                            }
                        }
                        for(int j=181;j<270;j++){   //円の上半分を円周を残して白くする
                            for(int p = enX[j]+1;p<enX[540-j];p++){     //左側の円周から右側の円周に向かって、円の中身を白くしていく
                                if(enY[j]>0 && enY[j]<height && p>0 && p<width) //Board.bmpの範囲内の時実行
                                    img.pixel[enY[j]][p] = 255;
                                if(enY[j]>0 && enY[j]<height && enX[j]>0 && enX[j]<width)   //異なる円周が同じy座標に存在した時のために、円周を黒くする
                                    img.pixel[enY[j]][enX[j]] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[540-j]>0 && enX[540-j]<width)
                                    img.pixel[enY[j]][enX[540-j]] = 0;
                            }
                        }
                        //上記のループで指定できなかった円の上と下の頂点を黒くする
                        if(enY[90]>0 && enY[90]<height && enX[90]>0 && enX[90]<width)   //Board.bmpの範囲内の時実行
                            img.pixel[enY[90]][enX[90]] = 0;
                        if(enY[270]>0 && enY[270]<height && enX[270]>0 && enX[270]<width)   //Board.bmpの範囲内の時実行
                            img.pixel[enY[270]][enX[270]] = 0;
                    }
                }
                white++;    //白丸を1つ生成したことを知らせる
            }
            else{
                for(int i=0;i<360;i++) {    //円を作成するために角度360度分、反復する
                    b=(R)*Math.sin(Math.toRadians(i));  //0度から359度までをラジアンに変えて、Math.sinメソッドに入れることでその角度のsinを求める
                    c=(R)*Math.cos(Math.toRadians(i));  //0度から359度までをラジアンに変えて、Math.Cosメソッドに入れることでその角度のcosを求める
                    int Y = (int)b + y0;    //求めたsinと指定した円の座標yを足して整数化した変数Y
                    int X = (int)c + x0;    //求めたcosと指定した円の座標xを足して整数化した変数X
                    if(Y>0 && Y<height && X>0 && X<width)   //以下の1文を画像の範囲内の時だけ実行
                        img.pixel[Y][X] = 0;    //座標(変数Y,変数X)の画素値を0にする。(円を作る)
                    enX[i] = X;             //変数X(円周の座標x)を配列に格納する
                    enY[i] = Y;             //変数Y(円周の座標y)を配列に格納する
                    if(i == 359){           //ループの最後に1度だけ実行
                        for(int j=180;j>90;j--){    //円の下半分を黒くする
                            for(int p = enX[j]+1;p<enX[180-j];p++){     //左側の円周から右側の円周に向かって、円の中身を黒くしていく
                                if(enY[j]>0 && enY[j]<height && p>0 && p<width) //Board.bmpの範囲内の時実行
                                    img.pixel[enY[j]][p] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[j]>0 && enX[j]<width)
                                    img.pixel[enY[j]][enX[j]] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[180-j]>0 && enX[180-j]<width)
                                    img.pixel[enY[j]][enX[180-j]] = 0;
                            }
                        }
                        for(int j=181;j<270;j++){    //円の上半分を黒くする
                            for(int p = enX[j]+1;p<enX[540-j];p++){     //左側の円周から右側の円周に向かって、円の中身を黒くしていく
                                if(enY[j]>0 && enY[j]<height && p>0 && p<width) //Board.bmpの範囲内の時実行
                                    img.pixel[enY[j]][p] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[j]>0 && enX[j]<width)
                                    img.pixel[enY[j]][enX[j]] = 0;
                                if(enY[j]>0 && enY[j]<height && enX[540-j]>0 && enX[540-j]<width)
                                    img.pixel[enY[j]][enX[540-j]] = 0;
                            }
                        }
                        if(enY[90]>0 && enY[90]<height && enX[90]>0 && enX[90]<width)
                            img.pixel[enY[90]][enX[90]] = 0;
                        if(enY[270]>0 && enY[270]<height && enX[270]>0 && enX[270]<width)
                            img.pixel[enY[270]][enX[270]] = 0;
                    }
                }
                black++;    //黒丸を1つ生成したことを知らせる
            }
        }

	     String fileName02 = "kadaiF-2";    //出力画像の名称
	     String fileType02 = "bmp";         //出力画像の形式
	     img.output(fileName02,fileType02); //画像を出力
	     fileName02 +="." + fileType02;     //fileName02に.fileType02を追加
	     System.out.println("Output file:"+fileName02); //文字列"Output file"と fileName02を出力
	}
}