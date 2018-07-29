package flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * ���ӵ��࣬
 * @author g
 *
 */
public class Column {
    /**
     * ������ͼƬ���뵽�����ڴ���
     */
	BufferedImage column;
	/**
	 * �������ĵ�����꣨λ�ã�
	 */
	int x;
	int y;
	/**
	 * ����ͼƬ�Ŀ�Ⱥ͸߶ȣ���С��
	 */
	int width;
	int height;
	/**��������֮��ռ�ĸ߶�*/
	int gap=109;
	/**
	 * �����������
	 */
	Random random;
	
	/**
	 * ȷ�����ӵ�λ��
	 * @param x
	 * @throws IOException 
	 */
	public Column(int x1) throws IOException{
		x=x1;
		/**
		 * ��ʼ�����ӵ�ͼƬ
		 */
		column = ImageIO.read(getClass().getResource("column.png"));
		width = column.getWidth();
		height = column.getHeight();
		/**
		 * ��ʼ��random
		 */
		random = new Random();
		/**
		 * �������м�ȱ�ڸ߶��������
		 */
		y=140+random.nextInt(140);
				
				
	}
	/**
	 * ��������ͼƬ��������
	 * @param g
	 */
	public void paint(Graphics g){
		g.drawImage(column,x-width/2,y-height/2,null);
	}
	/**
	 * �����ı����ӵĺ����꣬���Ҳ���һֱ�����ƶ�
	 */
	public void step(){
		x--;
		if (x<-width){
			x=320;
			y = 140+random.nextInt(140);
		}
		
	}
	
	
	
	
	
}
