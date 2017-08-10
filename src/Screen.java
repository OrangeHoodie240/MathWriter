
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
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

     protected boolean primaryClick = false; 
     
     protected boolean secondaryClick = false; 
     
     protected Selector selector = new Selector(this);
     
     protected Eraser eraser = new Eraser(this); 
     
     //This rectangle will be transparent
     //It's purpose is to set the size of the pane it is added to. 
     Rectangle screenBlock = new Rectangle(Specifications.screenWidth, Specifications.screenHeight);
     
    public Screen(){
        //Color the rectangle. Make transparent. 
        
        
        screenBlock.setFill(Color.rgb(0, 0, 255, 0));
        
       
        
        getChildren().add(screenBlock);
        
        
        
        setOnMousePressed(e ->{
            if(MouseButton.SECONDARY == e.getButton()){
                toggleSecondaryClick();
                if(getChildren().contains(selector)){
                    getChildren().remove(selector);
                    selector.emptyLines(); 
                      
                }
            }
            else if(MouseButton.PRIMARY == e.getButton()){
                togglePrimaryClick(); 
                
                if(selector.getSizeFinalized() == true && !selector.contains(e.getX(), e.getY())){
                    if(getChildren().contains(selector)){
                        getChildren().remove(selector);
                        selector.emptyLines(); 
                        
                    }
                }
            }
        });
        setOnMouseDragged(e->{

            if(MouseButton.PRIMARY == e.getButton() && !getChildren().contains(selector)){
                lineCount += 1;   
                    BulletInk b = new BulletInk(e.getX(), e.getY(), Settings.bulletSize, lineCount);
                    b.setFill(Color.BLACK);
                    getChildren().add(b);
                    line.add(b);
            }
            
            if(MouseButton.PRIMARY == e.getButton() && getChildren().contains(selector)){
                if(selector.getSizeFinalized() && selector.contains(e.getX(), e.getY())){
                    if(selector.isMoving() == false){
                        selector.toggleIsMoving();
                        selector.setMouseDistance(e.getX(), e.getY());
                    } 
                }
            }
            
                     
            if(primaryClick == true && secondaryClick == true){
               if(!getChildren().contains(selector)){
                    selector.setSelector(e.getX(), e.getY());
                    getChildren().add(selector);
               }
               else if(selector.getSizeFinalized() == false){
                    selector.resize(e.getX(), e.getY());
               }
            }
            
            if(selector.isMoving()){
                selector.moveSelector(e.getX(), e.getY());
            }
            
            if(e.getButton() == MouseButton.SECONDARY && !getChildren().contains(selector)){
                if(!getChildren().contains(eraser)){
                    getChildren().add(eraser);
                    eraser.setX(e.getX());
                    eraser.setY(e.getY());
                }
                else{
                    eraser.setX(e.getX());
                    eraser.setY(e.getY());
                    eraser.erase(); 
                }
            }
            if(e.getButton() == MouseButton.SECONDARY && getChildren().contains(selector)){
                getChildren().remove(eraser);
            }
           
           });
        
        setOnMouseReleased(e->{        
            if(MouseButton.PRIMARY == e.getButton()){
                togglePrimaryClick(); 
                if(selector.isMoving()){
                    selector.toggleIsMoving();
                }
                
                line.patch();
            
                getChildren().removeAll(line);
                for(BulletInk b: line){
                    getChildren().add(b);
            
                }
            
            
                lines.add(line);
                line = new Line(); 
            }
            else if(MouseButton.SECONDARY == e.getButton()){
                toggleSecondaryClick(); 
                if(getChildren().contains(eraser)){
                    getChildren().remove(eraser);
                }
            }
            
            //seltor finalized
            if(primaryClick == false && secondaryClick == false){
                    if(selector.getSizeFinalized() == false){
                        selector.toggleSizeFinalized();
                        selector.getLines(); 
                    }
                    
            }
            
            
            
        });
    }
    
    public void togglePrimaryClick(){
        if(primaryClick == false){
            primaryClick =true; 
        }
        else{
            primaryClick = false;
        }
    }
    
    public void toggleSecondaryClick(){
        if(secondaryClick == false){
            secondaryClick = true; 
        }
        else{
            secondaryClick = false; 
        }
    }
    
}


