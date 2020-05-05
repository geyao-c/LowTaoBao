package Servlet;

import Entity.Goods;
import Entity.ShopCar;
import Service.GoodsService;
import Service.ShopCarService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet.AddCarServlet", urlPatterns = "/add_car")
public class AddCarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String userid = request.getSession().getAttribute("userid").toString();

        System.out.println("before add car");

        //获得前端传送来的所有商品ID
        String goodsid = request.getParameter("items");
        ShopCarService shopCarService = new ShopCarService();
        GoodsService goodsService = new GoodsService();

        System.out.println("iter the items");
        System.out.println(goodsid);
        String[] goodIds = goodsid.split(",");
        System.out.println("goodIds: " + goodIds);

        // TODO: 添加错误, 无法解析列表

        JSONObject object = new JSONObject();
        boolean flag1 = false, flag2 = true;

        for (String goodid : goodIds){
            System.out.println("GoodId: " + goodid);
            //商品库存量减少
            if(goodsService.updateGoodsByGoodId(goodid)){
                //用户购物车商品数增加
                shopCarService.updateUserIdAndGoodId(userid,goodid);
                flag1 = true;
            } else {
                flag2 = false;
//                out.println("库存量不足");
            }
        }
        if (flag1 && flag2) {
            object.put("error", "1");
        } else {
            object.put("error", "0");
        }
        out.println(object.toString());

        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post");
        doGet(request,response);
    }
}
