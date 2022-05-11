<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <%--自动配置地址前缀，防止路径出错--%>
    <base href="<%=basePath%>">
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/font.css">
    <link rel="stylesheet" href="css/xadmin.css">
    <link rel="stylesheet" href="lib/layui/css/style.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="js/xadmin.js"></script>
    <%--<script type="text/javascript" src="js/register.js"></script>--%>
</head>
<body class="login-bg" background="images/bg.png">
<div class="login-main" style="margin-top: 40px;margin-right: 41px">
    <form method="POST" class="layui-form" action="<%=basePath%>LoginCheck.action">
       <%--<form class="layui-form" id="form1" method="post" lay-filter="first1">--%>
       <input type="hidden" name="action" value="registermember">
        <!--输入用户名-->
        <div class="layui-input-inline">
            昵称：
            <div class="layui-inline" style="width: 85%">
                <input type="text" name="name" id="name" required lay-verify="nikename" placeholder="请输入昵称"
                       autocomplete="off" class="layui-input">
            </div>
            <!--判断用户名是否可用的图标 -->
            <div class="layui-inline">
                <i class="layui-icon" hidden id="ri" style="color: green; font-weight: bold"></i>
                <i class="layui-icon" hidden id="le" style="color: red; font-weight: bold">ဆ</i>
            </div>
        </div>
        <!--输入邮箱-->
        <div class="layui-input-inline">
            邮箱：
            <div class="layui-inline" style="width: 85%">
                <input type="text" name="email" id="email" required lay-verify="email" placeholder="请输入邮箱"
                       autocomplete="off" class="layui-input">
            </div>
            <!--判断邮箱名是否可用的图标 -->
            <div class="layui-inline">
                <i class="layui-icon" hidden id="ri1" style="color: green; font-weight: bold"></i>
                <i class="layui-icon" hidden id="le1" style="color: red; font-weight: bold">ဆ</i>
            </div>
        </div>
        <!--输入密码-->
        <div class="layui-input-inline">
            密码：
            <div class="layui-inline" style="width: 85%">
                <input type="password" name="pwd" id="pwd" required lay-verify="pass" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <!--判断密码是否可用的图标 -->
            <div class="layui-inline">
                <i class="layui-icon" hidden id="ri2" style="color: green; font-weight: bold"></i>
                <i class="layui-icon" hidden id="le2" style="color: red; font-weight: bold">ဆ</i>
            </div>
        </div>
        <%--确认密码--%>
        <div class="layui-input-inline">
            确认：
            <div class="layui-inline" style="width: 85%">
                <input type="password" name="repwd" id="repwd" required lay-verify="repass" placeholder="请输入确认密码"
                       autocomplete="off" class="layui-input">
            </div>
            <!--判断重复密码是否可用的图标 -->
            <div class="layui-inline">
                <i class="layui-icon" hidden id="ri3" style="color: green; font-weight: bold"></i>
                <i class="layui-icon" hidden id="le3" style="color: red; font-weight: bold">ဆ</i>
            </div>
        </div>
        <!--输入验证码-->
        <div class="layui-input-inline">
            验证：
            <div class="layui-inline" style="width:48%">
                <input type="text" name="checkcode" id="checkcode" required lay-verify="required"
                       placeholder="请在60秒内输入验证码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline" style="width:29%">
                <button style="margin-left: 23px" type="button" name="checkcode" id="btnSendCode"
                        autocomplete="off" class="layui-btn">获取验证码
                </button>
            </div>
        </div>
        <div class="layui-input-inline login-btn" style="width: 96%">
            <button id="registe" type="submit" class="layui-btn" lay-submit lay-filter="registesub">注册</button>
        </div>
        <br/>
        <p style="width: 85%">
            <a href="filmpage.action?action=movepage&pagename=login" class="fl">已有账号？立即登录</a>
            <a href="javascript:;" class="fr">忘记密码？</a>
        </p>
    </form>
