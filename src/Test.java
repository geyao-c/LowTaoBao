import Entity.Goods;
import Entity.ShopCar;
import Entity.Users;
import Service.*;
import Util.DBUtil;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Dao.*;
public class Test {
    public static void main(String args[]){
        System.out.println("Hello World\n");
        Connection con=DBUtil.getConnetion();
        DBUtil.closeAll(null,null,con);
        UsersDao TestUser = new UsersDao();

        System.out.println("before insert");

        if(TestUser.addUser(new Users("2021","阿城","12345"))){

        }else{
            System.out.println("插入失败，该用户ID已经存在");
        }
        Users user=TestUser.queryUserByUserId("2020");
        boolean ok=TestUser.isExsit("2020");
        if(ok){
            System.out.println("已经存在");
        }
        if(user!=null){
            System.out.println(user.toString());
        }
        GoodsService goodsService=new GoodsService();
        if(goodsService.updateGoodsByGoodId("1")){
            System.out.println("更新成功\n");
        }else{
            System.out.println("更新失败\n");
        }
        List<Goods> goods = new ArrayList<>();
        goods = goodsService.queryAllGoods();
        for(Goods good : goods){
            System.out.println(good.toString());
        }
        ShopCarService shopCarService = new ShopCarService();
        if(shopCarService.updateUserIdAndGoodId("2020","2")){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失败");
        }
        List<ShopCar> shopCars = new ArrayList<>();
        shopCars=shopCarService.queryAllGoods("2020");
        for(ShopCar shopCar : shopCars){
            System.out.println(shopCar.toString());
        }
    }
}
