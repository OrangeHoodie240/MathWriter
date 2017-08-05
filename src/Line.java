


public class Line extends java.util.ArrayList<BulletInk>{
    public Line(){
        
    }
    
    public void patch(){
        new LineRepair(this).patch();
    }
}