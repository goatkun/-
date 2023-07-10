import javax.swing.*;
import java.awt.*;

public class NewButton extends JButton//按钮修改类
{
    private String s;
    private int sizeX;
    private int sizeY;
    public NewButton(String s )//传递图片引用
    {
        super(s);
    }
    protected void paintComponent(Graphics g)//绘制按钮内容
    {
        g.setColor(new Color(31,171,137));
        g.fillRoundRect(0,0,getSize().width-1,getSize().height-1,15,15);
        //绘制一个圆角矩形getSize()为获取组件的大小
        //g.drawImage(img, 0,0,50, 40, null);//除了形状外还可以为按钮绘制一个图片来美化按钮
        super.paintComponent(g);//使用父类函数绘制一个焦点框
    }
    protected void paintBorder(Graphics g)   //绘制按钮边框
    {
        g.drawRoundRect(0,0,getSize().width-1,getSize().height-1,15,15);
    }
}
