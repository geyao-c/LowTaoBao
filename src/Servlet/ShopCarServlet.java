package Servlet;

import Entity.Goods;
import Entity.ShopCar;
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

@WebServlet(name = "Servlet.ShopCarServlet", urlPatterns = "/shop_car")
public class ShopCarServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String userid = request.getSession().getAttribute("userid").toString();
        System.out.println("userId: " + userid);
        //根据用户ID获得该用户购物车的所有信息
        ShopCarService shopCarService = new ShopCarService();
        List<ShopCar> goods = new ArrayList<>();
        goods = shopCarService.queryAllGoods(userid);

        System.out.println("goods: " + goods);
        // 生成购物车
        String json = new String();
        json += "{\"items\":[";
        for (int i = 0; i < goods.size(); ++i) {
            json += "{\"GoodId\":\"" + goods.get(i).getGoodId() + "\",\""
                    + "UserId\":\"" + goods.get(i).getUserId() + "\",\""
                    + "Number\":\"" + goods.get(i).getNume() + "\",\""
                    + "Price\":\"" + goods.get(i).getPrice() + "\""
                    +"}";
            if (i != goods.size() - 1) json += ",";
        }
        json += "], \"error\": 1}";
        System.out.print(json);

        out.println(json);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
