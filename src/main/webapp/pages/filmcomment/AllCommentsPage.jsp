<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
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
<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <span class="x-right" style="line-height:40px">共有数据：${fn:length(cList)}条</span>
    </xblock>
    <table class="layui-table" id="LAY-table-operate" lay-filter="LAY-table-operate">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>图片</th>
            <th>标题</th>
            <th>发表时间</th>
            <th>作者</th>
            <th>电影名</th>
            <th>查看影评内容</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${fn:length(cList) >0}">

        <c:set var="pc" value="${param.pc eq null ? 1:param.pc}"></c:set>
        <c:forEach var="i" begin="${(pc-1)*3 }" end="${pc*3-1 >= fn:length(cList) ? fn:length(cList)-1:pc*3-1}">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${cList[i].id }'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td><img src="http://localhost:8080${cList[i].image}" style="width: 100px;height: 76px"></td>
                <td>${cList[i].title}</td>
                <td>${cList[i].date }</td>
                <td>${cList[i].mName}</td>
                <td>${cList[i].fName}</td>
                <td>
                    <a onclick="x_admin_show('添加用户','admin.action?/movepage&/=showcontent&cId=${cList[i].id }',600,400)">查看影评内容</a>
                </td>
                <td class="td-manage">
                    <a title="删除" onclick="member_del(this,'${cList[i].id }')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page">
        <div>
            <a>第${pc }页/共${tp }页</a>
            <c:if test="${pc>1 }">
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc-1 }'/>'>上一页</a>
            </c:if>
            <c:if test="${tp-pc>=2&&pc>2 }">
                ...
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc-2 }'/>'>${pc-2 }</a>
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc-1 }'/>'>${pc-1 }</a>
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc+1 }'/>'>${pc+1 }</a>
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc+2 }'/>'>${pc+2 }</a>
                ...
            </c:if>
            <c:if test="${pc<tp }">
                <a href='<c:url value='admin.action?/moveCommentPage&pc=${pc+1 }'/>'>下一页</a>
            </c:if>
            </c:if>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate', 'jquery'], function () {
        //layui.use("layer",function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

        $ = layui.jquery;
    });

    /*用户影评-异步删除*/
    function member_del(obj, id) {

        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "comment.action?/delectOne",
                method: "GET",
                contentType: 'appliction/json',
                data: {commentid: id},
                success: function (msg) {
                    if (msg == 200) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                    } else {
                        layer.msg('删除失败', {icon: 5, time: 1000});
                    }
                }
            });
        });
    }

    /*异步多选删除*/
    function delAll(argument) {
        //var checkStatus=table.checkStatus('LAY-table-operate');
        //var noList = new Array();
        var data = tableCheck.getData();
        /*        noList = data;*/
        var json = JSON.stringify(data);
        if (data.length == 0) {
            layer.msg('当前尚未选择影评信息', {icon: 7, time: 1000});
            return false;
        }
        layer.confirm('确认要删除吗？' + json, function (index) {
            $.ajax({
                url: "comment.action?/delectcomments",
                method: "GET",
                contentType: 'appliction/json',
                dataType: "json",
                data: {"ids": json},
                success: function (msg) {
                    if (msg == 200) {
                        layer.msg('删除成功', {icon: 1});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    } else {
                        layer.msg('删除失败', {icon: 5, time: 1000});
                    }
                }
            });
        });
    }
</script>
</html>
