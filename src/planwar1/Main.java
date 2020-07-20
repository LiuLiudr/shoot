package planwar1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class Main extends JPanel {
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage airplane;
    public static BufferedImage bigplane;
    public static BufferedImage background;
    public static BufferedImage gameover;
    public static BufferedImage pause;
    public static BufferedImage start;

    private Hero hero=new Hero();//英雄机
    private Bee bee1=new Bee();//蜜蜂
    private Airplane airplane1 =new Airplane();//小敌机
    private Bigplane bigplane1 =new Bigplane();//大敌机

    private  ArrayList<FlyingObject> flyings=new ArrayList<FlyingObject>();
    private  ArrayList<Bullet> bullets=new ArrayList<Bullet>();
    //游戏状态
    public  final int START=0;
    public  final int RUNNING=1;
    public  final int PUSE=2;
    public  final int GAMEOVER=3;
    private int state=START;//游戏状态

    static {//静态代码块
        //一次性读取图片
        try {
            hero0 = ImageIO.read(Main.class.getResourceAsStream("shooter/hero0.png"));
            hero1 = ImageIO.read(Main.class.getResourceAsStream("shooter/hero1.png"));
            bee = ImageIO.read(Main.class.getResourceAsStream("shooter/bee.png"));
            bullet = ImageIO.read(Main.class.getResourceAsStream("shooter/bullet.png"));
            airplane = ImageIO.read(Main.class.getResourceAsStream("shooter/airplane.png"));
            bigplane = ImageIO.read(Main.class.getResourceAsStream("shooter/bigplane.png"));
            background = ImageIO.read(Main.class.getResourceAsStream("shooter/background.png"));
            gameover = ImageIO.read(Main.class.getResourceAsStream("shooter/gameover.png"));
            pause = ImageIO.read(Main.class.getResourceAsStream("shooter/pause.png"));
            start = ImageIO.read(Main.class.getResourceAsStream("shooter/start.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static int WIDTH=400;
    public static int HEIGH=650;

    public static void main(String[] ags) {
        JFrame window = new JFrame();
        window.setTitle("飞机大战");
        window.setSize(WIDTH, HEIGH);//650
        window.setDefaultCloseOperation(3);
        window.setAlwaysOnTop(true); //设置总是在最上面
        Main a = new Main();
        window.add(a);
        window.setVisible(true);//显示窗口
        a.action();
    }

    private java.util.Timer timer=new java.util.Timer(); // 定时器
    private int intervel = 1000 / 100; // 时间间隔(毫秒)

    @Override
    public void paint(Graphics g) {
        super.paint(g);//清除绘画内容
        g.drawImage(background,0,0, this);//画背景
        if(state==START){//state画背景
            g.drawImage(start,0,0, this);
        }
        if(state==GAMEOVER){//gameover背景
            g.drawImage(gameover,0,0, this);
        }
        if(state==PUSE){//gameover背景
            g.drawImage(pause,0,0, this);
        }
        if(state==RUNNING){
            paintHero(g);//画英雄机
            paintFlyingObject(g);//画飞行物
            paintBullets(g);//画子弹
            paintNum(g);//画分数
        }
    }
    //画分数
    public void paintNum(Graphics g){
        Font font=new Font("宋体",Font.BOLD,15);
        g.setFont(font);
        g.setColor(Color.blue);
        g.drawString("NUM  "+sconum,10,30);
        g.drawString("LIFE "+hero.life,10,50);
    }
    //画英雄机
    public void paintHero(Graphics g){
        hero.move();
        g.drawImage(hero.getImg(), hero.getX(), hero.getY(), this);
    }

    //画子弹和子弹移动
    public void paintBullets(Graphics g){
        for(int i=0;i<bullets.size();i++){
            Bullet b=bullets.get(i);
            g.drawImage(b.getImg(), b.getX(),b.getY(), this);
        }
    }
    //画飞行物
    public void paintFlyingObject(Graphics g){
        for(int i=0;i<flyings.size();i++){
            FlyingObject f=flyings.get(i);
            //f.move();//飞行物移动
            g.drawImage(f.getImg(), f.getX(), f.getY(), this);
        }
    }
    //飞行物生成后入场方法
    int flyEnteredIndex=0;
    public void enterAction(){
        flyEnteredIndex++;
        if(flyEnteredIndex%20==0){//200ms生成一个飞行物
            nextOne();
        }
    }
    //生成随机飞行物
    public void nextOne() {
        FlyingObject fly;
        Random random=new Random();
        int type=random.nextInt(20);
        if(type==0||type==5||type==7){
            fly=new Bee();
            //return  new Bee();
        }
        else if (type==2||type==3||type==10){
            fly=new Bigplane();
            //return new Bigplane();
        }
        else{
            fly=new Airplane();
            //return new Airplane();
        }
        flyings.add(fly);
    }
    //越界处理
    public void outOfBandsAction(){
        for(int i=0;i<flyings.size();i++){
            FlyingObject f=flyings.get(i);
            if(f.getY()>=Main.HEIGH){
                flyings.remove(i);
                i--;
            }
        }
        for(int i=0;i<bullets.size();i++){
            Bullet b=bullets.get(i);
            if(b.getY()<=-b.getHeight()){
                bullets.remove(i);
                i--;
            }
        }
    }
    //获取子弹
    int flyBulletsIndex=0;
    public void shootAction(){
        flyBulletsIndex++;
        if(flyBulletsIndex%5==0) {
            Bullet[] b = hero.shoot();
            for (int i = 0; i < b.length; i++) {
                bullets.add(b[i]);
            }
        }
    }
    //移动子弹
      public void moveShoot(){
      for(int i=0;i<bullets.size();i++){
          Bullet b=bullets.get(i);
          b.move();
        }
      }
      //飞行物移动
      public void moveFlyingObject(){
          for(int i=0;i<flyings.size();i++){
              FlyingObject f=flyings.get(i);
              f.move();//飞行物移动
          }
      }
    //判断子弹碰撞
      public int bangShoot(Bullet m,FlyingObject n){
        if(m.getX()>n.getX()&&m.getX()<(n.getX()+n.winth)&&m.getY()<(n.getY()+n.height)&&m.getY()>n.getY()) {
            return 0;
        }
          else{return 1;
          }
      }
      //判断英雄机碰撞
      public int bangHero(FlyingObject n){
          int x1=n.getX()-hero.getWinth()/2;
          int x2=n.getX()+n.getWinth()+hero.getWinth()/2;
          int y1=n.getY()-hero.getHeight()/2;
          int y2=n.getY()+n.getHeight()+hero.getHeight()/2;
          int x=hero.getX()+hero.getWinth()/2;
          int y=hero.getY()+hero.getHeight()/2;
          if(x>=x1&&x<=x2&&y>=y1&&y<=y2){
              hero.doubleFire=0;//英雄级火力变为0模式
              return 1;
          }
          else {return 0;
          }
      }
      //英雄级坠机
    public void hitHero(){
        for(int i=0;i<flyings.size();i++) {
            FlyingObject f = flyings.get(i);
            if(bangHero(f)==1){
                hero.life-=1;
                flyings.remove(i);
                if(hero.life<=0){
                    state=GAMEOVER;
                    hero=new Hero();
                }
            }
        }
    }
    //奖励类型
    public void awardType(Award award){
        if(award.getType()==Award.DOUBLE_FIRE){
            hero.doubleFire=30;
        }
        else {
            hero.addLife();
        }
    }
    //子弹打中
    public int sconum=0;//分数
      //删除击中目标
    public void deleat(){
        for (int i=0;i<bullets.size();i++){
            Bullet bu=bullets.get(i);
            for(int j=0;j<flyings.size();j++) {
                FlyingObject f = flyings.get(j);
                if(bangShoot(bu,f)==0){
                    bullets.remove(i);
                    i--;
                    f.minu();
                    if(f.life<=0){
                        flyings.remove(j);
                        if(f instanceof Enemy){
                            Enemy enemy=(Enemy)f;
                             hero.addScore(enemy.getScore());
                            sconum=hero.getScore();
                            j--;
                        }
                        if(f instanceof Award){
                            Award award=(Award)f;
                            awardType(award);
                        }
                    }
                }
            }
        }
    }
    public void action(){
        //游戏开始的方法
        //TimerTask->要重复的代码 long->定时器什么时候开始 long->定时时间间隔
        timer.schedule(new TimerTask() {//new TimerTask() {}匿名内部类，里面为//定时重复代码
            @Override
            public void run() {
               if(state==RUNNING){
                    enterAction();//生成新飞行物，飞行物移动
                    hero.shoot();//发射子弹
                    shootAction();
                    outOfBandsAction();//判断飞行物和子弹越界
                    moveShoot(); //子弹移动
                    moveFlyingObject();//飞行物移动
                    deleat();//判断子弹碰撞后删除击中目标
                    hitHero();//英雄级坠机
                }
                repaint();
            }
        },1000,80);
        MouseAdapter adapter =new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state==START){
                    state=RUNNING;
                }else if(state==GAMEOVER){
                    state=START;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(state==PUSE){
                    state=RUNNING;
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(state==RUNNING){
                    state=PUSE;
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state==RUNNING){
                    int mouse_x=e.getX();
                    int mouse_y=e.getY();
                    hero.setX(mouse_x-hero.getWinth()/2);
                    hero.setY(mouse_y-hero.getHeight()/3);
                    repaint();
                }
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }
}
