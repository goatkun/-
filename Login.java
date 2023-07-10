import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Login {
    private JFrame f = new JFrame("登录");
    private JPanel panel = new JPanel();
    public void init() {
        f.setResizable(false);//框架
        f.setBounds(500, 350, 400, 300);

        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg=new ImageIcon("tx.png");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)f.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        //3.之后把组件和面板添加到窗口面板；
        pan.setLayout(null);

        Container con = f.getContentPane();//容器

        con.add(panel);

//声明
        Font f1=new Font("微软雅黑",Font.BOLD,15);//定义标签字体样式

        Font f2=new Font("微软雅黑",Font.BOLD,13);//定义按钮字体样式

        NewButton jblogin = new NewButton("登录");//按钮
        jblogin.setContentAreaFilled(false);//是否填充
        jblogin.setFont(f2);//修改字体大小

        NewButton jbregister = new NewButton("注册");
        jbregister.setContentAreaFilled(false);//是否填充
        jbregister.setFont(f2);//修改字体大小

        JLabel jllogin = new JLabel("用户名:");//标签
        jllogin.setForeground(Color.white);//标签颜色
        jllogin.setFont(f1);

        JLabel jlregister = new JLabel("密 码:");
        jlregister.setForeground(Color.white);//标签颜色
        jlregister.setFont(f1);

        JTextField jtname = new JTextField(10);
        JTextField jtpassword = new JTextField(10);
//设置属性
        jblogin.setBounds(100, 160, 60, 50);//按钮
        jbregister.setBounds(250, 160, 60, 50);
        jllogin.setBounds(80, 25, 60, 50);//标签
        jlregister.setBounds(90, 55, 60, 50);
        jtname.setBounds(140, 41, 150, 20);//文本框
        jtpassword.setBounds(140, 71, 150, 20);

//添加
        pan.add(jblogin);//按钮
        pan.add(jbregister);
        pan.add(jllogin);//标签
        pan.add(jlregister);
        pan.add(jtname);//文本框
        pan.add(jtpassword);

        f.setVisible(true);
        //监听
        jblogin.addActionListener(e -> {
            String sname = jtname.getText();
            String spassword = jtpassword.getText();
            String sql = "select bookpass from bookers where booknumber = '" + sname+ "'";//判断能不能登陆
            int flag = -1;
            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                //获得数据库连接
                Statement stmt = conn.createStatement(); //访问数据库
                ResultSet rs = stmt.executeQuery(sql);//执行SQL语句
                while (rs.next())
                {
                    String s1 = rs.getString(1);
                    if(s1.equals(spassword)){
                        flag = 1;
                    }
                }
            } catch (SQLException t) {
                t.printStackTrace();
                JOptionPane.showMessageDialog(f, "连接错误", "提示",JOptionPane.ERROR_MESSAGE);
            }
            if(flag == 1){
                if (sname.equals("lhk")){
                    f.setVisible(false);
                    Index index = new Index();
                    index.init();
                }else if (sname.equals("legend")) {
                    f.setVisible(false);
                    Index index = new Index();
                    index.init();
                }else if (sname.equals("zyx")) {
                    f.setVisible(false);
                    Index index = new Index();
                    index.init();
                }else{
                    f.setVisible(false);
                    bookuser user = new bookuser();
                    user.user(sname);
                }
            }
            else{
                JOptionPane.showMessageDialog(f, " 账号或密码错误", "提示",JOptionPane.ERROR_MESSAGE);
            }
        });
        jbregister.addActionListener(e -> {
            f.setVisible(false);
            Register re = new Register();
            re.registers();
        } );
    }
}

