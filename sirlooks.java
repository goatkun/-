import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class sirlooks{
    public void sirlooks(){
        JFrame f = new JFrame("多关键字查询书籍信息");
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

        NewButton jbenter = new NewButton("查询");//按钮
        jbenter.setContentAreaFilled(false);//是否填充
        jbenter.setFont(f2);//修改字体大小



        JLabel jlbookno = new JLabel("书号");//标签
        JLabel jlbookname = new JLabel("书名");
        JLabel jlbookwriter = new JLabel("作者");
        JLabel jlbookedition = new JLabel("出版社");

        jlbookno.setForeground(Color.white);//标签颜色
        jlbookno.setFont(f1);

        jlbookname.setForeground(Color.white);//标签颜色
        jlbookname.setFont(f1);

        jlbookwriter.setForeground(Color.white);//标签颜色
        jlbookwriter.setFont(f1);

        jlbookedition.setForeground(Color.white);//标签颜色
        jlbookedition.setFont(f1);

        JTextField jtbookno = new JTextField(10);
        JTextField jtbookname = new JTextField(10);
        JTextField jtbookwriter = new JTextField(10);
        JTextField jtbookedition = new JTextField(10);

        //设置属性
        jbenter.setBounds(170,200,60,50);//按钮
        jlbookno.setBounds(90,25,60,50);//标签
        jlbookname.setBounds(90,55,60,50);
        jlbookwriter.setBounds(90,85,60,50);
        jlbookedition.setBounds(90,115,60,50);

        jtbookno.setBounds(140,40,150,20);//文本框
        jtbookname.setBounds(140,70,150,20);
        jtbookwriter.setBounds(140,100,150,20);
        jtbookedition.setBounds(140,130,150,20);
        //添加
        pan.add(jbenter);//按钮
        pan.add(jlbookno);//标签
        pan.add(jlbookname);
        pan.add(jlbookwriter);
        pan.add(jlbookedition);
        pan.add(jtbookno);//文本框
        pan.add(jtbookname);
        pan.add(jtbookwriter);
        pan.add(jtbookedition);

        //监听
        jbenter.addActionListener(e -> {
                    String sbookno = jtbookno.getText();
                    String sbookname = jtbookname.getText();
                    String sbookwriter = jtbookwriter.getText();
                    String sbookedition = jtbookedition.getText();

                    if(sbookno.length() == 0 && sbookname.length() == 0 && sbookwriter.length() == 0 && sbookedition.length() == 0){
                        JOptionPane.showMessageDialog(f, "输入的信息不完整", "提示",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int flag = 0;
                        JFrame fr = new JFrame("查询书籍信息");
                        JPanel panell = new JPanel();
                        String sql = " select * from books where ";
                        if(sbookno.length() != 0){
                            if(flag == 1) sql = sql + "and bookno= '"+ sbookno + "'";
                            else{
                                sql = sql = sql + "bookno= '"+ sbookno + "'";
                                flag = 1;
                            }
                        }
                        if(sbookname.length() != 0) {
                            if(flag == 1) sql = sql + "and bookname= '"+ sbookname + "'";
                            else{
                                sql = sql = sql + "bookname= '"+ sbookname + "'";
                                flag = 1;
                            }
                        }
                        if(sbookwriter.length() != 0) {
                            if(flag == 1) sql = sql + "and bookwriter= '"+ sbookwriter + "'";
                            else{
                                sql = sql = sql + "bookwriter= '"+ sbookwriter + "'";
                                flag = 1;
                            }
                        }
                        if(sbookedition.length() != 0){
                            if(flag == 1) sql = sql + "and bookedition= '"+ sbookedition + "'";
                            else{
                                sql = sql = sql + "bookedition= '"+ sbookedition + "'";
                                flag = 1;
                            }
                        }
//                        System.out.println(sql);
                        try {
                            DefaultTableModel model = new DefaultTableModel();
                            JTable table = new JTable(model)
                            {
                                public boolean isCellEditable(int row, int column)
                                {return false;}//表格不允许被编辑
                            };
                            table.setPreferredScrollableViewportSize(new Dimension(500, 200));
                            JScrollPane s = new JScrollPane(table);
                            table.setRowHeight(40);
                            table.getTableHeader().setReorderingAllowed( false );//表头不可拖拽
                            //table.getTableHeader().setResizingAllowed(false);//列宽不可调整

                            model.addColumn("bookno");
                            model.addColumn("bookname");
                            model.addColumn("bookwriter");
                            model.addColumn("bookedition");
                            model.addColumn("booksum");
                            model.addColumn("booksnum");

                            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery(sql);
                            while (rs.next())
                            {
                                Vector vcRows = new Vector();
                                vcRows.addElement(rs.getString(1));
                                vcRows.addElement(rs.getString(2));
                                vcRows.addElement(rs.getString(3));
                                vcRows.addElement(rs.getString(4));
                                vcRows.addElement(rs.getInt(5));
                                vcRows.addElement(rs.getInt(6));
                                model.addRow(vcRows); //添加一行记录到表格模板中
                            }
                            fr.setResizable(false);//框架
                            fr.setBounds(300,200,600,400);
                            Container connn = fr.getContentPane();//容器
                            panell.add(s,BorderLayout.CENTER);
                            connn.add(panell);
                            fr.setVisible(true);
                        } catch (SQLException t) {
                            t.printStackTrace();
                            JOptionPane.showMessageDialog(fr, "连接错误", "提示",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );

    }
}
