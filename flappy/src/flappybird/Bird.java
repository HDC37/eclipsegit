package flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * С����
 * @author g
 *
 */
public class Bird {
	/**
	 * С������ĵ�
	 */
    int x;
    int y;
    /**
     * ת��С��ĳ�ʼͼƬ
     */
    BufferedImage bird;
    /**
     * ת��3�Ų�ͬ��̬��С��ͼƬ
     */
    BufferedImage[] birds;
    /**
     * �����±�
     */
    int index=0;
    /**
     * �������ٶ�
     */
    int g;
    /**
     * ���ʱ�� ��
     */
    double t;
    /**
     * ��ʼ�ٶ�  ����/��
     */
    double v0;
    /**
     * ��ǰʱ�̵��ٶ�
     */
    double vt;
    /**
     * �˶��ľ���
     */
    double s;
     
    /**
     * С��ķ��еĽǶ�
     */
    double angle;
     //С��Ĵ�С
    double size=26;
    
    public Bird(int x,int y) throws IOException{
    	this.x = x;
    	this.y = y;
    	bird = ImageIO.read(getClass().getResource("37.png"));
    	birds = new BufferedImage[3];
    	for(int i=0;i<3;i++){
    		birds[i] = ImageIO.read(getClass().getResource(i+".png"));
    		bird = birds[0];
    		//��ʼ������
    		g=4;
    		t=0.25;
    		v0=20;
    		vt=v0;
    	}
  }
    /**
     * ����С��
     * @param g
     */
    public void paint(Graphics g){
    //g.drawImage(bird,x,y,null);
    Graphics2D g2d = (Graphics2D)g;
    g2d.rotate(angle,x,y);
    
    //��x ,yΪ���Ļ���ͼƬ
    int x1=x-bird.getWidth()/2;
    int y1=y-bird.getWidth()/2;
    g.drawImage(bird,x1,y1,null);
    g2d.rotate(-angle,x,y);
    }
	/**
	 * С���ȳ���������˶�
	 */
    public void step(){
    	/**
    	 * �����˶�
    	 */
    	//��ǰ�ٶ�
    	double vt1=vt;
    	//��ֱ���ף�����t�����ٶ�
    	double v=vt1-g*t;
    	//v��Ϊ��һ��λ�Ƶĳ�ʼ�ٶ�
    	vt=v;
    	//���о���
    	s =vt1*t-0.5*g*t*t;
    	y=y-(int)s;
    	//����С��ĽǶ�
    	angle = -Math.atan(s/15);  //15=60*0.25
    	
    	/**
    	 * �ȳ��
    	 */
    	index++;
    	bird = birds[index/10%3];
    }
    
    /**
     * ���Ϸɵķ�����ÿ���������ʱ��С�����»�ó��ٵģ����Ϸ�
     * ֱ����һ�ε��֮ǰ����һֱ�����������˶�
     */
    public void flappy(){
    	//new myMusicPlay("fei.wav").start();;
    	vt=v0;
    }
    /**
     * ���С���Ƿ�����棬�컨�壬���ӷ�����ײ
     * @param col1
     * @param col2
     * @param ground
     * @return
     */
    public boolean hit(Column col1,Column col2,Ground ground){
    	//�ж�С���Ƿ����컨�巢������ײ
    	if(y-size/2<=0){
    		return true ;
    	}
    	//����淢������ײ
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