</div>
<script src="layui/layui.js"></script>
<script type="text/javascript">
    var rand;//60秒内输入正确的验证密码
    var email;
    var nameflag = 1;//默认可以发送验证码
    var pswflag=1;
    var repswflag=1;
    var emailflag=1;
    //设置点击发送验证码发生的事件
    $("#btnSendCode").click(function () {
        //layer.msg('正在调用该函数', {icon: 1});
        email = $('#email').val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (myreg.test(email)) {
            if (nameflag == 1&&pswflag==1&&repswflag==1&&emailflag==1) {
                var x = 1000;
                var y = 9999;
                rand = parseInt(Math.random() * (x - y + 1) + y);
                $("#registe").removeAttr("disabled");//启用按钮
                //$("#registe").css("background-color", "gary");
                sendMessage();
            } else {
                layer.msg('请完善注册信息', {icon: 7});
                //layer.msg('n:'+nameflag +'p:'+pswflag+'rp'+repswflag);
            }
        } else {
            layer.msg('请填写正确的邮箱格式', {icon: 7});
        }
    });
    //点击注册按钮触发的事件
    $("#registe").click(function () {
        var inputcode = $("#checkcode").val();
        if (rand == inputcode) {
            layer.msg('验证码输入正确', {icon: 1});
        } else {
            layer.msg('对不起，验证码输入错误', {icon: 2});
            return false;
        }
    });
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    function sendMessage() {
        curCount = count;
        //向后台发送处理数据
        $.ajax({
            type: "GET", //用GET方式传输
            contentType: 'appliction/json',
            dataType: "json", //数据格式:JSON
            url: 'LoginCheck.action', //目标地址
            data: "action=" + "codeCheck" + "&email=" + email + "&checkcode=" + rand,
            success: function (msg) {
                if (msg == 200) {
                    layer.msg('发送验证码成功', {icon: 1});
                    //设置button效果，开始计时
                    $("#btnSendCode").attr("disabled", "true");
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    //layer.msg(rand);
                    layer.msg('发送验证码失败', {icon: 5, time: 1000});
                }
            }
        });
    }

    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnSendCode").removeAttr("disabled");//启用按钮
            //$("#btnSendCode").val("重新发送验证码");
            $("#registe").attr("disabled", "true");
        }
        else {
            curCount--;
            // $("#checkcode").val("请在" + curCount + "秒内输入验证码");
        }
    }

    /*前端即使触发动作设置*/
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        //为表单添加blur事件
        $('#name').blur(function () {
            var name = $('#name').val();
            //ajax异步刷新
            if (name.length < 3) {
                nameflag = 0;
                layer.msg('昵称至少要3个字符', {icon: 5, time: 1000});
                $('#ri').attr('hidden', 'hidden');
                $('#le').removeAttr('hidden');
            } else {
                $.ajax({
                    url: "LoginCheck/isExistName.action",
                    method: "GET",
                    contentType: 'appliction/json',
                    dataType: "json",
                    data: {name: name},
                    success: function (data) {
                        if (data == '200') {
                            //layer.msg('可以注册')
                            $('#ri').removeAttr('hidden');
                            $('#le').attr('hidden', 'hidden');
                            nameflag = 1;
                        } else {
                            nameflag = 0;//状态值设为0，信息未填写完整不允许发送验证码
                            //layer.msg('用户名已被占用')
                            $('#ri').attr('hidden', 'hidden');
                            $('#le').removeAttr('hidden');
                            $('#registe').attr('hidden', 'hidden');
                            layer.msg('用户名已被占用', {icon: 5, time: 1000});
                        }
                    }
                })
            }
        });
        //为邮箱输入框添加blur事件
        $('#email').blur(function () {
            var email = $('#email').val();
            //ajax异步刷新
            var flag2 = email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/);
            if (!flag2) {
                flag = 0;
                $('#ri1').attr('hidden', 'hidden');
                $('#le1').removeAttr('hidden');
                layer.msg('请填写正确的邮箱格式', {icon: 7, time: 1000});
            } else {
                $.ajax({
                    url: "LoginCheck/isExistEmail.action",
                    method: "GET",
                    contentType: 'appliction/json',
                    dataType: "json",
                    data: {email: email},
                    success: function (data) {
                        if (data == '200') {
                            //layer.msg('可以注册')
                            $('#ri1').removeAttr('hidden');
                            $('#le1').attr('hidden', 'hidden');
                            emailflag = 1;
                        } else {
                            emailflag = 0;//状态值设为0，信息未填写完整不允许发送验证码
                            //layer.msg('邮箱已被占用')
                            $('#ri1').attr('hidden', 'hidden');
                            $('#le1').removeAttr('hidden');
                            layer.msg('该邮箱已被占用', {icon: 5, time: 1000});
                        }
                    }
                })
            }
        });
        //验证密码位数是否为6位以上
        $('#pwd').blur(function () {
            var pwd = $('#pwd').val();
            if (pwd.length < 6) {
                pswflag = 0;//状态值设为0，信息未填写完整不允许发送验证码
                layer.msg('密码至少六位', {icon: 5, time: 1000});
                $('#ri2').attr('hidden', 'hidden');
                $('#le2').removeAttr('hidden');
                return false;
            }else{
                pswflag=1;
                $('#le2').attr('hidden', 'hidden');
                $('#ri2').removeAttr('hidden');
            }
        });
        //判断重复密码是否正确
        $('#repwd').blur(function () {
            var pwd = $('#pwd').val();
            var repwd = $('#repwd').val();
            //ajax异步刷新
            if (pwd.length < 6) {
                pswflag = 0;//状态值设为0，信息未填写完整不允许发送验证码
                layer.msg('密码至少六位', {icon: 5, time: 1000});
                $('#ri2').attr('hidden', 'hidden');
                $('#le2').removeAttr('hidden');
                return false;
            } else if (pwd != repwd) {
                repswflag = 0;//状态值设为0，信息未填写完整不允许发送验证码
                layer.msg('两次输入密码不一致', {icon: 5, time: 1000});
                $('#ri3').attr('hidden', 'hidden');
                $('#le3').removeAttr('hidden');
                return false;
            } else {
                repswflag = 1;
                $('#le2').attr('hidden', 'hidden');
                $('#ri2').removeAttr('hidden');
                $('#le3').attr('hidden', 'hidden');
                $('#ri3').removeAttr('hidden');
            }
        });
    });
</script>
</body>
</html>
