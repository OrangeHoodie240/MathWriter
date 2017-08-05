
import java.util.ArrayList;




public class LineRepair{
    ArrayList<BulletInk> line; 
    
    public LineRepair(ArrayList<BulletInk> line){
        this.line = line; 
            
        
    }
    
    public void patch(){
        int lineCounter = 0; 
        while(lineCounter < line.size() -1){
            BulletInk pointA = line.get(lineCounter); 
            BulletInk pointB = line.get(lineCounter + 1); 
            double distance = calculateDistance(pointA, pointB);        
            if(distance >= 1){
                double slope = calculateSlope(pointA, pointB);
                if(slope > 100000000 || slope < -10000000)System.out.println(slope);
                
                if(Math.abs((pointA.getX() - pointB.getX())) >= .3){
                    BulletInk newBullet = substituteY(pointA, pointB, slope);
                    line.add(lineCounter + 1, newBullet);
                }
                else{
                    BulletInk newBullet = substituteX(pointA, pointB, slope);
                    line.add(lineCounter + 1, newBullet);
                }
                
            }
           
            lineCounter += 1; 
        }
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
    
    
    public BulletInk substituteY(BulletInk pointA, BulletInk pointB, double slope){
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
        return new BulletInk(x, y, Settings.bulletSize, lineNumber, bulletNumber); 
    }
    
    public BulletInk substituteX(BulletInk pointA, BulletInk pointB, double slope){
        double y = 0.0; 
        long lineNumber = pointA.lineNumber; 
        long bulletNumber = pointA.bulletNumber + 1; 
        
        if(pointB.getY() > pointA.getY()){
           y = pointA.getY() + .5; 
        }
        else{
            y = pointA.getY() - .5; 
        }

        double x = (y - pointA.getY())/slope + pointA.getX(); 

        return new BulletInk(x, y, Settings.bulletSize, lineNumber, bulletNumber); 
    }
    
    
}