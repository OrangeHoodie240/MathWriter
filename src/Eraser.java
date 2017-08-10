
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;




public class Eraser extends Rectangle{
    Screen screen;
    
    public Eraser(Screen screen){
        this.screen = screen;
        setWidth(10); 
        setHeight(10); 
        setStroke(Color.PINK.darker());
        setFill(Color.PINK);
    }
    
    public void erase(){
        
        Lines markedLines = new Lines(); 
        
        for(Line l: screen.lines){
            for(BulletInk b: l){
                if(contains(b.getX(),b.getY())){
                    screen.getChildren().remove(b); 
                    b.setBlank(); 
                    if(!markedLines.contains(l)){
                        markedLines.add(l); 
                    }
                }
            }
        }
        
        Lines newLines = new Lines(); 
        for(Line l: markedLines){
            newLines.addAll(l.split()); 
            screen.lines.remove(l);
        }
        screen.lines.addAll(newLines);
    }
    
}