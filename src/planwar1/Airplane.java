package planwar1;

public class Airplane extends FlyingObject implements Enemy{
    protected int score;
    private int speed=4;//y的移动步长
   // protected int life;
    public Airplane(){
        score=1;
        life=1;
        img= Main. airplane;
        winth=img.getWidth();
        height=img.getHeight();
        x=(int)(Math.random()*400);
        y=-height;
    }
    @Override
    public void move() {
        //往下，x不变，y变大
        y+=speed;
    }


    @Override
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
