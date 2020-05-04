<%--
  Created by IntelliJ IDEA.
  User: 姬小野
  Date: 2019/11/3
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*, java.text.*" %>
<%@ page import="myjdbc.utils.DBUtils" %>
<%@ page import="myjdbc.dao.*" %>
<%@ page import="myjdbc.tables.*"%>
<%@ page import="redis.clients.jedis.Jedis" %>
<html>
<head>
    <title>servlet处理数据</title>
    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 包含 bootstrap 样式表 -->
    <link rel="stylesheet" href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<br>
<%
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
//    Date d = new Date(session.getCreationTime());
//    session.setAttribute("username", "Kobe");
//    Jedis jedis = new Jedis("localhost");
//    if (jedis == null) {
//        System.out.println("jedis failed");
//    } else {
//        System.out.println("success");
//        jedis.ping();
//    }
//    jedis.ping();
%>

<%--session 的创建时间：<%=sdf.format(d) %><br> &lt;%&ndash;session.getCreationTime() 返回的是一个毫秒数 &ndash;%&gt;--%>
<%--&lt;%&ndash;new Date(session.getCreationTime()) Thu Mar 08 09:57:56 CST 2018 &ndash;%&gt;--%>
<%--session ID: <%=session.getId() %><br>--%>
<%--获取用户名：<%=session.getAttribute("username") %>--%>
<div class="container">
    <br><br>
    <center>
        <div class="col-sm-offset-1 col-sm-10">
            <a class="btn btn-info btn-lg" href="index.jsp">回到主页</a>&nbsp&nbsp&nbsp&nbsp
            <a class="btn btn-info btn-lg" href="users.jsp">操作users表</a>&nbsp&nbsp&nbsp&nbsp
            <a class="btn btn-info btn-lg" href="person.jsp">操作person表</a>&nbsp&nbsp&nbsp&nbsp
            <a class="btn btn-info btn-lg" href="query.jsp">查看表数据</a>
        </div>
    </center>
    <br><br><br>

    <br />
    <br />
    <div class="jumbotron" >
        <center><h2>users表格数据</h2></center>
    </div>

    <table class="table table-striped">
        <thead>
        <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
        <script>
            console.log("执行查询js");
            window.onload = init;
            function init() {
                init_table_1();
                init_table_2();
            }
            function init_table_1() {
                $.ajax({
                    url: "http://121.199.49.224:8080/web_war/query",
                    data: {
                        table_name: "users"
                    },
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        console.log("查询user成功");
                        console.log(data);
                        console.log(data.data);
                        console.log(data.users);
                        var tb = document.getElementById("table1");
                        for (var i = 0; i < data.users.length; ++i) {
                            console.log("i = " + i);
                            str = "<tr>\n" +
                                "            <th scope=\"row\">" + i + "</th>\n" +
                                "            <td>" + data.users[i].username + "</td>\n" +
                                "            <td>" + data.users[i].password + "</td>\n" +
                                "        </tr>";
                            tb.innerHTML += str;
                            console.log(str);
                        }
                    }
                })
            }
            function init_table_2() {
                $.ajax({
                    url: "http://121.199.49.224:8080/web_war/query",
                    // url: "http://121.199.49.224:8080/web_war/login",
                    data: {
                        table_name: "person"
                    },
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        console.log("查询person成功");
                        console.log(data);
                        console.log(data.data);
                        console.log(data.person);
                        var tb = document.getElementById("table2");
                        for (var i = 0; i < data.person.length; ++i) {
                            console.log("i = " + i);
                            var str = "<tr>\n" +
                                "            <th scope=\"row\">" + i + "</th>\n" +
                                "            <td>" + data.person[i].username + "</td>\n" +
                                "            <td>" + data.person[i].name + "</td>\n";
                            if (data.person[i].age == "null") {
                                str += "            <td> </td>\n";
                            } else {
                                str += "            <td>" + data.person[i].age + "</td>\n";
                            }
                            if (data.person[i].telephone == "null") {
                                str += "            <td> </td>\n";
                            } else {
                                str += "            <td>" + data.person[i].telephone + "</td>\n";
                            }
                            str +=    "        </tr>";
                            tb.innerHTML += str;
                            console.log(str);
                        }
                    }
                })
            }
        </script>
        <tr>
            <th scope="col">#</th>
            <th scope="col">username</th>
            <th scope="col">password</th>
        </tr>
        </thead>
        <tbody id="table1">
        </tbody>
    </table>



    <br><br><br><br>
    <%--    表格删除部分   --%>
    <div class="jumbotron">
        <center><h2>Person表格数据</h2></center>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">username</th>
            <th scope="col">name</th>
            <th scope="col">age</th>
            <th scope="col">telephone</th>
        </tr>
        </thead>
        <tbody id="table2">
        </tbody>
    </table>

    <br><br><br><br>

</div>

<script>
    if (screen && screen.width > 480) {
        document.write('<script type="text/javascript" color="102,185,255" opacity="100" zIndex="-2" count="299" src="//static.ffis.me/javascript/canvas-nest.min.js"><\/script>');
    }
</script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
    /* 鼠标点击特效 - 7Core.CN */
    var a_idx = 0;jQuery(document).ready(function($) {$("body").click(function(e) {var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正" ,"法治", "爱国", "敬业", "诚信", "友善");var $i = $("<span/>").text(a[a_idx]);
        a_idx = (a_idx + 1) % a.length;var x = e.pageX,y = e.pageY;$i.css({"z-index": 100000000,"top": y - 20,"left": x,"position": "absolute","font-weight": "bold","color": "#ff6651"});$("body").append($i);$i.animate({"top": y - 180,"opacity": 0},1500,function() {$i.remove();});});});
</script>

</body>
</html>