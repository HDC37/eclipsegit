package flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 小鸟类
 * @author g
 *
 */
public class Bird {
	/**
	 * 小鸟的中心点
	 */
    int x;
    int y;
    /**
     * 转载小鸟的初始图片
     */
    BufferedImage bird;
    /**
     * 转载3张不同姿态的小鸟图片
     */
    BufferedImage[] birds;
    /**
     * 数组下标
     */
    int index=0;
    /**
     * 重力加速度
     */
    int g;
    /**
     * 间隔时间 秒
     */
    double t;
    /**
     * 初始速度  像素/秒
     */
    double v0;
    /**
     * 当前时刻的速度
     */
    double vt;
    /**
     * 运动的距离
     */
    double s;
     
    /**
     * 小鸟的飞行的角度
     */
    double angle;
     //小鸟的大小
    double size=26;
    
    public Bird(int x,int y) throws IOException{
    	this.x = x;
    	this.y = y;
    	bird = ImageIO.read(getClass().getResource("37.png"));
    	birds = new BufferedImage[3];
    	for(int i=0;i<3;i++){
    		birds[i] = ImageIO.read(getClass().getResource(i+".png"));
    		bird = birds[0];
    		//初始化参数
    		g=4;
    		t=0.25;
    		v0=20;
    		vt=v0;
    	}
  }
    /**
     * 绘制小鸟
     * @param g
     */
    public void paint(Graphics g){
    //g.drawImage(bird,x,y,null);
    Graphics2D g2d = (Graphics2D)g;
    g2d.rotate(angle,x,y);
    
    //以x ,y为中心绘制图片
    int x1=x-bird.getWidth()/2;
    int y1=y-bird.getWidth()/2;
    g.drawImage(bird,x1,y1,null);
    g2d.rotate(-angle,x,y);
    }
	/**
	 * 小鸟扇翅膀，做抛物运动
	 */
    public void step(){
    	/**
    	 * 抛物运动
    	 */
    	//当前速度
    	double vt1=vt;
    	//垂直上抛，经过t秒后的速度
    	double v=vt1-g*t;
    	//v作为下一次位移的初始速度
    	vt=v;
    	//运行距离
    	s =vt1*t-0.5*g*t*t;
    	y=y-(int)s;
    	//计算小鸟的角度
    	angle = -Math.atan(s/15);  //15=60*0.25
    	
    	/**
    	 * 扇翅膀
    	 */
    	index++;
    	bird = birds[index/10%3];
    }
    
    /**
     * 向上飞的方法：每次鼠标点击的时候，小鸟重新获得初速的，向上飞
     * 直到下一次点击之前，都一直做自由落体运动
     */
    public void flappy(){
    	//new myMusicPlay("fei.wav").start();;
    	vt=v0;
    }
    /**
     * 检测小鸟是否与地面，天花板，柱子发生碰撞
     * @param col1
     * @param col2
     * @param ground
     * @return
     */
    public boolean hit(Column col1,Column col2,Ground ground){
    	//判断小鸟是否与天花板发生了碰撞
    	if(y-size/2<=0){
    		return true ;
    	}
    	//与地面发生了碰撞
    	if(y-size/2>ground.y){
    		return true;
    	}
    	if (hitColumn(col1)||hitColumn(col2)){
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean hitColumn (Column col){
    	if (x>col.x-col.width/2-size/2&&
    			x<col.x+col.width/2+size/2){
    		if (y>col.y-col.gap/2+size/2&&
    				y<col.y+col.gap/2-size/2){
    			return false;
    		}
    		return true;
    	}return false;
    }
    
    public boolean pass(Column col1,Column col2){
    	return col1.x ==x||col2.x==x;
    	
    }
        
    
}

