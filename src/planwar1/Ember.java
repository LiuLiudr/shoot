package planwar1;

import java.awt.image.BufferedImage;

public class Ember {
    protected BufferedImage[] imges;
    protected int index=0;
    protected BufferedImage img;
    protected int interval=10,i=0;
    protected int x,y;
    Ember(FlyingObject object){

    }
    public BufferedImage getImg(){
        return img;
    }
  public void burnDown(){
        i++;
        if(i%interval==0){
            for(index=0;index<imges.length;index++){
                img=imges[index];
            }
        }
    }
}
