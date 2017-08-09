
import javafx.scene.paint.Color;






public class Selector extends javafx.scene.shape.Rectangle{
    protected Screen screen;
    protected boolean sizeFinalized = true; 
    protected Lines lines = new Lines(); 
    protected boolean isMoving = false; 
    protected double mouseXDist = 0.0; 
    protected double mouseYDist = 0.0; 
    
    public Selector(Screen screen){
        this.screen = screen;
        setFill(Color.color(0, 1, 0, .1));
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }
    
    public void setSelector(double x, double y){
        setX(x);
        setY(y);
        setWidth(10);
        setHeight(10);
        sizeFinalized = false; 
        isMoving = false; 
    }
    
    public void resize(double x, double y){
        if(sizeFinalized == false){
            if(x > getX()){
            double difX = Math.abs(getX() - x);
            setWidth(difX);
            } 
        
            if(y > getY()){
                double difY = Math.abs(getY() - y); 
                setHeight(difY);
            }
        
        
        }
        
        
       
    }
    
    
    public void getLines(){
        for(Line l: screen.lines){
            if(containsLine(l)){
                lines.add(l); 
            }
        } 
    }
    
    public boolean containsLine(Line l){
        boolean contains = true; 
        for(BulletInk b: l){
            if(!contains(b.getX(), b.getY())){
                contains = false; 
            }
        }
        return contains;
    }
    
    public void emptyLines(){
        lines = new Lines(); 
    }
    
    public void toggleSizeFinalized(){
        if(sizeFinalized == true){
            sizeFinalized = false;
        }
        else{
            sizeFinalized = true;
        }
    }
    
    public boolean getSizeFinalized(){
        return sizeFinalized; 
    }
    
    public boolean isMoving(){
        return isMoving; 
    }
    
    public void toggleIsMoving(){
        if(isMoving){
            isMoving = false; 
        }
        else{
            isMoving = true; 
        }
    }
    
    public void setMouseDistance(double x, double y){
        mouseXDist = Math.abs(getX() - x ); 
        mouseYDist = (Math.abs(getY() - y)); 
    }
    
    public void moveSelector(double x, double y){
        double originalSelectorX = getX();
        double originalSelectorY = getY();
        
        
        setX(x - mouseXDist);
        setY(y - mouseYDist); 
        
        double xChange = 0.0;
        if(originalSelectorX > getX()){
            xChange = -Math.abs(originalSelectorX -getX()); 
        }
        else{
            xChange = Math.abs(originalSelectorX - getX());
        }

        double yChange = 0.0;
        if(originalSelectorY > getY()){
            yChange = -Math.abs(originalSelectorY -getY()); 
        }
        else{
            yChange = Math.abs(originalSelectorY - getY());
        }
        
        moveLines(xChange, yChange);
    }
    public void moveLines(double x, double y){
        for(Line l: lines){
            l.moveLine(x,y);
            
        }
    }
    
}