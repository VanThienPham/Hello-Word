/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ThienPC
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

public class Connect {
    Connection con = null;
    public Connection getConnecDB(){
        try {
            String dbUrl = "jdbc:jtds:sqlserver://DESKTOP-U7F6278:1433/BANK";
            con = DriverManager.getConnection(dbUrl,"sa","sa");
            //System.out.println("thành công");
        } catch (Exception e) {
            //System.out.println("không kết nối được");
        }
        return con;
    }
}
