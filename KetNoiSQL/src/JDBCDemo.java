/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThienPC
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
public class JDBCDemo {

    private Connection con = null;

    public JDBCDemo() {
        String url = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String db = "jdbc:jtds:sqlserver://localhost:1433/KNDTB";
            con = DriverManager.getConnection(db,"sa","sa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getResultSet() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Account");
        return rs;
    }
    
    public void Close() throws Exception {
        con.close();
    }

    public static void main(String[] args) {
        Vector vData = null, vTitle = null;
        try        {
            JDBCDemo kn = new JDBCDemo();
            ResultSet rs = kn.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            vTitle = new Vector(numberOfColumns, 0);
            for (int j = 1; j <= numberOfColumns; j++) {
                vTitle.add(rsmd.getColumnLabel(j));
            }
            vData = new Vector(10, 10);
            while (rs.next()) {
                Vector row = new Vector(numberOfColumns, 0);
                for (int i = 1; i <= numberOfColumns; i++) {
                    row.add(rs.getObject(i));
                }
                vData.add(row);
            }
            rs.close();
            kn.Close();
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        JScrollPane tableResult = new JScrollPane(new JTable(vData, vTitle));
        JFrame f = new JFrame();
        f.setSize(600, 480);
        f.setContentPane(tableResult);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.show();
    }
}
