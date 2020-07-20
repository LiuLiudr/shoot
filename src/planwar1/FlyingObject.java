package planwar1;

import java.awt.image.BufferedImage;

public abstract  class FlyingObject {
    protected int life;
    protected   int x,y;
    BufferedImage img;
    protected   int winth;
    protected   int height;
    public abstract void move();
    public FlyingObject(){
    }

    public int getLife() {
        return life;
    }
    public void minu(){
        this.life--;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWinth() {
        return winth;
    }

    public void setWinth(int winth) {
        this.winth = winth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
