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
//�ζ�
/**
 * ��������
 * @author g
 * ������ͼƬ����ʼͼƬ�ӵ��ڴ�
 */
public class World extends JPanel{
  /**
   * ����
   */
	BufferedImage background;
  /**
   * ��ʼͼƬ
   */
	BufferedImage startImage;
	/**
	 * ���ӵ���
	 */
    Ground ground;
    
   /**
    * ����
    */
    Column column1;
    Column column2;
    boolean start;
    boolean gameover;
    Bird bird;
    int score;
/**
 * ����һ�����췽��,��ʼ��
 * @throws IOException 
 */
public World() throws IOException{
	/**
	 *������ͼƬ����ʼͼƬ���ص��ڴ���
	 */
	background = ImageIO.read(getClass().getResource("bg.png"));
	startImage = ImageIO.read(getClass().getResource("start.png"));
	/**
	 * ��ʼ������
	 */
	ground = new Ground();
	/**
	 * ��ʼ������
	 */
	column1 = new Column(420);
	column2 = new Column(600);
	/*��ʼ��С��
	 */
	bird = new Bird (140,225);
}
	/**
	 * ����ͼƬ
	 */
	public void paint(Graphics g){
		/**
		 * ���Ʊ���ͼƬ
		 */
		g.drawImage(background,0,0,null);
		
		/**
	     * ��������
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
		 * ���ƿ�ʼͼƬ
		 */
		if(start == false){
		g.drawImage(startImage,0,0,null);
		
		}
	    /**
	  	 * ʹ��ground���ƿ��ƶ�����ͼƬ
	  	 */
	    
	  		    ground.paint(g);
	  		    
	  		//���Ʒ���
	  g.setFont(new Font(Font.MONOSPACED,Font.BOLD ,25));
	  g.setColor(Color.white );
	  g.drawString("score="+score,25,40)	;	    
	  		    
		
	}
	/**
	 * ��Ϸ��ʼ�Ķ���
	 * @throws InterruptedException 
	 */
	public void action () throws InterruptedException{
		/**
		 * ���������¼�
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
			 * ���������Ϸ��ʼ
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (gameover==true){
					score = 0;
					start = false;
					gameover = false;
					
					/**
					 * ��ʼ������
					 */
					try {
						ground = new Ground();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					/**
					 * ��ʼ������
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
					/*��ʼ��С��
					 */
					try {
						bird = new Bird (140,225);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				}
				start = true ;
				//ÿ�ε������ʱ��С��ǰ�ٶ����±�Ϊ���ϵĳ��ٶ�20
				bird.flappy();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	
		while(true){
		ground.step();//����step����
		if (start == true )
		{  //��������
			column1.step();
			column2.step();
			//С���ȳ��
			bird.step();
			//��ײ���
			if(bird.hit(column1, column2, ground)){
				start =false;
				gameover=true;
			}
			if (bird.pass(column1, column2)){
				score++;
			}
		}
		repaint();//���»���
		Thread.sleep(1000/60);//һ�����60��
		}	
	}
	/**
	 * ������
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main (String[] args) throws IOException, InterruptedException{
		/**
		 * ����һ������
		 */
		JFrame frame =new JFrame("FlappyBird");
		/**
		 * ����һ�����
		 */
		World world =new World();
		/**
		 * ���ô��ڴ�С
		 */
		frame.setSize(320,480);
		/**
		 * ���ô��ڲ��ɸı��С
		 */
		frame.setResizable(false);
		/**
		 * ���ùرմ����˳�����
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		/**
		 * ���ô�����ʾ����Ļ�м�
		 */
		frame.setLocationRelativeTo(null);
		/**
		 * �����World��ӵ�������
		 */
		frame.add(world);
		/**
		 * ���ô��ڿɼ�
		 */
		frame.setVisible(true);
		/**
		 * ִ�п�ʼ����
		 */
	    world.action();
		
		}
}