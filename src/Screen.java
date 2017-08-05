
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
     protected ArrayList<BulletInk> line = new ArrayList<>(); 
     //Used to count the dots in a line
     protected long bulletCount = 0; 
     
     //This rectangle will be transparent
     //It's purpose is to set the size of the pane it is added to. 
     Rectangle screenBlock = new Rectangle(Specifications.screenWidth, Specifications.screenHeight);
     
    public Screen(){
        //Color the rectangle. Make transparent. 
        screenBlock.setFill(Color.rgb(0, 0, 255, 0));
        
       
        
        getChildren().add(screenBlock);
        
        
        
        
        setOnMouseDragged(e->{
            lineCount += 1;   
            bulletCount +=1; 
            BulletInk b = new BulletInk(e.getX(), e.getY(), Settings.bulletSize, lineCount, bulletCount);
            b.setFill(Color.BLACK);
            getChildren().add(b);
            line.add(b);
            
           });
        
        setOnMouseReleased(e->{
            bulletCount = 0; 
            new LineRepair(line).patch();
           
            
            getChildren().clear();  
            for(BulletInk b: line){
                getChildren().add(b);
            
            }
            
            double slope = 0.0;
            double distance = 0.0;
            for(int i = 0; i < line.size() -1; i ++){
                BulletInk a = line.get(i); 
                BulletInk b = line.get(i + 1); 
                slope = new LineRepair(line).calculateSlope(a, b); 
                distance = new LineRepair(line).calculateDistance(a, b);
                
                /*
                System.out.print("bullet Number: " + a.bulletNumber + "   slope with next bullet: " + slope + "   coords:   " +   a.getX() + ", " + a.getY());
                System.out.println("  Distance with next: " + distance);
               */
            }
            
            line = new ArrayList<>(); 
        });
    }
    
    
    
}


