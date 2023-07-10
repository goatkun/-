import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class bookuser {
    private JFrame jframe = new JFrame("用户界面");
    private JPanel jpanel = new JPanel();

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("菜单");
    private JMenuItem itemlook = new JMenuItem("查看书本信息");
    private JMenuItem itemlookbor = new JMenuItem("个人中心");
    private JMenuItem itempre = new JMenuItem("预约");
    private JMenuItem itemborrow = new JMenuItem("借书");
    private JMenuItem itemreturn = new JMenuItem("还书");
    private JMenuItem itemlooks = new JMenuItem("多关键字查询书本信息");
    public void user(String name) {

        jframe.setResizable(false);//框架
        jframe.setBounds(300,200,1000,600);

        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg=new ImageIcon("D:\\桌面\\作业\\数据库课设\\src\\main\\java\\tx.png");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        jframe.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)jframe.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        //3.之后把组件和面板添加到窗口面板；
        pan.setLayout(null);//设计按钮布局为空

        Container con = jframe.getContentPane();//容器
        con.add(jpanel);
        //con.setBackground(Color.BLACK);
        //菜单

        menu.add(itemlook);
        menu.add(itemlookbor);
        menu.add(itempre);
        menu.add(itemborrow);
        menu.add(itemreturn);
        menu.add(itemlooks);
        menuBar.add(menu);
        jframe.setJMenuBar(menuBar);
        jframe.setVisible(true);
        //监听
        NewButton jblook = new NewButton("查看书本信息");//按钮
        NewButton jblookbor = new NewButton("个人中心");
        NewButton jbpre = new NewButton("预约");
        NewButton jbborrow = new NewButton("借书");//按钮
        NewButton jbreturn = new NewButton("还书");
        NewButton jbexit = new NewButton("退出");

        JLabel jlnumber = new JLabel("欢迎使用图书借阅系统");//标签
        //设置属性

        Font f1=new Font("微软雅黑",Font.BOLD,40);//定义标签字体样式

        Font f2=new Font("微软雅黑",Font.BOLD,18);//定义按钮字体样式

        jblook.setBounds(110,300,150,40);//按钮
        jblook.setContentAreaFilled(false);//是否填充
        jblook.setFont(f2);//修改字体大小

        jblookbor.setBounds(110,380,150,40);
        jblookbor.setContentAreaFilled(false);
        jblookbor.setFont(f2);

        jbpre.setBounds(410,300,150,40);
        jbpre.setContentAreaFilled(false);
        jbpre.setFont(f2);

        jbborrow.setBounds(410,380,150,40);
        jbborrow.setContentAreaFilled(false);
        jbborrow.setFont(f2);

        jbreturn.setBounds(710,300,150,40);
        jbreturn.setContentAreaFilled(false);
        jbreturn.setFont(f2);

        jlnumber.setBounds(300,60,6000,40);//标签
        jlnumber.setForeground(Color.white);//标签颜色
        jlnumber.setFont(f1);

        jbexit.setBounds(710,380,150,40);
        jbexit.setContentAreaFilled(false);
        jbexit.setFont(f2);
        //添加
        //jblook.setBorderPainted(true);//是否画边框

        //jblook.setFocusPainted(true);//是否画焦点


        pan.add(jblook);
        pan.add(jblookbor);
        pan.add(jbpre);
        pan.add(jbborrow);
        pan.add(jbreturn);
        pan.add(jbexit);
        pan.add(jlnumber);

        jframe.setVisible(true);

        jblook.addActionListener(e -> {
            userlook temp = new userlook();
            temp.userslook();
        });
        jblookbor.addActionListener(e -> {
            userlookbor temp = new userlookbor();
            temp.usersbor(name);
        });
        jbpre.addActionListener(e -> {
            userpre temp = new userpre();
            temp.userspre(name);
        });
        jbborrow.addActionListener(e -> {
            bookborrow temp = new bookborrow();
            temp.bookborrows(name);
        });
        jbreturn.addActionListener(e -> {
            bookreturn temp = new bookreturn();
            temp.bookreturns(name);
        });
        jbexit.addActionListener(e -> {
            Login login = new Login();
            login.init();
            jframe.dispose();
        });


        itemlook.addActionListener(e -> {
            userlook temp = new userlook();
            temp.userslook();
        });
        itemlookbor.addActionListener(e -> {
            userlookbor temp = new userlookbor();
            temp.usersbor(name);
        });
        itempre.addActionListener(e -> {
            userpre temp = new userpre();
            temp.userspre(name);
        });
        itemborrow.addActionListener(e -> {
            bookborrow temp = new bookborrow();
            temp.bookborrows(name);
        });
        itemreturn.addActionListener(e -> {
            bookreturn temp = new bookreturn();
            temp.bookreturns(name);
        });
        itemlooks.addActionListener(e -> {
            sirlooks temp = new sirlooks();
            temp.sirlooks();
        });
    }
}
