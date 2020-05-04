package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entity.Users;
import Service.UsersService;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获取前端提交的数据
        String userid = request.getParameter("UserId");
        String username = request.getParameter("UserName");
        String pass = request.getParameter("Pass");
        HttpSession session = request.getSession();

        UsersService usersService = new UsersService();
        Users user = usersService.queryPersonByUserId(userid);

        if(user!=null&&(pass.equals(user.getPass()))) {//如果该用户存在且密码正确
            //记录userid
            session.setAttribute("userid",userid);
            request.setAttribute("error", "1");
            //进行跳转
//            request.getRequestDispatcher("ItemServlet").forward(request, response);
        }else {
            request.setAttribute("error", "0");
        }
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
