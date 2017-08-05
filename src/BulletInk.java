
import javafx.scene.shape.Rectangle;




public class BulletInk extends Rectangle{
    
    protected long lineNumber = 0; 
    protected long bulletNumber = 0; 
    
    public BulletInk(double x, double y, double size, long lineNumber, long bulletNumber){
        super(x, y, size, size);
        this.lineNumber = lineNumber;
        this.bulletNumber = bulletNumber;
    }
}