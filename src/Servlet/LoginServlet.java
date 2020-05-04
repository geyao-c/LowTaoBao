package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import Entity.Users;
import Service.UsersService;

@WebServlet(name = "Servlet.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获取前端提交的数据
        System.out.println("start to login...");
        String userid = request.getParameter("UserId");
        String username = request.getParameter("UserName");
        String pass = request.getParameter("Pass");
        HttpSession session = request.getSession();

        UsersService usersService = new UsersService();
        Users user = usersService.queryPersonByUserId(userid);

        System.out.println("userid = " + userid);
        System.out.println("username = " + username);
        System.out.println("pass = " + pass);

        JSONObject object = new JSONObject();

        if(user!=null&&(pass.equals(user.getPass()))) {//如果该用户存在且密码正确
            //记录userid
            session.setAttribute("userid",userid);
            request.setAttribute("error", "1");
            System.out.println("set request .");

            object.put("error", "1");
            out.println(object.toString());
            //进行跳转
//            request.getRequestDispatcher("ItemServlet").forward(request, response);
        } else {
            System.out.println("error!");
            request.setAttribute("error", "0");

            object.put("error", "0");
            out.println(object.toString());
        }

        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
