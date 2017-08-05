
import java.util.ArrayList;
import javafx.scene.paint.Color;




public class LineRepair{
    ArrayList<BulletInk> line; 
    
    public LineRepair(ArrayList<BulletInk> line){
        this.line = line; 
            
        
    }
    
     public void patch(){
        double slope, distance; 
        double x1, y1, x2, y2; 
        for(int i = 0; i < line.size() - 1; i++){
            
            //Setting bulletNumber in patch() because the line 
            //was incomplete before
            line.get(i).setBulletNumber(i + 1);
            
            slope = calculateSlope(line.get(i), line.get(i + 1));
           
            distance = calculateDistance(line.get(i), line.get(i + 1));
            
            if(distance >= 1){                
                x1 = line.get(i).getX(); 
                y1 = line.get(i).getY();
                x2 = line.get(i+1).getX(); 
                y2 = line.get(i+1).getY();
                BulletInk b; 
               
                if(Math.abs(x1 -x2) > Math.abs(y1-y2)){
                    b = substituteX(line.get(i), line.get(i + 1), slope);
                }
                else{
                    b = substituteY(line.get(i), line.get(i + 1), slope);
                }
                line.add(i + 1, b);
            }
            
        }
        
        //The very last BulletInk is not iterated and so needs its number set 
        //after the loop has iterated
        line.get(line.size() -1).setBulletNumber(line.size() - 1);
    }
    
    public double calculateDistance(BulletInk pointA, BulletInk pointB){
         double aX = pointA.getX(); 
         double aY = pointA.getY();  
         double bX = pointB.getX(); 
         double bY = pointB.getY(); 
            
         return Math.sqrt((Math.pow(aX-bX,2) + Math.pow(aY-bY,2)));
    }
    
    public double calculateSlope(BulletInk pointA, BulletInk pointB){
        double aX = pointA.getX(); 
        double aY = pointA.getY();  
        double bX = pointB.getX(); 
        double bY = pointB.getY(); 
        
        return (bY-aY)/(bX-aX);
    }
    
    
    public BulletInk substituteX(BulletInk pointA, BulletInk pointB, double slope){
        double x = 0.0; 
        long lineNumber = pointA.lineNumber; 
        long bulletNumber = pointA.bulletNumber + 1; 
        
        if(pointB.getX() > pointA.getX()){
           x = pointA.getX() + .5; 
        }
        else{
            x = pointA.getX() - .5; 
        }
        double y = slope * (x -pointA.getX()) + pointA.getY();
        return new BulletInk(x, y, Settings.bulletSize, lineNumber); 
         
    }
    
    public BulletInk substituteY(BulletInk pointA, BulletInk pointB, double slope){
        double y = 0.0; 
        long lineNumber = pointA.lineNumber; 
        long bulletNumber = pointA.bulletNumber + 1; 
        
        if(pointB.getY() > pointA.getY()){
           if(slope > 100000 || slope < -100000 ){
               return calculateYFromInfSlope(pointA, slope, 1);
           }
            
           y = pointA.getY() + .5; 
        }
        else{
            if(slope > 100000 || slope < -100000 ){
               return calculateYFromInfSlope(pointA, slope, -1);
           }
            
            y = pointA.getY() - .5; 
        }

        double x = (y - pointA.getY())/slope + pointA.getX(); 
        return new BulletInk(x, y, Settings.bulletSize, lineNumber);
        
    }
    
    public BulletInk calculateYFromInfSlope(BulletInk pointA, double slope, int sign){
        double diff = 0.0;
        if(sign == 1){
            diff = .5; 
        }
        else{
            diff = -.5; 
        }
        double x = pointA.getX(); 
        double y = pointA.getY() + diff; 
        long lineNumber = pointA.lineNumber; 
        long bulletNumber = pointA.bulletNumber + 1; 
        return new BulletInk(x, y, Settings.bulletSize, lineNumber);
        
    }
    
   
}