package Util;

import Entity.Goods;
import Entity.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    //  不使用连接池直接JDBC连接
    public static Connection getConnetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connection = DriverManager.getConnection("jdbc:mysql://localhost:33066/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "fiveplus", "fiveplus");
//            return connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "root", "imthenumber1");
        } catch (Exception e) {
            System.out.println("connection failed");
            e.printStackTrace();
        }
        return null;
    }

    // 关闭
    public static void closeAll(ResultSet rs, Statement stmt, Connection connection) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                pstmt.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    // 创建PreparedStatsement
    public static PreparedStatement creatPreparedStatement(String sql, Object params[]) throws SQLException {
        pstmt = getConnetion().prepareStatement(sql);
        try {
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    // 通用增删改
    public static boolean executeUpdate(String sql, Object[] params) {
        try {
            pstmt = creatPreparedStatement(sql, params);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeAll(null, pstmt, connection);
        }
    }

    // 通用查
    public static ResultSet executeQuery(String sql, Object[] params) throws ClassNotFoundException {
        try {
            pstmt = creatPreparedStatement(sql, params);
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
