package Game;



public abstract class GameEntity {
    protected int x;
    protected int y;
    
    protected int w;
    protected int h;
    
    protected int gameWidth;
    protected int gameHeight;
    
    protected int speed;
    protected int dx;
    protected int dy;
    public GameEntity(int gameW, int gameH, int startX, int startY, int w ,int h, int speed){
        this.gameWidth=gameW;
        this.gameHeight=gameH;
        
        this.x=startX;
        this.y=startY;
        
        this.w=w;
        this.h=h;
        
        this.speed=speed;
        
    }
    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    abstract public void update();
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
    
}
