package flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * �ƶ��ĵ���
 * @author g
 *
 */
public class Ground {
	/**
	 * ����������ڴ�
	 */
    BufferedImage ground;
    /**
     * �������ĺ�������
     */
    int x;
    int y;
/**
 * ����ͼƬ
 * @throws IOException
 */
    public Ground() throws IOException{
	ground = ImageIO.read(getClass().getResource("ground.png"));
	/**
	 * ����������
	 */
	y=400;
}
    /**
     * x--,��ͼƬ�����ƶ�, ��xС��ĳ������ʱ��λ
     */
    public void step(){
  if(x<-137){   //360-497
	  x=0;
  }
    	x--;
}
    /**
     * ���Ƶ���ͼƬ
     */
    public void paint(Graphics g){
    	g.drawImage(ground,x,y,null);
    	
    }


}




