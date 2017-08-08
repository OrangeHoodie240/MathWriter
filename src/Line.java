


public class Line extends java.util.ArrayList<BulletInk>{
    protected double[] centerPoint; 
    
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
    
    public void shrinkDown(int scalar){
        double x = 0.0; 
        double y = 0.0;
        for(int i = 0; i < size() -1; i++){
            x = get(i).getX(); 
            y = get(i).getY(); 
            
            get(i).setX(x / scalar);
            get(i).setY(y / scalar);
        }
    }
    
    
    public double[] getCenterPoint(){       
        double[] centerPoint = new double[2];
        double maxX = this.get(0).getX(); 
        double maxY = this.get(0).getY();
        double minX = this.get(0).getX();
        double minY = this.get(0).getY();
        double midX; 
        double midY;
        
        for(BulletInk b: this){
            if(b.getX() > maxX){
                maxX = b.getX();
            }
            else if(b.getX() < minX){
                minX = b.getX();
            }
            
            if(b.getY() > maxY){
                maxY = b.getY();
            }
            else if(b.getY() < minY){
                minY = b.getY();
            }
        }
        
        if(maxX == minX){
            midX = maxX;
        }
        else{
            midX = ( minX + (Math.abs(minX-maxX)/2)); 
        }
        
        if(minY == maxY){
            midY = maxY;
        }
        else{
            midY = (minY + (Math.abs(minY - maxY)/2));
        }
        
        System.out.println(maxX); 
        System.out.println(minX); 
        System.out.println(maxY);
        System.out.println(minY);
        
        centerPoint[0] = midX;
        centerPoint[1] = midY;
        return centerPoint;
    }
    
   
    public void moveLine(double moveX, double moveY){
        if(moveX != 0.0 || moveY != 0.0){
            
            System.out.println("Happend");
            for(BulletInk b: this){
                b.setX(b.getX() + moveX);
                b.setY(b.getY() + moveY); 
            }
        }
    }
}