package planwar1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero extends FlyingObject{
    protected int life;
    protected int score;
    protected Bullet[] bullets;
    protected int index=0;
    protected BufferedImage[] imges={};//英雄机图片
    protected int doubleFire;
    protected int award;

    public Hero(){
        doubleFire=0;
        x=160;
        y=490;
        imges=new BufferedImage[]{Main.hero0,Main.hero1};
        img= Main.hero0;
        winth=img.getWidth();
        height=img.getHeight();
        life=3;
    }
    public void addScore(int score){//分数加
       this.setScore(this.getScore()+score);
    }
    public void addLife(){
        this.setLife(this.getLife()+1);
    }
    @Override
    public void move() {
        //切换图片
        if(imges.length>0){
            img = imges[index++%2];  //切换图片hero0，hero1
        }
    }
    public Bullet[] shoot(){//发射子弹并有返回值
        if(doubleFire==0) {
            Bullet[] bullets = new Bullet[1];
            bullets[0] = new Bullet(this.getX() + this.getWinth() / 2, this.getY());//根据英雄机获取x，y
            return bullets;
        }
        else{
            Bullet[] bullets=new Bullet[2];
            bullets[0]=new Bullet(this.getX()+this.getWinth()/5,this.getY());
            bullets[1]=new Bullet(this.getX()+this.getWinth()-this.getWinth()/5,this.getY());
            doubleFire--;
            return bullets;
        }
    }

    public int getDoubleFire() {
        return doubleFire;
    }

    public void setDoubleFire(int doubleFire) {
        this.doubleFire = doubleFire;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }
}
