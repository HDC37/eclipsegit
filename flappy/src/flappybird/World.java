package flappybird;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//何东
/**
 * 定义世界
 * @author g
 * 将背景图片，开始图片加到内存
 */
public class World extends JPanel{
  /**
   * 背景
   */
	BufferedImage background;
  /**
   * 开始图片
   */
	BufferedImage startImage;
	/**
	 * 增加地面
	 */
    Ground ground;
    
   /**
    * 柱子
    */
    Column column1;
    Column column2;
    boolean start;
    boolean gameover;
    Bird bird;
    int score;
/**
 * 定义一个构造方法,初始化
 * @throws IOException 
 */
public World() throws IOException{
	/**
	 *将背景图片，开始图片加载到内存中
	 */
	background = ImageIO.read(getClass().getResource("bg.png"));
	startImage = ImageIO.read(getClass().getResource("start.png"));
	/**
	 * 初始化地面
	 */
	ground = new Ground();
	/**
	 * 初始化柱子
	 */
	column1 = new Column(420);
	column2 = new Column(600);
	/*初始化小鸟
	 */
	bird = new Bird (140,225);
}
	/**
	 * 绘制图片
	 */
	public void paint(Graphics g){
		/**
		 * 绘制背景图片
		 */
		g.drawImage(background,0,0,null);
		
		/**
	     * 绘制柱子
	     */
	    if(start == true ){
	    	column1.paint(g);
	    	column2.paint(g);
	        bird.paint(g);
	    }
	    if (gameover){
	    	try {
				BufferedImage end= ImageIO.read(getClass().getResource("gameover.png"));
				g.drawImage(end, 0, 0,null);
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    /**
		 * 绘制开始图片
		 */
		if(start == false){
		g.drawImage(startImage,0,0,null);
		
		}
	    /**
	  	 * 使用ground绘制可移动地面图片
	  	 */
	    
	  		    ground.paint(g);
	  		    
	  		//绘制分数
	  g.setFont(new Font(Font.MONOSPACED,Font.BOLD ,25));
	  g.setColor(Color.white );
	  g.drawString("score="+score,25,40)	;	    
	  		    
		
	}
	/**
	 * 游戏开始的动作
	 * @throws InterruptedException 
	 */
	public void action () throws InterruptedException{
		/**
		 * 添加鼠标点击事件
		 */
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
 
			/**
			 * 鼠标点击，游戏开始
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (gameover==true){
					score = 0;
					start = false;
					gameover = false;
					
					/**
					 * 初始化地面
					 */
					try {
						ground = new Ground();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					/**
					 * 初始化柱子
					 */
					try {
						column1 = new Column(420);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						column2 = new Column(600);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					/*初始化小鸟
					 */
					try {
						bird = new Bird (140,225);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				}
				start = true ;
				//每次点击鼠标的时候，小鸟当前速度重新变为向上的初速度20
				bird.flappy();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		while(true){
		ground.step();//调用step方法
		if (start == true )
		{  //绘制柱子
			column1.step();
			column2.step();
			//小鸟扇翅膀
			bird.step();
			//碰撞检测
			if(bird.hit(column1, column2, ground)){
				start =false;
				gameover=true;
			}
			if (bird.pass(column1, column2)){
				score++;
			}
		}
		repaint();//重新绘制
		Thread.sleep(1000/60);//一秒绘制60次
		}	
	}
	/**
	 * 主方法
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main (String[] args) throws IOException, InterruptedException{
		/**
		 * 创建一个窗口
		 */
		JFrame frame =new JFrame("FlappyBird");
		/**
		 * 创建一个面板
		 */
		World world =new World();
		/**
		 * 设置窗口大小
		 */
		frame.setSize(320,480);
		/**
		 * 设置窗口不可改变大小
		 */
		frame.setResizable(false);
		/**
		 * 设置关闭窗口退出程序
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		/**
		 * 设置窗口显示在屏幕中间
		 */
		frame.setLocationRelativeTo(null);
		/**
		 * 将面板World添加到窗口中
		 */
		frame.add(world);
		/**
		 * 设置窗口可见
		 */
		frame.setVisible(true);
		/**
		 * 执行开始动作
		 */
	    world.action();
		
		}
}