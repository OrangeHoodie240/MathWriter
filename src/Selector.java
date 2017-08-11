
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;






public class Selector extends javafx.scene.shape.Rectangle{
    protected Screen screen;
    protected boolean sizeInitialized = true; 
    protected Lines lines = new Lines(); 
    protected boolean isMoving = false; 
    protected double mouseXDist = 0.0; 
    protected double mouseYDist = 0.0; 
    protected boolean beingRescaled = false; 
    protected boolean botRIsAnchor = false;
    protected boolean botLIsAnchor = false; 
    protected boolean topRIsAnchor = false ;
    protected boolean topLIsAnchor = false; 
    protected double[] anchorPoint = new double[2]; 
    
    
    protected javafx.scene.shape.Rectangle topL = new Rectangle(); 
    protected javafx.scene.shape.Rectangle topR = new Rectangle(); 
    protected javafx.scene.shape.Rectangle botL = new Rectangle(); 
    protected javafx.scene.shape.Rectangle botR = new Rectangle(); 
    
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
        updateAnchorPoint(); 
        updateScaleSpots(); 
        sizeInitialized = false; 
        isMoving = false; 
    }
    
    public void resize(double x, double y){
        if(sizeInitialized == false){
                    
                        
            double difX = Math.abs(anchorPoint[0] - x);
            setWidth(difX);
            double difY = Math.abs(anchorPoint[1] - y); 
            setHeight(difY);
                
            if(x > anchorPoint[0] && y > anchorPoint[1]){
                topLIsAnchor = true; 
                botRIsAnchor = false; 
                botLIsAnchor = false; 
                topRIsAnchor = false; 
            }
            else if(x > anchorPoint[0] && y < anchorPoint[1]){
                botLIsAnchor = true; 
                botRIsAnchor = false; 
                topRIsAnchor = false;
                topLIsAnchor = false;
            }
            else if(x < anchorPoint[0] && y > anchorPoint[1]){
                topRIsAnchor = true; 
                botRIsAnchor = false; 
                botLIsAnchor = false;
                topLIsAnchor = false; 
            }
            else if(x < anchorPoint[0] && y < anchorPoint[1]){
                botRIsAnchor = true; 
                botLIsAnchor = false; 
                topRIsAnchor = false;
                topLIsAnchor = false; 
            }
            
            if(topLIsAnchor){
                setX(anchorPoint[0]);
                setY(anchorPoint[1]);
            }
            else if(botLIsAnchor){
                double x2 = anchorPoint[0]; 
                double y2 = anchorPoint[1] - getHeight(); 
                setX(x2); 
                setY(y2); 
            }
            else if(botRIsAnchor){
                double x2 = anchorPoint[0] - getWidth(); 
                double y2 = anchorPoint[1] - getHeight();
                setX(x2);
                setY(y2);
            }
            else if(topRIsAnchor){
                double x2 = anchorPoint[0] - getWidth(); 
                double y2 = anchorPoint[1];
                setX(x2); 
                setY(y2);
            }

        
        }
        
        
       
    }
    
    
    public void loadLines(){
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
        lines.clear();
    }
    
    public void toggleSizeInitialized(){
        if(sizeInitialized == true){
            sizeInitialized = false;
        }
        else{
            sizeInitialized = true;
            updateScaleSpots(); 
            
        }
    }
    
    public boolean getSizeInitialized(){
        return sizeInitialized; 
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
        
        updateScaleSpots();
        updateAnchorPoint(); 
    }
    public void moveLines(double x, double y){
        for(Line l: lines){
            l.moveLine(x,y);
            
        }
    }
    
    
    public void updateScaleSpots(){
        double size = getWidth()/8; 
        
        topL.setX(getX()); 
        topL.setY(getY()); 
        topL.setWidth(size);
        topL.setHeight(size);
        
        topR.setX(getX() + getWidth() - size); 
        topR.setY(getY());
        topR.setWidth(size);
        topR.setHeight(size);
        
        
        botL.setX(getX()); 
        botL.setY(getY() + getHeight() - size);
        botL.setWidth(size); 
        botL.setHeight(size);
        
        botR.setX(getX() + getWidth() - size); 
        botR.setY(getY() + getHeight() -size); 
        botR.setWidth(size); 
        botR.setHeight(size);
        
    }
    
    public void updateAnchorPoint(){
        anchorPoint[0] = getX();
        anchorPoint[1] = getY(); 
    }
    
    public Rectangle getTopR(){
        return topR; 
    }
    
    public Rectangle getTopL(){
        return topL; 
    }
    
    public Rectangle getBotR(){
        return botR; 
    }
    
    public Rectangle getBotL(){
        return botL; 
    }
    
    public boolean isEmpty(){
        if(lines.isEmpty()){
            return true; 
        }
        else{
            return false; 
        }
    }
    
    public boolean isBeingRescaled(){
        return beingRescaled; 
    }
    
    public void toggleBeingRescaled(){
        if(beingRescaled){
            beingRescaled = false; 
        }
        else{
            beingRescaled = true; 
        }
    }
    
    public void scaleFromTopL(double x, double y){
        
    }
}