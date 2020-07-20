package planwar1;


import java.util.Random;

public class Bee extends  FlyingObject implements Enemy,Award{
    protected int score;
    private int xSpeed=3;//移动速度
    private int ySpeed=2;
    protected int award;
   // protected int life;

    public Bee(){
        score=3;
        life=3;
        img= Main.bee;
        winth=img.getWidth();
        height=img.getHeight();
        x=(int)(Math.random()*400);
        y=-height;
        award=getType();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void move() {
        //向右，x变大到最大，y一直变大
        //向左，x开始减小，y继续变大
        x+=xSpeed;
        y+=ySpeed;
        if(x>=Main.WIDTH-winth){//-winth是不让蜜峰出去
            xSpeed=-2;
        }
        if(x<0){
            xSpeed=1;
        }
    }
    @Override
    public int getType() {
        Random m=new Random();
        if(m.nextInt(2)==1){
            return DOUBLE_FIRE;
        }
        else{
            return LIFE;
        }
    }

}
