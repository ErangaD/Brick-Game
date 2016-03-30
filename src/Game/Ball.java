package Game;



public class Ball extends GameEntity{
    
    
    public Ball(int gameW, int gameH, int startX, int startY, int size, int speed){
        super(gameW, gameH, startX, startY, size, size, speed);
        dx=speed;
        dy=speed;       
    }
    
    public void update(){
        
        if(y+w >gameHeight){
            dy=-5;
        }
        if(x+w>gameWidth-15){
            dx=-5;
        }
        if(x<15){
            dx=5;
        }
        if(y<0){
            dy=5;
        }
        x=x+dx;
        y=y+dy;
        
    }
    public void goDown(){
        dy=5;
    }
    
    public void reverse(){
        dy=-dy-2;
    }

    public int getSize() {
        return w;
    }
    
}
