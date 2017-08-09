
import javafx.scene.paint.Color;






public class Selector extends javafx.scene.shape.Rectangle{
    protected Screen screen;
    protected boolean sizeFinalized = true; 
    protected Lines lines = new Lines(); 
    
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
    
    public void makeLinesRed(){
        for(Line l: lines){
            for(BulletInk b: l){
                b.setFill(Color.RED);
            }
        }
    }
    
    public void getLines(){
        for(Line l: screen.lines){
            if(containsLine(l)){
                lines.add(l); 
            }
        }
        makeLinesRed(); 
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
    
    
}