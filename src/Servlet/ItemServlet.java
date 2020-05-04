package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import Entity.Goods;
import Service.GoodsService;

@WebServlet(name = "Servlet.ItemServlet", urlPatterns = "/items")
public class ItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //获得全部商品
        GoodsService goodsService = new GoodsService();
        List<Goods> goods = new ArrayList<>();
        goods = goodsService.queryAllGoods();
        System.out.println("goods:" + goods);
//        request.setAttribute("goods",goods);

        String json = new String();
        json += "{\"items\":[";
        for (int i = 0; i < goods.size(); ++i) {
            json += "{\"GoodId\":\"" + goods.get(i).getGoodId() + "\",\""
                    + "GoodName\":\"" + goods.get(i).getGoodName() + "\",\""
                    + "Number\":\"" + goods.get(i).getNumber() + "\",\""
                    + "Category\":\"" + goods.get(i).getCategory() + "\",\""
                    + "Price\":\"" + goods.get(i).getPrice() + "\""
                    +"}";
            if (i != goods.size() - 1) json += ",";
        }
        json += "], \"error\": 1}";
//            String json = "{'username':'hhh', 'name':'name', 'age': 12, 'telephone': '123'}";
        System.out.print(json);

        out.println(json);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
