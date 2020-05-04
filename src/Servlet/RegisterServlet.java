package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import Entity.Users;
import Service.UsersService;

@WebServlet(name = "Servlet.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获取前端提交的数据
        String userid = request.getParameter("UserId");
        String username = request.getParameter("UserName");
        String pass = request.getParameter("Pass");
        Users user = new Users(userid,username,pass);
        UsersService usersService = new UsersService();

        System.out.println("userId: " + userid);

        JSONObject object = new JSONObject();

        boolean isin = usersService.isExsit(userid);
        if(isin == true) {//该用户ID已经被注册
            request.setAttribute("error", "0");
        } else {
            boolean result = usersService.addUser(user);
            if(result) {//插入成功
//                request.setAttribute("error", "1");
                //页面跳转
//                request.getRequestDispatcher("Login.jsp").forward(request, response);
                object.put("error", 1);
            }else {//插入失败
//                request.setAttribute("error", "0");
                object.put("error", 0);
            }
        }

        out.println(object.toString());
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
