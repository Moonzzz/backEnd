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

<form class="layui-form layui-form-pane1" id="form1" name="form1" style="display: none;" lay-filter="first1">
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="email" id="email" required lay-verify="email" placeholder="请输入邮箱"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="findPassword">确定</button>
        </div>
    </div>
</form>
<div class="login-main" style="margin-top: 40px;margin-right: 41px">
    <form onsubmit="formCheck" id="loginForm" method="POST" class="layui-form" action="filmpage.action?/memberlogin">

        <%--登陆类型代号：1邮箱登陆 2用户名登陆--%>
        <input type="hidden" id="logintype" name="logintype" value="1">
        <!--输入用户名或邮箱-->
        <div class="layui-input-inline">
            昵称：
            <div class="layui-inline" style="width: 85%">
                <input type="text" name="name" id="name" required lay-verify="nikename" placeholder="请输入会员名或邮箱"
                       autocomplete="off" class="layui-input">
            </div>
            <!--判断用户名是否可用的图标 -->
            <div class="layui-inline">
                <i class="layui-icon" hidden id="ri" style="color: green; font-weight: bold"></i>
                <i class="layui-icon" hidden id="le" style="color: red; font-weight: bold">ဆ</i>
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
        <div class="layui-input-inline">
            验证：
            <div class="layui-inline" style="width: 85%">
                <input id="check" style=" float:left;width: 170px;height: 37px;" type="text" value="" required
                       lay-verify="nikename" placeholder="请输入验证码（不区分大小写）" class="layui-input">
                <canvas id="canvas" style="margin-left: 13px; float:left;width:114px; height:37px;"></canvas>
            </div>
        </div>
        <div class="layui-input-inline login-btn" style="width: 96%">
            <button type="submit" id="login" class="layui-btn">登陆</button>
        </div>
        <br/>
        <p style="width: 85%">
            <a href="memberControl.action?pagename=register" class="fl">没有账号？立即注册</a>
            &nbsp;&nbsp;<a style="margin-left: 40px" onclick="find()" href="javascript:;" class="fr">忘记密码？点击找回</a>
        </p>
    </form>
</div>
<script src="layui/layui.js"></script>
<script type="text/javascript">
    function find() {
        layui.use(['table', 'form', 'laydate'], function () {
            var table = layui.table
                , form = layui.form
                , laydate = layui.laydate;
            layer.open({
                title: '找回密码',
                type: 1,
                area: ['400px', '200px'],
                content: $("#form1")
            });
            form.on('submit(findPassword)', function (obj) {
                var email = $('#email').val();
                $.ajax({
                    url: "LoginCheck.action?/findpsw",
                    type: "POST",
                    dataType: "text",
                    data: {
                        "email": email
                    },
                    success: function (data) {
                        if (data == 'true') {
                            layer.msg("发送邮件成功！", {icon: 1});
                        } else if (data == 'false') {
                            layer.msg("发送邮件失败！", {icon: 7});
                        } else if (data == 'noExist') {
                            layer.msg("抱歉，该邮箱未绑定账号！", {icon: 2});
                        }
                        //window.location.reload();
                    },
                    error: function (data) {
                        layer.msg("发送邮件失败！", {icon: 7});
                    }
                });
                return false;
            });
        });
    }

    var show_num = [];
    $(function () {

        draw(show_num);
        $("#canvas").on('click', function () {
            draw(show_num);
        });

        /*为了测试，暂时注释掉验证码功能*/
        $("#login").click(function () {
            var name = $('#name').val();
            var type = name.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/);
            //不是邮箱格式则为用户名登陆类型
            if (!type) {
                $("#logintype").val("2");
            }
            /* var val = $("#check").val().toLowerCase();
             var num = show_num.join("");
             if (val == '') {
                 layer.msg("请输入验证码！", {icon: 7});
                 return false;
             } else if (val == num) {
                 $(".input-val").val('');
                 draw(show_num);
                 //$("#loginForm").submit();
             } else {
                 layer.msg("验证码错误！请重新输入！", {icon: 2});
                 $(".input-val").val('');
                 draw(show_num);
                 return false;
             }*/
        });
    });

    function draw(show_num) {
        var canvas_width = $('#canvas').width();
        var canvas_height = $('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度

        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
</script>
</body>
</html>
