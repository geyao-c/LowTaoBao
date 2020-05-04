package Servlet;

import Entity.Goods;
import Entity.ShopCar;
import Service.GoodsService;
import Service.ShopCarService;

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
        //获得前端传送来的所有商品ID
        String []goodsid = request.getParameterValues("items");
        ShopCarService shopCarService = new ShopCarService();
        GoodsService goodsService = new GoodsService();

        System.out.println(goodsid);

        for(String goodid : goodsid){
            //商品库存量减少
            if(goodsService.updateGoodsByGoodId(goodid)){
                //用户购物车商品数增加
                shopCarService.updateUserIdAndGoodId(userid,goodid);
            }else{
                out.println("库存量不足");
            }
        }
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
