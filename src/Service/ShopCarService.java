package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.ShopCarDao;
import Entity.Goods;
import Entity.ShopCar;
import Entity.Users;
import Util.DBUtil;

public class ShopCarService{

    //根据用户ID和商品ID,更新该用户的购物车
    ShopCarDao shopCarDao = new ShopCarDao();
    public boolean updateUserIdAndGoodId(String userid,String goodid) {
        return shopCarDao.updateUserIdAndGoodId(userid,goodid);
    }
    //根据用户ID查看某个用户购物车的所有信息
    public List<ShopCar>queryAllGoods(String userid) {
        return shopCarDao.queryAllGoods(userid);
    }
}
