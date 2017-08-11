
import javafx.scene.shape.Rectangle;




public class BulletInk extends Rectangle{
    
    protected long lineNumber = 0; 
    protected long bulletNumber = 0; 
    protected Double histX; 
    protected Double histY; 
    
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
    
    public void setHist(){
        histX = getX();
        histY = getY(); 
    }
    
    public double getHistX(){
        return histX; 
    }
    
    public double getHistY(){
        return histY; 
    }
    
    public void restoreToHist(){
        setX(getHistX());
        setY(getHistY());
    }
    
    public boolean hasHist(){
        if(histX == null || histY == null){
            return false;
        }
        return true; 
    }
    
    public void clearHistory(){
        histX = null; 
        histY = null;
    }
}