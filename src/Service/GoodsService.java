package Service;

import Dao.GoodsDao;
import Entity.Goods;
import java.util.List;

public class GoodsService {
    GoodsDao goodsDao = new GoodsDao();
    //根据商品ID修改商品数量,添加进购物车一个则该商品数量减一
    public boolean updateGoodsByGoodId(String goodid) {
        return goodsDao.updateGoodsByGoodId(goodid);
    }
    //获取全部商品
    public List<Goods> queryAllGoods() {
        return goodsDao.queryAllGoods();
    }
}
