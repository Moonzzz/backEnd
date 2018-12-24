<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <%--自动配置地址前缀，引用绝对路径，防止路径出错--%>
    <base href=" <%=basePath%>">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
</head>

<body>
<div class="x-body">
    <form class="layui-form" action="<%=basePath%>/member/update.action">
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="x-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="email" required="" lay-verify="email"
                       value="${item.email}" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为您唯一的登入名
            </div>
        </div>
        <input type="hidden" value="${item.id}" name="memberid">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="name"
                       lay-verify="nikename"
                       value="${item.loginName}" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="pass" required="" lay-verify="pass"
               value="${item.password}"        autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                       value="${item.password}"      autocomplete="off" class="layui-input">
            </div>
        </div>
<%--        <div class="layui-form-item">
            <label for="sex" class="layui-form-label">
                <span class="x-red">*</span>性别
            </label>
            <div class="layui-input-inline">
                <input type="text" id="sex" name="sex" required="" lay-verify="repass"
                  value="${item.sex}"  autocomplete="off" class="layui-input">
            </div>
        </div>--%>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" ${item.sex eq '男'?"checked='true'" : "" }>
            <input type="radio" name="sex" value="女" title="女"${item.sex eq '女'?"checked='true'" : "" }>
        </div>
        <div class="layui-form-item">
            <label for="phonenum" class="layui-form-label">
                <span class="x-red">*</span>电话号码
            </label>
            <div class="layui-input-inline">
                <input type="text" id="phonenum" name="phonenum" required="" lay-verify="phone"
                       value="${item.phonenum}"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="birthday" class="layui-form-label">
                <span class="x-red">*</span>生日
            </label>
            <div class="layui-input-inline">
                <input type="text" id="birthday" name="birthday" required="" lay-verify="repass"
                       value="${item.birthday}"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">
                确认修改
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['laydate','form', 'layer'], function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#birthday' //指定元素
        });
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符~~~';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
            ,phone:[/^[1][3,4,5,7,8][0-9]{9}$/, '手机号码格式有误，请重新输入']
        });

        //监听提交
        form.on('submit()', function (data) {
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("修改成功", {icon: 6}, function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
        });


    });
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

