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
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
</head>
<body>
<div class="x-body">
    <div class="layui-row">
        <form method="POST" action="searchMembers.action" class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <input class="layui-input" placeholder="截止日" name="end" id="end">
            <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            <button class="layui-btn"type="submit"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','memberControl.action?pagename=add',600,400)">
            <i class="layui-icon"></i>添加
        </button>
        <span class="x-right" style="line-height:40px">共有数据：${fn:length(mList)}条</span>
    </xblock>
    <table class="layui-table" id="LAY-table-operate" lay-filter="LAY-table-operate">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i
                        class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>会员名</th>
            <th>性别</th>
            <th>手机号</th>
            <th>邮箱号</th>
            <th>出生日期</th>
            <th>加入时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="pc" value="${param.pc eq null ? 1:param.pc}"></c:set>
        <c:forEach var="i" begin="${(pc-1)*5 }" end="${pc*5-1 >= fn:length(mList) ? fn:length(mList)-1:pc*5-1}">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${mList[i].id }'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${mList[i].id }</td>
                <td>${mList[i].loginName }</td>
                <td>${mList[i].sex }</td>
                <td>${mList[i].phonenum }</td>
                <td>${mList[i].email }</td>
                <td>${mList[i].birthday }</td>
                <td>${mList[i].joindate }</td>
                <td class="td-status">
                    <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td>
                <td class="td-manage">
                    <a onclick="member_stop(this,'10001')" href="javascript:;" title="启用">
                        <i class="layui-icon">&#xe601;</i>
                    </a>
                        <%--<a title="编辑" onclick="x_admin_show('编辑','MemberUpdate.jsp',600,400)" href="javascript:;">
                            <i class="layui-icon">&#xe642;</i>
                        </a>--%>
                    <a onclick="x_admin_show('修改信息','memberControl.action?pagename=update&id=${mList[i].id }',600,400)"
                       title="修改信息" href="javascript:;">
                        <i class="layui-icon">&#xe631;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,'${mList[i].id }')" href="javascript:;">
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
                <a href='<c:url value='memberControl.action?pagename=movepage&pc=${pc-1 }'/>'>上一页</a>
            </c:if>
            <c:if test="${pc<tp }">
                <a href='<c:url value='memberControl.action?pagename=movepage&pc=${pc+1 }'/>'>下一页</a>
            </c:if>
        </div>
    </div>
</div>
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

    /*用户-停用*/
    function member_stop(obj, id) {
        layer.confirm('确认要停用吗？', function (index) {

            if ($(obj).attr('title') == '启用') {
                //发异步把用户状态进行更改
                $(obj).attr('title', '停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!', {icon: 2, time: 1000});
            } else {
                $(obj).attr('title', '启用')
                $(obj).find('i').html('&#xe601;');
                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!', {icon: 1, time: 1000});
            }
        });
    }

    /*用户-异步删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "<%=basePath%>/member/delect.action",
                method: "GET",
                contentType: 'appliction/json',
                data: {memberid: id},
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
            layer.msg('当前尚未选择会员信息', {icon: 7, time: 1000});
            return false;
        }
        layer.confirm('确认要删除吗？' + json, function (index) {
            $.ajax({
                url: "<%=basePath%>/member/delectmembers.action",
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
<script>var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>
</html>