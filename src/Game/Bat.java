package Game;



public class Bat extends GameEntity{
    
    public Bat(int gameW, int gameH, int startX, int startY, int w ,int h, int speed){
        super(gameW, gameH, startX, startY, w, h, speed);
    }

    public void setLeft(){
        this.dx=-(this.speed);
    }
    public void setRight(){
        this.dx=this.speed;
    }
    public void shift(){
        this.dy=-2;
    }
    public void goLow(){
        this.dy=2;
    }
    @Override
    public void update(){
        x=x+dx;
        y=y+dy;
        if(this.x<=15){
            this.x=15;
        }
        if(this.x>=this.gameWidth-this.w-15){
            this.x=this.gameWidth-this.w-15;
        }
        if(this.y>570)dy=0;
        this.dx=0;
    }

    
}
