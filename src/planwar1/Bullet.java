package planwar1;

public class Bullet extends FlyingObject{
    private  int speed=7;//子弹速度
    public Bullet(int x,int y){
        //子弹坐标跟着英雄机
        img= Main.bullet;
        winth=img.getWidth();
        height=img.getHeight();
        this.x=x;
        this.y=y;

    }
    @Override
    public void move() {
        y-=speed;
    }
}
