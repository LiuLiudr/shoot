package planwar1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//1.自定义类，继承Jpanel
public class Mypanel extends JPanel {
    //2.重写自定义绘画方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //下面进行自定义绘画增强
        //设置画笔g 的字体属性
        Font font=new Font("宋体",Font.BOLD,15);//（字体，字体样式（加粗这种），字体大小）
        g.setFont(font);
        /*
        设置画笔颜色
        Color c=new Color(255,255,255);用rgb表示颜色
        g.setColor(c);
         */
        g.setColor(Color.PINK);//用Color常量表示颜色
        //画文字的内容
        paintimage(g);
        paintfont(g);

    }

    private void paintfont(Graphics g) {//文字内容方法
        g.drawString("Life",10,15);//(内容，坐标)
        g.drawString("Num",10,30);
    }
    private void paintimage(Graphics g) {//图片内容方法
        BufferedImage img=null;//初始化
        try{
            img= ImageIO.read(Mypanel.class.getResourceAsStream("shoot/background.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        g.drawImage(img,0,0,this);
    }
}
