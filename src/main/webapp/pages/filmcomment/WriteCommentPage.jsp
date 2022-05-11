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
    <link rel="stylesheet" href="css/filmtype.css">
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

<%--顶部导航栏开始--%>
<div class="container">
    <div class="logo"><a href="<%=basePath%>filmpage.action?/toFilmMainPage">电影选优</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
    <ul class="layui-nav left fast-add " lay-filter="">
        <li class="layui-nav-item">
            <a href="filmpage.action?/movepage&pagename=types&type=all" target="_blank" class="">影视库 <span
                    class="arrow"></span></a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd>
                    <a href="filmpage.action?/movepage&pagename=types&type=喜剧" target="_blank">喜剧</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=动作" target="_blank">动作</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=奇幻" target="_blank">奇幻</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=恐怖" target="_blank">恐怖</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=爱情" target="_blank">爱情</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=真人秀" target="_blank">真人秀</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=科幻" target="_blank">科幻</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=悬疑" target="_blank">悬疑</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=古装" target="_blank">古装</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=历史" target="_blank">历史</a>
                    <a href="filmpage.action?/movepage&pagename=types&type=纪录片" target="_blank">纪录片</a>
                </dd>
            </dl>
        </li>
    </ul>
    <%--用户信息--%>
    <ul class="layui-nav right" lay-filter="">
        <c:choose>
            <c:when test="${empty sessionScope.membername }"><!-- 如果 -->
                <li class="layui-nav-item">
                    <a onclick="x_admin_show('会员登陆','filmpage.action?/movepage&pagename=login',450,540)"
                       href="javascript:;">登陆</a>
                </li>
                <li class="layui-nav-item">
                    <a onclick="x_admin_show('注册(请先点击获取验证码按钮进行身份确认)','memberControl.action?pagename=register',450,540)"
                       href="javascript:;">注册</a>
                </li>
            </c:when>
            <c:otherwise><!-- 否则 -->
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        您好，${sessionScope.membername}
                    </a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="membercenter.action?/movepage&pagename=pCenter"><i
                                class="iconfont">&#xe6a2;</i>我的主页</a></dd>
                        <dd><a href="membercenter.action?/pComment"><i
                                class="iconfont">&#xe6a8;</i>我的影评</a></dd>
                        <dd><a href="filmpage.action?/movepage&pagename=destroy"><i
                                class="iconfont">&#xe6b8;</i>注销</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item to-index"><a href="membercenter.action?/movepage&pagename=pCenter">个人中心</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
    <%--搜索框--%>
    <form action="<%=basePath%>filmpage.action" METHOD="post" name="search">
        <ul>
            <li class="layui-nav right" style="margin-left: -40px">
                <button id="searchbtn" style="background-color:#1E9FFF;margin-right: 50px" class="layui-btn"
                        type="submit" lay-filter="sreach"><i
                        class="layui-icon">&#xe615;</i></button>
            </li>
            <li class="layui-nav right">

                <input type="hidden" name="action" value="searchmovies">
                <input style="color:black;border-radius:12px;margin-top: 3px;" type="text" name="filmname"
                       placeholder="搜一下你想要看的电影吧" autocomplete="on" class="layui-input"
                       id="search">
            </li>
        </ul>
    </form>
</div>
<%--顶部导航栏结束--%>
<%--编辑影评框开始--%>
<div class="container_box">
    <form class="layui-form" action="comment.action?/writeComment" method="post" id="uploadForm"
          enctype="multipart/form-data">
        <div class="row" style="margin: 40px">
            <div class="layui-input-inline">
                <span class=" input-group-addon">影评标题：</span> <input type="text" required lay-verify="nikename"
                                                                     name="title" id="title" placeholder="请填写影评标题"
                                                                     class="layui-input" maxlength="20">
            </div>
        </div>
        <div class="row" style="margin: 40px">
            <div class="col-xs-8 col-sm-4  input-group">
                <span class=" input-group-addon">请上传影评封面(必选)</span><input type="file" name="picFile"
                                                                          required lay-verify="nikename"   class="layui-input">
            </div>
        </div>
        <div class="row" style="margin: 40px">
            <div class="col-xs-8 col-sm-4  input-group">
                <span class=" input-group-addon">请上传影评视频(非必选)</span><input type="file" name="videoFile"
                                                                      class="layui-input">
            </div>
        </div>
        <div id="editor" style="margin: 40px">
            <p>请在这里编写影评</p>
        </div>
        <input type="hidden" name="fId" value="${fId}">
        <input type="hidden" name="content" id="content">
        <div class="row" style="margin: 40px">
            <div class="col-xs-4 col-sm-2  ">
                <button style="margin-left: 670px" type="submit" class="layui-btn"
                        id="send">提交影评
                </button>
            </div>
        </div>
    </form>
</div>
<%--编辑影评框结束--%>
</body>
<style>
    .container_box {
        margin-left: 261px;
        margin-top: 80px;
        width: 840px;
        /*margin: 0 auto;*/
    }
</style>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>
<script type="text/javascript">
    $(function () {
        //创建富文本框
        var E = window.wangEditor;
        var editor = new E('#editor');
        editor.create();
        //editor.txt.html('请输入影评内容...');
        //hidden赋值，传至后台发送影评
        $("#send").click(function () {
            var text = editor.txt.html();
            $("#content").val(text);
            //$("#content").val=text;
            var formData = new FormData($('#uploadForm')[0]);
            /*$.ajax({
                type: 'POST',
                url: 'comment.action?/toComment',
                data: formData,
                async: false,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert("OK");
                //alert(data);
            }).error(function () {
                alert(text);
                //alert("上传失败");
            });*/
        });


        // 在键盘按下并释放及提交后验证提交表单
        $("#uploadForm").validate({
            rules: {
                email: {
                    required: true,
                    email: true
                }
            },
            messages: {

                email: "请输入一个正确的邮箱",

            }
        });
    });
</script>
</html>
