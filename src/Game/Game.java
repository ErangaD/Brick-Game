package Game;



import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;
        private ArrayList<Brick> bricks=new ArrayList<>();
	private BufferedImage buffer; // Create the buffer
        private long waitingTime;
        private long startTime;
        private Ball ball;
        private Bat bat;
        private BufferedImage img;
        private BufferedImage ballImage;
        private BufferedImage batImage,brickImage;
        private int numberOfBrickCollisions;
        private boolean isLeft;
        private boolean isRight;
        
	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
                ball=new Ball(dim.width,dim.height, 200,200, 10,5 );
                waitingTime=50;
                bat=new Bat(dim.width,dim.height, 50,dim.height-50, 50, 20, 5);
                
		buffer = new BufferedImage(dim.width, dim.height,
				BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
                numberOfBrickCollisions=0;
                try{
                     img=ImageIO.read(getClass().getResource("bg.png"));
                     batImage=ImageIO.read(getClass().getResource("bat.png"));
                     brickImage=ImageIO.read(getClass().getResource("brick.png"));
                     ballImage=ImageIO.read(getClass().getResource("ball.png"));
                     
                }
                catch(IOException s){
                }
                isLeft=false;
                isRight=false;
                for(int line=60;line<121;line+=20){
                    
                    for(int i=20;i<370;i+=20){
                       bricks.add(new Brick(i, line, 18));
                       
                    }
                }
	}

	/**
	 * Start the game
	 */
	public void Start() {
                
                startTime = System.currentTimeMillis()/1000;
                while (true) {			
                        ball.update();
                        if(isLeft) bat.setLeft();
                        if(isRight) bat.setRight();
                        
                        detectCollision();
                        bat.update();
                        // Draw the buffer
                        drawBuffer();
			// Paint the buffer on screen
			drawScreen();
                        
                        if(ball.getY()>580){
                            drawEndBuffer();
                            drawScreen();
                            break;
                        }
                        if(bricks.isEmpty()){
                            drawFinishedBuffer();
                            drawScreen();
                            break;
                        }
                        
			try {
				Thread.sleep(waitingTime);
			} catch (InterruptedException e) {
			}
		}
	}
        public long getFinishedTime(){
            return System.currentTimeMillis();
        }
	public void detectCollision(){
            Rectangle rectBall=new Rectangle(ball.getX(),ball.getY(),ball.getSize(),ball.getSize());
            Rectangle rectBat=new Rectangle(bat.getX(), bat.getY(), bat.getW(),bat.getH());
            if(rectBat.intersects(rectBall)){
                ball.reverse();
            }
            
            for(Brick brick:bricks){
                Rectangle rectBrick=new Rectangle(brick.getX(), brick.getY(), brick.getW(), brick.getH());
                if(rectBall.intersects(rectBrick)){
                    numberOfBrickCollisions++;
                    ball.goDown();                    
                    ArrayList<Brick> copy=new ArrayList<>(bricks);
                    copy.remove(brick);
                    bricks=copy;                    
                }
            }
            
        }
        
        public void drawEndBuffer(){
            Graphics2D b = buffer.createGraphics();
            
            b.drawString("You Loose!", 150, 300);
            b.drawString("Score :"+numberOfBrickCollisions*(getFinishedTime()-startTime)/1000000000, 150,350 );
        }
        private void drawFinishedBuffer() {
            Graphics2D b = buffer.createGraphics();
            
            b.drawString("You Won!", 150, 300);
            b.drawString("Score :"+numberOfBrickCollisions*(getFinishedTime()-startTime)/1000000000, 150,350 );
            
        }

	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
		Graphics2D b = buffer.createGraphics();
		b.setColor(Color.BLACK);
		b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());                
                
                b.fillOval(0, 0, 5, 5);
                
                b.drawImage(img, 0, 0, this);
                
                for(Brick brick:bricks){
                    
                    b.drawImage(brickImage,brick.getX(),brick.getY(),brick.getW(),brick.getH() ,this);
                }
                
                b.drawImage(ballImage,ball.getX(),ball.getY(),ball.getW(),ball.getH(), this); 
                b.drawImage(batImage, bat.getX(), bat.getY(),bat.getW()+5,bat.getH(), this);
        }
        
        
        /**
	 *  Update it to the screen
	 */
	public void drawScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

    

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyCode()==37){
            isLeft=true;
            bat.setLeft();
        }
        if(e.getKeyCode()==39){
            isRight=true;
            bat.setRight();
        }
        if(e.getKeyCode()==32){
            
            bat.shift();
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==37){
            isLeft=false;
            bat.setLeft();
        }
        if(e.getKeyCode()==39){
            isRight=false;
            bat.setRight();
        }
        if(e.getKeyCode()==32){
            
            bat.goLow();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    
}
