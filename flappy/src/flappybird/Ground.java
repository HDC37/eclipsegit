package flappybird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 移动的地面
 * @author g
 *
 */
public class Ground {
	/**
	 * 将地面加入内存
	 */
    BufferedImage ground;
    /**
     * 定义地面的横纵坐标
     */
    int x;
    int y;
/**
 * 加载图片
 * @throws IOException
 */
    public Ground() throws IOException{
	ground = ImageIO.read(getClass().getResource("ground.png"));
	/**
	 * 设置纵坐标
	 */
	y=400;
}
    /**
     * x--,让图片向左移动, 当x小于某个数的时候复位
     */
    public void step(){
  if(x<-137){   //360-497
	  x=0;
  }
    	x--;
}
    /**
     * 绘制地面图片
     */
    public void paint(Graphics g){
    	g.drawImage(ground,x,y,null);
    	
    }


}




