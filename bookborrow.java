import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class bookborrow {
    public void bookborrows(String name){
        JFrame f = new JFrame("借书");
        f.setBounds(300,200,400,400);
        f.setVisible(true);
        JPanel panel = new JPanel();
        Container con = f.getContentPane();//容器
        panel.setLayout(null);
        con.add(panel);

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
//声明
        Font f1=new Font("微软雅黑",Font.BOLD,15);//定义标签字体样式

        Font f2=new Font("微软雅黑",Font.BOLD,13);//定义按钮字体样式

        NewButton jbpre = new NewButton("已预约");//按钮
        jbpre.setContentAreaFilled(false);//是否填充
        jbpre.setFont(f2);//修改字体大小

        NewButton jbborrow = new NewButton("借书");
        jbborrow.setContentAreaFilled(false);//是否填充
        jbborrow.setFont(f2);//修改字体大小

        JLabel jlbookno = new JLabel("书号");//标签
        JLabel jlbookname = new JLabel("书名");

        jlbookno.setForeground(Color.white);//标签颜色
        jlbookno.setFont(f1);

        jlbookname.setForeground(Color.white);//标签颜色
        jlbookname.setFont(f1);

        JTextField jtbookno = new JTextField(10);
        JTextField jtbookname = new JTextField(10);

        //设置属性
        jbpre.setBounds(80,160,80,50);//按钮
        jbborrow.setBounds(230,160,80,50);
        jlbookno.setBounds(85,25,60,50);//标签
        jlbookname.setBounds(85,55,60,50);
        jtbookno.setBounds(130,40,150,20);//文本框
        jtbookname.setBounds(130,70,150,20);
        //添加
        pan.add(jbpre);//按钮
        pan.add(jbborrow);
        pan.add(jlbookno);//标签
        pan.add(jlbookname);
        pan.add(jtbookno);
        pan.add(jtbookname);
        //监听
        jbpre.addActionListener(e -> {
                    String sqlsele = "select bookno,bookerno,bookname from bookpre where bookerno = "+"'"+name+"'";//预约加入书表；
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                        Statement stmt = conn.createStatement();
                        Statement statement = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(sqlsele);
                        while (rs.next())
                        {
                            String s1 = rs.getString(1);
                            String s2 = rs.getString(2);
                            String s3 = rs.getString(3);
                            String in = "insert into bookborrow(bookno,bookerno,bookername) values (?,?,?)";

                            PreparedStatement st = null;
                            st = conn.prepareStatement(in);
                            st.setString(1, s1);
                            st.setString(2, s2);
                            st.setString(3, s3);
                            System.out.println(s1);
                            System.out.println(s2);
                            System.out.println(s3);
                            System.out.println(in);
                            st.executeUpdate();
                        }
                    } catch (SQLException t) {
                        t.printStackTrace();
                        JOptionPane.showMessageDialog(f, "连接错误1", "提示",JOptionPane.ERROR_MESSAGE);
                    }

                    String sql = "delete  from bookpre where bookerno = "+"'"+name+"'";//判断是否有预约；
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                        Statement stmt = conn.createStatement();
                        if(stmt.executeUpdate(sql) != 0){
                            JOptionPane.showMessageDialog(f, "已成功取书", "提示",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(f, "没有预约", "提示",JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException t) {
                        t.printStackTrace();
                        JOptionPane.showMessageDialog(f, "连接错误2", "提示",JOptionPane.ERROR_MESSAGE);
                    }

                }
        );
        jbborrow.addActionListener(e -> {
                    String sbookno = jtbookno.getText();
                    String sbookname = jtbookname.getText();
                    if (sbookno.length() == 0 || sbookname.length() == 0) {
                        JOptionPane.showMessageDialog(f, "输入的信息不完整", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sql = "select booknum from books where bookno = " + "'" +sbookno+ "'";//判断是否有此书，以及是否有库
                        int flag = -1;
                        try {
                            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                String s = rs.getString(1);
                                flag = Integer.valueOf(s).intValue();
                            }
                            //    System.out.println(flag);
                        } catch (SQLException t) {
                            t.printStackTrace();
                            JOptionPane.showMessageDialog(f, "连接错误", "提示", JOptionPane.ERROR_MESSAGE);
                        }
                        if (flag >= 1) {
                            PreparedStatement statement = null;
                            String sqll = "insert into bookborrow(bookno,bookerno,bookername) values (?,?,?)";//加入借书表
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                                statement = connection.prepareStatement(sqll);
                                statement.setString(1, sbookno);
                                statement.setString(2, name);
                                statement.setString(3, sbookname);
                                if (statement.executeUpdate() == 1) {
                                    JOptionPane.showMessageDialog(f, "已成功加入借书表", "提示", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (SQLException t) {
                                t.printStackTrace();
                                JOptionPane.showMessageDialog(f, "错误.", "提示", JOptionPane.ERROR_MESSAGE);
                            }
                            String sqlll = "update books set booknum = ? where bookno = ?";//库存减少
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                                statement = connection.prepareStatement(sqlll);
                                statement.setString(1, Integer.toString(flag - 1));
                                statement.setString(2, sbookno);
                                if (statement.executeUpdate() == 1) {
                                    JOptionPane.showMessageDialog(f, "已成功借书", "提示", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (SQLException t) {
                                t.printStackTrace();
                                JOptionPane.showMessageDialog(f, "连接错误.", "提示", JOptionPane.ERROR_MESSAGE);
                            }
                            flag = -1;
                        } else {
                            JOptionPane.showMessageDialog(f, "此书已借完或没有此书", "提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }
}
