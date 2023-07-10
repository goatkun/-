import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class lookpre {
    public void lookpres() {
        JFrame f = new JFrame("查询预约信息");
        JPanel panel = new JPanel();
        String sql = " select * from bookpre";
        try {
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }//表格不允许被编辑
            };
            table.setPreferredScrollableViewportSize(new Dimension(500, 200));
            JScrollPane s = new JScrollPane(table);
            table.setRowHeight(40);
            table.getTableHeader().setReorderingAllowed(false);//表头不可拖拽

            model.addColumn("bookno");
            model.addColumn("bookerno");
            model.addColumn("bookname");

            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=TS;", "sa", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Vector vcRows = new Vector();
                vcRows.addElement(rs.getString(1));
                vcRows.addElement(rs.getString(2));
                vcRows.addElement(rs.getString(3));
//                System.out.println(vcRows);
                model.addRow(vcRows); //添加一行记录到表格模板中
            }
            f.setResizable(false);//框架
            f.setBounds(300, 200, 600, 400);
            Container con = f.getContentPane();//容器
            panel.add(s, BorderLayout.CENTER);
            con.add(panel);
            f.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
}
