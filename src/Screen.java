
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Screen extends Pane{
    
    //Counts the number of lines drawn
     //used to identify the drawn dots as belonging 
     //to the same line
     protected long lineCount = 0; 
     
     protected Lines lines = new Lines();
     
     protected Line line = new Line(); 

     
     //This rectangle will be transparent
     //It's purpose is to set the size of the pane it is added to. 
     Rectangle screenBlock = new Rectangle(Specifications.screenWidth, Specifications.screenHeight);
     
    public Screen(){
        //Color the rectangle. Make transparent. 
        screenBlock.setFill(Color.rgb(0, 0, 255, 0));
        
       
        
        getChildren().add(screenBlock);
        
        
        
        
        setOnMouseDragged(e->{
            if(javafx.scene.input.MouseButton.PRIMARY == e.getButton()){
                lineCount += 1;   
                BulletInk b = new BulletInk(e.getX(), e.getY(), Settings.bulletSize, lineCount);
                b.setFill(Color.BLACK);
                getChildren().add(b);
                line.add(b);
            }
            
           });
        
        setOnMouseReleased(e->{        
            if(javafx.scene.input.MouseButton.PRIMARY == e.getButton()){
                line.patch();
            
                getChildren().removeAll(line);
                for(BulletInk b: line){
                    getChildren().add(b);
            
                }
            
            
                lines.add(line);
                line = new Line(); 
            }
        });
    }
    
    
    
}


