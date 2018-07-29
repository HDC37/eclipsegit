package flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 柱子的类，
 * @author g
 *
 */
public class Column {
    /**
     * 将柱子图片加入到缓存内存中
     */
	BufferedImage column;
	/**
	 * 柱子中心点的坐标（位置）
	 */
	int x;
	int y;
	/**
	 * 柱子图片的宽度和高度（大小）
	 */
	int width;
	int height;
	/**上下柱子之间空间的高度*/
	int gap=109;
	/**
	 * 定义随机变量
	 */
	Random random;
	
	/**
	 * 确定柱子的位置
	 * @param x
	 * @throws IOException 
	 */
	public Column(int x1) throws IOException{
		x=x1;
		/**
		 * 初始化柱子的图片
		 */
		column = ImageIO.read(getClass().getResource("column.png"));
		width = column.getWidth();
		height = column.getHeight();
		/**
		 * 初始化random
		 */
		random = new Random();
		/**
		 * 让柱子中间缺口高度随机出现
		 */
		y=140+random.nextInt(140);
				
				
	}
	/**
	 * 绘制柱子图片到界面上
	 * @param g
	 */
	public void paint(Graphics g){
		g.drawImage(column,x-width/2,y-height/2,null);
	}
	/**
	 * 用来改变柱子的横坐标，并且不能一直向左移动
	 */
	public void step(){
		x--;
		if (x<-width){
			x=320;
			y = 140+random.nextInt(140);
		}
		
	}
	
	
	
	
	
}
