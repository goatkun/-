import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class bookreturn {
    public void bookreturns(String name){
        JFrame f = new JFrame("还书");
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

        NewButton jbret = new NewButton("还书");//按钮
        jbret.setContentAreaFilled(false);//是否填充
        jbret.setFont(f2);//修改字体大小

        JLabel jlbookno = new JLabel("书号");//标签
        JLabel jlbookname = new JLabel("书名");

        jlbookno.setForeground(Color.white);//标签颜色
        jlbookno.setFont(f1);

        jlbookname.setForeground(Color.white);//标签颜色
        jlbookname.setFont(f1);

        JTextField jtbookno = new JTextField(10);
        JTextField jtbookname = new JTextField(10);

        //设置属性
        jbret.setBounds(170,200,80,50);//按钮
        jlbookno.setBounds(90,20,60,50);//标签
        jlbookname.setBounds(90,50,60,50);
        jtbookno.setBounds(140,40,150,20);//文本框
        jtbookname.setBounds(140,70,150,20);
        //添加
        pan.add(jbret);//按钮
        pan.add(jlbookno);//标签
        pan.add(jlbookname);
        pan.add(jtbookno);//文本框
        pan.add(jtbookname);

        //监听

        jbret.addActionListener(e -> {
                    String sbookno = jtbookno.getText();
                    String sbookname = jtbookname.getText();
                    if (sbookno.length() == 0 || sbookname.length() == 0) {
                        JOptionPane.showMessageDialog(f, "输入的信息不完整", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sql = "select bookno from bookborrow where bookno = " + "'"+ sbookno + "'"+ "and bookerno =" + "'"+ name + "'";//判断是否借了此书
                        int flag = -1;
                        try {
                            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next()) {
                                String s = rs.getString(1);
                                flag = 1;
                            }
                                System.out.println(flag);
                        } catch (SQLException t) {
                            t.printStackTrace();
                            JOptionPane.showMessageDialog(f, "连接错误", "提示", JOptionPane.ERROR_MESSAGE);
                        }

                        if (flag != -1) {
                            String sqldel = "delete from bookborrow where bookno = "+"'"+sbookno+"'"+" and bookerno = " + "'"+name+"'";
                            try {
                                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                                Statement stmt = conn.createStatement();
                                if(stmt.executeUpdate(sqldel) == 0){
                                    JOptionPane.showMessageDialog(f, "错误(没有此书）。", "提示",JOptionPane.ERROR_MESSAGE);
                                }
//                                else{
//                                    JOptionPane.showMessageDialog(f, "已删除。", "提示",JOptionPane.INFORMATION_MESSAGE);
//                                }
                            } catch (SQLException t) {
                                t.printStackTrace();
                                JOptionPane.showMessageDialog(f, "错误。", "提示",JOptionPane.ERROR_MESSAGE);
                            }
                            PreparedStatement statement = null;
                            String sqlll = "update books set booknum = ? where bookno = ?";//还回库存
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                                statement = connection.prepareStatement(sqlll);
                                statement.setString(1, Integer.toString(flag + 1));
                                statement.setString(2, sbookno);
                                if (statement.executeUpdate() == 1) {
                                    JOptionPane.showMessageDialog(f, "已成功还书", "提示", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (SQLException t) {
                                t.printStackTrace();
                                JOptionPane.showMessageDialog(f, "连接错误.", "提示", JOptionPane.ERROR_MESSAGE);
                            }
                            flag = -1;
                        } else {
                            JOptionPane.showMessageDialog(f, "没有借此书", "提示", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }
}
