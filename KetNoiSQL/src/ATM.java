
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATM {

    static Connect kn = new Connect();

    public static String kiemTraName(String accName) {
        Connection con = kn.getConnecDB();
        String sql = "select * from customer where accName = ?";
        String s = "";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, accName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("accName"));
               // System.out.println("co ton tai");
                s = "ton tai";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static String kiemTraPass(String pass) {
        Connection con = kn.getConnecDB();
        String sql = "select * from customer where pass = ?";
        String s = "";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, pass);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                s = "dung mat khau";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public static void select(String accName) {
        Connection con = kn.getConnecDB();
        String sql ="select * from customer where accName = ?  ";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, accName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println(" ---- TEN TAI KHOAN  : " + rs.getString("accName"));
                System.out.println(" ---- MAT KHAU : " + rs.getString("pass"));
                System.out.println(" ---- TIEN CON LAI  : " + rs.getInt("balance"));
                System.out.println(" ---- SO CAN CUOC     : " + rs.getString("IdNo"));
                System.out.println(" ---- SO TAI KHOAN    : " + rs.getString("accNo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int selectMoney(String accName) {
        Connection con = kn.getConnecDB();
        String sql = "select * from customer where accName = ?";
        int tien = 0;
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, accName);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                tien = (rs.getInt("balance"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tien;
    }

    public static void updateMoney(String accName, int money) {
        Connection con = kn.getConnecDB();
        int m = selectMoney(accName);
        m = m - money;
        String sql = "update customer set balance = ? where accName = ?";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setInt(1, m);
            pstm.setString(2, accName);
            int rs = pstm.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ATM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Connection cn = kn.getConnecDB();
        Statement stm = null;
        ResultSet rs = null;
        String accName, pass;
        int money;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap Ten tai khoan : ");
        accName = sc.nextLine();
        System.out.print("Nhap mat khau : ");
        pass = sc.nextLine();
        if (kiemTraName(accName) != "") {
            System.out.println(kiemTraName(accName));
            if (kiemTraPass(pass) != "") {
                System.out.println(kiemTraPass(pass));
                System.out.println("thong tin tai khoan: ");
                select(accName);
            } else {
                System.out.println("mat khau khong dung . ");
            }
        } else {
            System.out.println("khong ton tai");
        }
        System.out.print("NHAP SO TIEN :  ");
        money = sc.nextInt();
        updateMoney(accName, money);
        System.out.println("SO DU CON LAI : " + selectMoney(accName));

    }
}
