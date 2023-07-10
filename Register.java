import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {

    private JFrame jf = new JFrame("注册");
    private JPanel jp = new JPanel();
    private ImageIcon image = new ImageIcon();
    public void registers(){
        jf.setResizable(false);//框架
        jf.setBounds(300,200,400,300);
        Container con = jf.getContentPane();//容器
        jp.setLayout(null);
        con.add(jp);

        //声明
        JButton jbresiter = new JButton("注册");//按钮
        JButton jbcs = new JButton("取消");
        JButton jbre = new JButton("返回");

        JLabel jlnumber = new JLabel("用户名:");//标签
        JLabel jlpass = new JLabel("密   码:");
        JLabel jlname = new JLabel("姓名");
        JLabel jl = new JLabel(image);


        JTextField jtnumber = new JTextField(10);
        JTextField jtpass = new JTextField(10);
        JTextField jtname = new JTextField(10);
        //设置属性
        jbresiter.setBounds(110,150,60,40);//按钮
        jbcs.setBounds(180,150,60,40);
        jbre.setBounds(250,150,60,40);
        jlnumber.setBounds(98,20,60,40);//标签
        jlpass.setBounds(98,50,60,40);
        jlname.setBounds(98,80,60,40);
        jtnumber.setBounds(140,30,150,20);//文本框
        jtpass.setBounds(140,60,150,20);
        jtname.setBounds(140,90,150,20);
        //添加
        jp.add(jbresiter);//按钮
        jp.add(jbcs);
        jp.add(jbre);
        jp.add(jlnumber);//标签
        jp.add(jlpass);
        jp.add(jlname);
        jp.add(jtnumber);//文本框
        jp.add(jtpass);
        jp.add(jtname);

        jf.setVisible(true);
        //监听
        jbresiter.addActionListener(e -> {
            String snumber = jtnumber.getText();
            String spass = jtpass.getText();
            String sname = jtname.getText();
            if(sname.length() == 0 || spass.length() == 0 || sname.length() == 0){
                JOptionPane.showMessageDialog(jf, "输入的信息不完整", "提示",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql = "insert into bookers(booknumber,bookpass,bookername) values (?,?,?)";
                PreparedStatement statement = null;
                try {
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, snumber);
                    statement.setString(2, spass);
                    statement.setString(3, sname);
                    if(statement.executeUpdate() == 1){
                        JOptionPane.showMessageDialog(jf, "已注册", "提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                    System.out.println("插入成功");
                } catch (SQLException t) {
                    t.printStackTrace();
                    JOptionPane.showMessageDialog(jf, "错误（账户名已存在）。", "提示",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jbcs.addActionListener(e -> {
            jf.dispose();
        });
        jbre.addActionListener(e -> {
            Login login = new Login();
            login.init();
            jf.dispose();
        });
    }
}
