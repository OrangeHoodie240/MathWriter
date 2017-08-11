


public class Line extends java.util.ArrayList<BulletInk>{
    
    public Line(){
        
    }
    
    public void patch(){
        if(size() > 0){
            new LineRepair(this).patch();
    
        }
    }
    public void blowUp(double scalar){
        if(size() > 0){
            
            double midX = getCenterPoint()[0];
            double midY = getCenterPoint()[1];
      
            double x = 0.0; 
            double y = 0.0;
            for(int i = 0; i < size() ; i++){
                x = get(i).getX(); 
                y = get(i).getY(); 
            
                get(i).setX(x * scalar);
                get(i).setY(y * scalar);
            }
        
            double tempMidX = getCenterPoint()[0]; 
            double tempMidY = getCenterPoint()[1];
        
            double moveX = (Math.abs(midX - tempMidX));
            if(tempMidX > midX) moveX *=  -1; 
        
            double moveY = Math.abs(midY - tempMidY);
            if(tempMidY > midY) moveY *= -1;
        
            moveLine(moveX, moveY);
        
        }
    }
    
    public void shrinkDown(int scalar){
        if(size() > 0){
            
            double midX = getCenterPoint()[0];
            double midY = getCenterPoint()[1];
      
            double x = 0.0; 
            double y = 0.0;
            for(int i = 0; i < size() ; i++){
                x = get(i).getX(); 
                y = get(i).getY(); 
            
                get(i).setX(x / scalar);
                get(i).setY(y / scalar);
            }
        
            double tempMidX = getCenterPoint()[0]; 
            double tempMidY = getCenterPoint()[1];
        
            double moveX = (Math.abs(midX - tempMidX));
            if(tempMidX > midX) moveX *=  -1; 
        
            double moveY = Math.abs(midY - tempMidY);
            if(tempMidY > midY) moveY *= -1;
        
            moveLine(moveX, moveY);
        
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
       
       
       for(int i = 0; i < size(); i++){
           if(get(i).getX() > maxX){
               maxX = get(i).getX();
           }
           else if(get(i).getX() < minX){
               minX = get(i).getX(); 
           }
           if(get(i).getY() > maxY){
               maxY = get(i).getY(); 
           }
           else if(get(i).getY() < minY){
               minY = get(i).getY();
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
        
        centerPoint[0] = midX;
        centerPoint[1] = midY;
        return centerPoint;
    }
    
   
    public void moveLine(double moveX, double moveY){
        if(moveX != 0.0 || moveY != 0.0){

            for(BulletInk b: this){
                b.setX(b.getX() + moveX);
                b.setY(b.getY() + moveY); 
            }
        }
    }
    
    
    public double getMaxX(){
        double maxX = get(0).getX();
        for(int i = 0; i < size(); i++){
            if(get(i).getX() > maxX){
                maxX = get(i).getX(); 
            }
        }
                    return maxX; 
    }
    
    public double getMaxY(){
        double maxY = get(0).getY();
        for(int i = 0; i < size(); i++){
            if(get(i).getY() > maxY){
                maxY = get(i).getY(); 
            }
        }
                    return maxY; 
    }
    
    
    public double getMinX(){
        double minX = get(0).getX();
        for(int i = 0; i < size(); i++){
            if(get(i).getX() < minX){
                minX = get(i).getX(); 
            }
        }
                    return minX; 
    }
    
    
    public double getMinY(){
        double minY = get(0).getY();
        for(int i = 0; i < size(); i++){
            if(get(i).getY() < minY){
                minY = get(i).getY(); 
            }
        }
                    return minY; 
    }
    
    public Lines split(){
        Lines lines = new Lines(); 
        Line newLine = new Line();
        
        for(BulletInk b: this){
            if(!b.isBlank()){
                newLine.add(b); 
            }
            else{
                if(newLine.size() > 0){
                    lines.add(newLine); 
                    newLine = new Line(); 
                }
            }
        }
        
        if(newLine.size() > 0) lines.add(newLine);
        return lines; 
    }
    
    public boolean haveHistoryCoordinates(){
        for(BulletInk b: this){
            if(!b.hasHist()){
                return false;
            }
        }
        return true; 
    }
    
    public void setHistoryCoordinates(){
        for(BulletInk b: this){
            b.setHist(); 
        }
    }
    
    public void restoreToHistory(){
        for(BulletInk b: this){
            b.restoreToHist();
        }
    }
    
    public void clearLineHistory(){
        for(BulletInk b: this){
            b.clearHistory(); 
        }
    }
}