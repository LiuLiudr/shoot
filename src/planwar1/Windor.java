package planwar1;

import planwar1.Mypanel;

import javax.swing.*;

public class Windor {
   public static void main(String [] age){
       //创建窗口
       JFrame window=new JFrame();
       //设置标题：与 JFrame window=new JFrame("飞机大战");相同
       window.setTitle("飞机大战");
       //设置窗口大小
       window.setSize(400,650);
       //设置窗口默认关闭选项:0-3
       window.setDefaultCloseOperation(3);
       //设置窗口居中
       window.setLocationRelativeTo(null);
       //设置置顶 window.setAlwaysOnTop();
       //设置窗口没有外边框 window.setUndecorated(true);

       //显示窗口
       Mypanel pan=new Mypanel();//创建画板类对象
       window.add(pan);//将画板内容add到窗口
       window.setVisible(true);//显示窗口

   }
}
