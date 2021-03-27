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
import java.util.logging.Level;
import java.util.logging.Logger;

public class KetNoiSQL {
        private Connection con = null;

    public KetNoiSQL() {
        String url = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String dbUrl = "jdbc:jtds:sqlserver://localhost:1433/KNDTB";
            con = DriverManager.getConnection(dbUrl,"sa","sa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet GetResultSet(String tableName) throws SQLException {
        ResultSet rs = null;
        Statement stmt = con.createStatement();
        String sql = "select * from " + tableName;
        rs = stmt.executeQuery(sql);
        return rs;
    }

    public void Close() throws Exception {
        con.close();
    }

    public static void main(String[] args) {
      KetNoiSQL kn = new KetNoiSQL();
        try {
            ResultSet rs= kn.GetResultSet("Account");//Table Name
            while(rs.next())
            {
                System.out.println(rs.getString("accName"));//Field Name
            }
            kn.Close();
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
