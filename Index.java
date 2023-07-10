import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Index {
    private JFrame jframe = new JFrame("管理员界面");
    private JPanel jpanel = new JPanel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("菜单");
    private JMenuItem itemlook = new JMenuItem("查看书本信息");
    private JMenuItem itemlooks = new JMenuItem("多关键字查询");
    private JMenuItem itemputin = new JMenuItem("录入书本信息");
    private JMenuItem itemdel = new JMenuItem("删除书本信息");
    private JMenuItem itempre = new JMenuItem("查看预约信息");
    private JMenuItem itemborrow = new JMenuItem("查看借书信息");
    private JMenuItem itembooker = new JMenuItem("查看人员信息");
    private JMenuItem itemreturn = new JMenuItem("退出");
    public void init() {
        jframe.setResizable(false);//框架
        jframe.setBounds(300,200,1000,600);
        Container con = jframe.getContentPane();//容器
        jpanel.setLayout(null);
        con.add(jpanel);

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

        NewButton nblook = new NewButton("查看书本信息");//按钮
        NewButton nblooks = new NewButton("多关键字查询");
        NewButton nbputin = new NewButton("录入书本信息");
        NewButton nbdel = new NewButton("删除书本信息");//按钮
        NewButton nbpre = new NewButton("查看预约信息");
        NewButton nbborrow = new NewButton("查看借书信息");
        NewButton nbreturn = new NewButton("退出");

        JLabel jlnumber = new JLabel("欢迎来到管理员界面");//标签
        Font f1=new Font("微软雅黑",Font.BOLD,40);//定义标签字体样式

        Font f2=new Font("微软雅黑",Font.BOLD,18);//定义按钮字体样式

        nblook.setBounds(110,300,150,40);//按钮
        nblook.setContentAreaFilled(false);//是否填充
        nblook.setFont(f2);//修改字体大小

        nblooks.setBounds(110,380,150,40);
        nblooks.setContentAreaFilled(false);
        nblooks.setFont(f2);

        nbputin.setBounds(410,300,150,40);
        nbputin.setContentAreaFilled(false);
        nbputin.setFont(f2);

        nbdel.setBounds(410,380,150,40);
        nbdel.setContentAreaFilled(false);
        nbdel.setFont(f2);

        nbpre.setBounds(710,300,150,40);
        nbpre.setContentAreaFilled(false);
        nbpre.setFont(f2);

        nbborrow.setBounds(710,380,150,40);
        nbborrow.setContentAreaFilled(false);
        nbborrow.setFont(f2);

        nbreturn.setBounds(410,460,150,40);
        nbreturn.setContentAreaFilled(false);
        nbreturn.setFont(f2);

        jlnumber.setBounds(310,60,6000,40);//标签
        jlnumber.setForeground(Color.white);//标签颜色
        jlnumber.setFont(f1);
        //pan.add(jlnumber);

        //菜单
        menu.add(itemlook);
        menu.add(itemlooks);
        menu.add(itemputin);
        menu.add(itemdel);
        menu.add(itempre);
        menu.add(itemborrow);
        menu.add(itemreturn);
        menu.add(itembooker);
        menuBar.add(menu);
        jframe.setJMenuBar(menuBar);
        jframe.setVisible(true);
        //监听
        pan.add(nblook);
        pan.add(nblooks);
        pan.add(nbputin);
        pan.add(nbdel);
        pan.add(nbpre);
        pan.add(nbborrow);
        pan.add(nbreturn);
        pan.add(jlnumber);

        nblook.addActionListener(e -> {
            sirlook temp = new sirlook();
            temp.sirlook();
        });
        nblooks.addActionListener(e -> {
            sirlooks temp = new sirlooks();
            temp.sirlooks();
        });
        nbputin.addActionListener(e -> {
            sirputin temp = new sirputin();
            temp.sirput();
        });
        nbdel.addActionListener(e -> {
            sirdel temp = new sirdel();
            temp.dele();
        });
        nbpre.addActionListener(e -> {
            lookpre temp = new lookpre();
            temp.lookpres();
        });
        nbborrow.addActionListener(e -> {
            sirlookbor temp = new sirlookbor();
            temp.sirlookbors();
        });
        nbreturn.addActionListener(e -> {
            Login login = new Login();
            login.init();
            jframe.dispose();
        });

        itemlook.addActionListener(e -> {
            sirlook temp = new sirlook();
            temp.sirlook();
        });
        itemlooks.addActionListener(e -> {
            sirlooks temp = new sirlooks();
            temp.sirlooks();
        });
        itemputin.addActionListener(e -> {
            sirputin temp = new sirputin();
            temp.sirput();
        });
        itemdel.addActionListener(e -> {
            sirdel temp = new sirdel();
            temp.dele();
        });
        itempre.addActionListener(e -> {
            lookpre temp = new lookpre();
            temp.lookpres();
        });
        itemborrow.addActionListener(e -> {
            sirlookbor temp = new sirlookbor();
            temp.sirlookbors();
        });
        itembooker.addActionListener(e -> {
            sirbooker temp = new sirbooker();
            temp.sirbookers();
        });
        itemreturn.addActionListener(e -> {
            Login login = new Login();
            login.init();
            jframe.dispose();
        });
    }
}
