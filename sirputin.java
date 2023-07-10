import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class sirputin {
    public void sirput(){
        JFrame f = new JFrame("增加书籍信息");
        f.setBounds(300,200,400,400);
        f.setVisible(true);
        JPanel panel = new JPanel();
        Container con = f.getContentPane();//容器
        panel.setLayout(null);
        con.add(panel);

        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg=new ImageIcon("D:\\桌面\\作业\\数据库课设\\src\\main\\java\\tx.png");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //2.把窗口面板设为内容面板并设为透明、流动布局。
        JPanel pan=(JPanel)f.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        //3.之后把组件和面板添加到窗口面板；
        pan.setLayout(null);//设计按钮布局为空

        Font f1=new Font("微软雅黑",Font.BOLD,15);//定义标签字体样式

        Font f2=new Font("微软雅黑",Font.BOLD,13);//定义按钮字体样式

        NewButton jbenter = new NewButton("录入");//按钮
        jbenter.setContentAreaFilled(false);//是否填充
        jbenter.setFont(f2);//修改字体大小

        JLabel jlbookno = new JLabel("书号");//标签
        JLabel jlbookname = new JLabel("书名");
        JLabel jlbookwriter = new JLabel("作者");
        JLabel jlbookedition = new JLabel("出版社");
        JLabel jlbooksum = new JLabel("总数");


        JTextField jtbookno = new JTextField(10);
        JTextField jtbookname = new JTextField(10);
        JTextField jtbookwriter = new JTextField(10);
        JTextField jtbookedition = new JTextField(10);
        JTextField jtbooksum = new JTextField(10);

        jlbookno.setForeground(Color.white);//标签颜色
        jlbookno.setFont(f1);

        jlbookname.setForeground(Color.white);//标签颜色
        jlbookname.setFont(f1);

        jlbookwriter.setForeground(Color.white);//标签颜色
        jlbookwriter.setFont(f1);

        jlbookedition.setForeground(Color.white);//标签颜色
        jlbookedition.setFont(f1);

        jlbooksum.setForeground(Color.white);//标签颜色
        jlbooksum.setFont(f1);
        //设置属性
        jbenter.setBounds(170,200,60,50);//按钮
        jlbookno.setBounds(90,25,60,50);//标签
        jlbookname.setBounds(90,55,60,50);
        jlbookwriter.setBounds(90,85,60,50);
        jlbookedition.setBounds(90,115,60,50);
        jlbooksum.setBounds(90,145,60,50);

        jtbookno.setBounds(140,40,150,20);//文本框
        jtbookname.setBounds(140,70,150,20);
        jtbookwriter.setBounds(140,100,150,20);
        jtbookedition.setBounds(140,130,150,20);
        jtbooksum.setBounds(140,160,150,20);
        //添加
        pan.add(jbenter);//按钮
        pan.add(jlbookno);//标签
        pan.add(jlbookname);
        pan.add(jlbookwriter);
        pan.add(jlbookedition);
        pan.add(jlbooksum);
        pan.add(jtbookno);//文本框
        pan.add(jtbookname);
        pan.add(jtbookwriter);
        pan.add(jtbookedition);
        pan.add(jtbooksum);


        //监听
        jbenter.addActionListener(e -> {
            String sbookno = jtbookno.getText();
            String sbookname = jtbookname.getText();
            String sbookwriter = jtbookwriter.getText();
            String sbookedition = jtbookedition.getText();
            String sbooksum = jtbooksum.getText();

            if(sbookno.length() == 0 || sbookname.length() == 0 || sbookwriter.length() == 0 || sbookedition.length() == 0 || sbooksum.length() == 0){
                JOptionPane.showMessageDialog(f, "输入的信息不完整", "提示",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql = "insert into books(bookno,bookname,bookwriter,bookedition,booksum,booknum) values (?,?,?,?,?,?)";
                PreparedStatement statement = null;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, sbookno);
                    statement.setString(2, sbookname);
                    statement.setString(3, sbookwriter);
                    statement.setString(4, sbookedition);
                    statement.setString(5, sbooksum);
                    statement.setString(6, sbooksum);
                    if(statement.executeUpdate() == 1){
                        JOptionPane.showMessageDialog(f, "已加入", "提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                    //System.out.println("插入成功");
                } catch (SQLException t) {
                    t.printStackTrace();
                    JOptionPane.showMessageDialog(f, "错误（书号有错误或者输入总数不是整数）", "提示",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

    }
}
