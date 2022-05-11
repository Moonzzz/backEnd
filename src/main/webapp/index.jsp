<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
String fileBathPath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";

%>
<!doctype html>
<html lang="en">
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
<style>
    body{margin: 10px;}
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    .code {
        width: 400px;
        margin: 0 auto;
    }
    .input-val {
        width: 295px;
        background: #ffffff;
        height: 2.8rem;
        padding: 0 2%;
        border-radius: 5px;
        border: none;
        border: 1px solid rgba(0,0,0,.2);
        font-size: 1.0625rem;
    }
    #canvas {
        float: right;
        display: inline-block;
        border: 1px solid #ccc;
        border-radius: 5px;
        cursor: pointer;
    }
    .btn {
        width: 100px;
        height: 40px;
        background: #f1f1f1;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin: 20px auto 0;
        display: block;
        font-size: 1.2rem;
        color: #e22e1c;
        cursor: pointer;
    }
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }
</style>
</head>

<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<body cz-shortcut-listen="true" class="layui-layout-body">
<div class="layui-layer-move">

    <div class="code">
        <input type="text" value="" placeholder="请输入验证码（不区分大小写）" class="input-val">
        <canvas id="canvas" width="100" height="43"></canvas>
        <button class="btn">提交</button>
    </div>

</div>
<div>
    <h2>
        <i class="">海王的视频和图片</i>
        · · · · · ·
        <span class="pl">
            (
                <a href="https://movie.douban.com/subject/3878007/trailer#trailer">预告片41</a>&nbsp;|&nbsp;<a href="https://movie.douban.com/subject/3878007/trailer#short_video">视频评论2</a>&nbsp;·&nbsp;<a href="/video/create?subject_id=3878007">添加</a>&nbsp;|&nbsp;<a href="https://movie.douban.com/subject/3878007/all_photos">图片878</a>&nbsp;·&nbsp;<a href="https://movie.douban.com/subject/3878007/mupload">添加</a>
            )
            </span>
    </h2>
    <ul class="related-pic-bd  wide_videos">
        <li class="label-trailer">
            <a class="related-pic-video" href="https://movie.douban.com/trailer/240074/#content" title="预告片" style="background-image:url(https://img1.doubanio.com/img/trailer/medium/2541280237.jpg?)">
            </a>
        </li>

        <li class="label-short-video">
            <a class="related-pic-video" href="https://movie.douban.com/video/101725/" title="视频评论" style="background-image:url(https://img1.doubanio.com/view/photo/photo/public/p2542486308.jpg?)">
            </a>
        </li>
        <li>
            <a href="https://movie.douban.com/photos/photo/2459657787/"><img src="https://img1.doubanio.com/view/photo/sqxs/public/p2459657787.jpg" alt="图片" /></a>
        </li>
        <li>
            <a href="https://movie.douban.com/photos/photo/2456697541/"><img src="https://img3.doubanio.com/view/photo/sqxs/public/p2456697541.jpg" alt="图片" /></a>
        </li>
    </ul>
</div>
</body>
<script>

    $(function(){
        var show_num = [];
        draw(show_num);

        $("#canvas").on('click',function(){
            draw(show_num);
        })
        $(".btn").on('click',function(){
            var val = $(".input-val").val().toLowerCase();
            var num = show_num.join("");
            if(val==''){
                alert('请输入验证码！');
            }else if(val == num){
                alert('提交成功！');
                $(".input-val").val('');
                draw(show_num);

            }else{
                alert('验证码错误！请重新输入！');
                $(".input-val").val('');
                draw(show_num);
            }
        })
    })

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
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
</html>