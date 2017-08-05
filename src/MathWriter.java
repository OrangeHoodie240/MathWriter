
import javafx.stage.Stage;
import javafx.scene.Scene; 
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.scene.layout.BorderPane; 

public class MathWriter extends Application{ 
    
    public static void main(String[] args){
        Application.launch(args); 
    }
    
    public void start(Stage primaryStage){
       //BorderPane pane = new BorderPane();
       Screen pane = new Screen(); 
       Scene scene = new Scene(pane); 
       primaryStage.setTitle("Math Writer"); 
       primaryStage.setScene(scene); 
       primaryStage.show(); 
       
    }
}