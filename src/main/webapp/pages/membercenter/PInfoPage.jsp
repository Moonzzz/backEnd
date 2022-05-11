<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin250</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <%--自动配置地址前缀，引用绝对路径，防止路径出错--%>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="css/filmpage.css">
    <link rel="stylesheet" href="css/filmmainpage.css">
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    <%--自动补全--%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<div class="layui-form">
    <table style="width: 900px; margin-left: 140px;text-align: left" class="layui-table">
        <colgroup>
            <col width="150">
            <col width="200">
        </colgroup>
        <thead>
        <tr>
            <th>信息名称</th>
            <th>详细信息</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>头像</td>
            <td><img style="width: 120px;height: 80px" src="http://localhost:8080${mItem.imgsrc}"></td>
        </tr>
        <tr>
            <td>昵称</td>
            <td>${mItem.loginName}</td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td>${mItem.email}</td>
        </tr>
        <tr>
            <td>性别</td>
            <td>${mItem.sex}</td>
        </tr>
        <tr>
            <td>生日</td>
            <td>${mItem.birthday}</td>
        </tr>
        <tr>
            <td>加入时间</td>
            <td>${mItem.joindate}</td>
        </tr>
        <tr>
            <td colspan="2">
                <a onclick="x_admin_show('修改信息','membercenter.action?/movepage&pagename=update&id=${mItem.id }',600,400)">
                <button style="float: right" class="layui-btn">
                    <i class="layui-icon">&#xe631;</i>
                    修改信息
                </button>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
