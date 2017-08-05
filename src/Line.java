


public class Line extends java.util.ArrayList<BulletInk>{
    public Line(){
        
    }
    
    public void patch(){
        new LineRepair(this).patch();
    }
    
    public void blowUp(int scalar){
        double x = 0.0; 
        double y = 0.0;
        for(int i = 0; i < size() -1; i++){
            x = get(i).getX(); 
            y = get(i).getY(); 
            
            get(i).setX(x * scalar);
            get(i).setY(y * scalar);
        }
    }
}