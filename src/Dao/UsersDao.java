package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entity.Users;
import Util.DBUtil;

public class UsersDao extends Object{
    //根据userid判断是否存在
    public boolean isExsit(String parauserid) {
        return queryUserByUserId(parauserid)==null?false:true;
    }
    //增加person
    public boolean addUser(Users user) {
        String sql = "insert into Users values(?,?,?)";
        Object[] params= {user.getUserId(),user.getUserName(),user.getPass()};
        return DBUtil.executeUpdate(sql, params);
    }
    //根据用户ID查找
    public Users queryUserByUserId(String parauserid) {
        ResultSet rs = null;
        Users user = null;
        try {
            String sql = "select * from Users where UserId=?";
            Object[] params={parauserid};
            rs = DBUtil.executeQuery(sql, params);
            if(rs.next()) {
                String UserId = rs.getString("UserId");
                String UserName = rs.getString("UserName");
                String Pass = rs.getString("Pass");
                user = new Users(UserId,UserName,Pass);
            }
            return user;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.closeAll(rs,null,DBUtil.connection);
        }
    }
}
