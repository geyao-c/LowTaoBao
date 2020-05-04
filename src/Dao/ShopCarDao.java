package Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.Goods;
import Entity.ShopCar;
import Util.DBUtil;
import Dao.GoodsDao;

public class ShopCarDao extends Object{
    //根据用户ID和商品ID,更新该用户的购物车
    GoodsDao goodsDao = new GoodsDao();
    public boolean updateUserIdAndGoodId(String userid,String goodid){
        if(goodIsIn(userid,goodid)){//如果购物车中已经存在这一项
//            System.out.println("存在这一项");
            String sql = "update ShopCar set Nume=Nume+1 where UserId=? and GoodId=?";
            Object[] params = {userid,goodid};
            return DBUtil.executeUpdate(sql, params);
        }else {//不存在则要新插入一项
//            System.out.println("不存在这一项");
            Goods good = new Goods();
            good = goodsDao.getGoodById(goodid);
            String sql = "insert into ShopCar values(?,?,?,?)";
            Object[] params = {userid,goodid,1,good.getPrice()};
            return DBUtil.executeUpdate(sql, params);
        }
    }
    //查询该用户的购物车是否已经存在该物品
    public boolean goodIsIn(String userid,String goodid){
        ResultSet rs = null;
        try {
            String sql = "select * from ShopCar where UserId=? and GoodId=?";
            Object[] params= {userid,goodid};
            rs = DBUtil.executeQuery(sql, params);
            System.out.print(rs.getFetchSize());
            if(rs.next()) {
                return true;
            }else return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            DBUtil.closeAll(rs,null,DBUtil.connection);
        }
    }

    //根据用户ID查看某个用户购物车的所有信息
    public List<ShopCar>queryAllGoods(String userid) {
        ResultSet rs = null;
        ShopCar shopCar = null;
        List<ShopCar>shopCars = new ArrayList<>();
        try {
            String sql = "select * from ShopCar where UserId = ?";
            Object[] params= {userid};
            rs = DBUtil.executeQuery(sql, params);
            while(rs.next()) {
                String goodid = rs.getString("GoodId");
                int nume = rs.getInt("Nume");
                float price = rs.getFloat("Price");
                shopCar = new ShopCar(userid,goodid,nume,price);
                shopCars.add(shopCar);
            }return shopCars;
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
