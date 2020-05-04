<%--
  Created by IntelliJ IDEA.
  User: 姬小野
  Date: 2019/12/12
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<%

    String username = request.getParameter("username");
    String email = request.getParameter("email");

%>


<br><br><br><br>
<div class="container">
    <div class="jumbotron">
        <center></center><h1><%=username%> 你好啊! 欢迎登陆~</h1></center>
        <p>
            你的邮箱是 <%=email%>
        </p>
        <br><br>
        <p><a class="btn btn-primary btn-lg" role="button" href="/web_war/index.html">
            返回</a>
        </p>
    </div>
</div>


</body>
</html>
