package Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entity.Goods;
import Util.DBUtil;

public class GoodsDao extends Object{
    //根据商品ID修改商品数量,添加进购物车一个则该商品数量减一
    public boolean updateGoodsByGoodId(String goodid) {
        Goods good = getGoodById(goodid);
        if(good==null){
            return false;
        }else{
            //首先得到该商品的数量,若大于零说明能够继续卖出
            if(good.getNumber()>0) {
                String sql = "update Goods set  Nume=Nume-1 where GoodId=?";
                Object[] params = {goodid};
                return DBUtil.executeUpdate(sql, params);
            }else{
                return false;
            }
        }
    }

    //根据商品ID获得该商品的所有属性
    public Goods getGoodById(String goodid){
        ResultSet rs = null;
        Goods good = new Goods();
        int nume=0;
        try {
            String sql = "select * from Goods where GoodId=?";
            Object[] params= {goodid};
            rs = DBUtil.executeQuery(sql, params);
            if(rs.next()) {
                good.setGoodId(goodid);
                good.setGoodName(rs.getString("GoodName"));
                good.setNumber(rs.getInt("Nume"));
                good.setCategory(rs.getString("Category"));
                good.setPrice(rs.getFloat("Price"));
            }
            return good;
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



    //获取全部商品
    public List<Goods>queryAllGoods() {
        ResultSet rs = null;
        Goods good;
        List<Goods>goods = new ArrayList<>();
        try {
            String sql = "select * from Goods";
            rs = DBUtil.executeQuery(sql, null);
            while(rs.next()) {
                String goodid = rs.getString("GoodId");
                String goodname = rs.getString("GoodName");
                int nume = rs.getInt("Nume");
                float price = rs.getFloat("Price");
                String category = rs.getString("Category");
                good = new Goods(goodid,goodname,nume,price,category);
                goods.add(good);
            }return goods;
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
