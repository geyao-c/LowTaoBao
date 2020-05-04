package Servlet;

import Entity.ShopCar;
import Service.ShopCarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShopCarServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        String userid = request.getSession().getAttribute("userid").toString();
        //根据用户ID获得该用户购物车的所有信息
        ShopCarService shopCarService = new ShopCarService();
        List<ShopCar> shopCars = new ArrayList<>();
        shopCars = shopCarService.queryAllGoods(userid);
        request.setAttribute("shopcar",shopCars);

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
