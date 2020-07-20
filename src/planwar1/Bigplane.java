package planwar1;

import java.util.Random;

public class Bigplane extends FlyingObject implements Enemy ,Award{
    protected int score;
    protected int award;
    //protected int life;
    private  int speed=2;
    public Bigplane(){
        score=5;
        life=4;
        img= Main.bigplane;
        winth=img.getWidth();
        height=img.getHeight();
        x=(int)(Math.random()*400);
        y=-height;
        award=getType();
    }
    @Override
    public void move() {
        //x不变，y变大
        y+=speed;
    }

    @Override
    public int getType() {
        {
            Random m=new Random();
            if(m.nextInt(2)==1){
                return DOUBLE_FIRE;
            }
            else{
                return LIFE;
            }
        }
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
