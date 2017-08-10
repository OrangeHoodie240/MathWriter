
import javafx.scene.shape.Rectangle;




public class BulletInk extends Rectangle{
    
    protected long lineNumber = 0; 
    protected long bulletNumber = 0; 
    protected boolean blank = false; 
    
    public BulletInk(double x, double y, double size, long lineNumber){
        super(x, y, size, size);
        this.lineNumber = lineNumber;
    }
    
    
    public long getBulletNumber(){
        return bulletNumber;
    }
    
    public void setBulletNumber(long bulletNumber){
        this.bulletNumber = bulletNumber; 
    }
    
    public void setBlank(){
        blank = true; 
    }
    
    public boolean isBlank(){
        return blank; 
    }
}