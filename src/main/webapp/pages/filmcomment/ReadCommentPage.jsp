<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String memberId = (String) session.getAttribute("memberId");

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
<%--电影评论模版开始--%>
<div class="article-box">
    <div class="article-wp art-in">
        <div class="article-main-wp">
            <div class="main-picture">
                <figure class="article-figure">
                    <picture>
                        <source srcset="http://localhost:8080${cItem.image}"
                                type="image/webp">
                        <img style="height: 320px" alt="${cItem.fName}"
                             src="http://localhost:8080${cItem.image}"
                             title="${cItem.fName}">
                    </picture>
                    <h1 title="${cItem.title}" class="title">${cItem.title}</h1>
                </figure>
                <div class="main-picture__info"><p class="info-tit" style="">
                    评《
                    <a href="filmpage.action?/filmdetail&fid=${cItem.fId}" target="_blank" class="article-title">
                        ${cItem.fName}
                    </a>
                    》
                </p>
                    <p title="2018-12-27 15:17" class="info-time">${cItem.date}发布</p>
                    <p class="info-view"><i class="ico-article-view"></i>${cItem.viewNum}</p>

                </div>
            </div>

            <div class="article-main">
                <p class="text-article-note"><span class="ico-note"></span>
                    该影评可能有剧透
                </p>
            </div>
            <c:if test="${cItem.video ne 'null'}">
                <div>
                    <video id="my-video" class="video-js" controls preload="auto" width="510" height="330"
                           poster="m.jpg" data-setup="{}">
                        <source src="http://localhost:8080${cItem.video}" type="video/mp4">
                        <p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a
                            web
                            browser that
                            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
                        </p>
                    </video>
                </div>
            </c:if>
            <%--文本内容开始--%>
            <div class="comment">
                ${cItem.content}
            </div>
            <%--文本内容结束--%>
            <div id="comment_list"></div>
        </div>
        <%--右侧作者与推荐视图开始--%>
        <div class="side-art">
            <div class="side-art-wrap">
                <div class="side-art__user">
                    <div class="side-art__user-att">
                        <div class="side-art__user-avatar">
                            <a rel="nofollow"
                               target="_blank" href="membercenter.action?/toPersonOut&mId=${cItem.mId}" class="pic">
                                <img
                                        src="<%=filePath%>${cItem.mImgsrc}"
                                        title=" ${cItem.mName}" alt=" ${cItem.mName}"></a>
                        </div>
                        <div class="side-art__user-detail"><a rel="nofollow"
                                                              target="_blank" title="挖娱乐"
                                                              href="membercenter.action?/toPersonOut&mId=${cItem.mId}"
                                                              class="name">
                            ${cItem.mName}
                        </a>
                            <div class="side-art__user-total"><p><span class="tit">粉丝</span><span>2</span></p></div>
                            <%--<a href="javascript:;" class="btn-att"></a>--%>
                            <c:choose>
                                <c:when test="${empty sessionScope.membername }">
                                    <a class="btn-att"
                                       onclick="x_admin_show('请先登陆','filmpage.action?/movepage&pagename=login',450,540)"
                                       href="javascript:;"></a>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="concern()" href="javascript:;" class="btn-att"></a>
                                </c:otherwise>
                            </c:choose>
                            <p title="挖娱乐，挖出每天娱乐新鲜趣事。" class="side-art__sign"><span>挖娱乐，挖出每天娱乐新鲜趣事。</span></p></div>
                    </div>
                </div>
            </div>
        </div>
        <%--右侧作者与推荐视图结束--%>
    </div>
</div>
<%--电影评论模版结束--%>
</body>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>

<script>
    $(function(){
        $.ajax({
            type:"get"
            ,url:"<%=basePath%>reply/getHtmlByPostId.action?postId=${cItem.id}&filmId=${cItem.fId}"
            ,success:function (data) {
                $("#comment_list").html(data);
            }
        });
    });
    var mId = ${cItem.mId}
        function concern() {
            $.ajax({
                type: "GET", //用GET方式传输
                contentType: 'appliction/json',
                dataType: "json", //数据格式:JSON
                url: 'membercenter.action?/concern', //目标地址
                data: {mId: mId},
                success: function (msg) {
                    if (msg == 200) {
                        layer.msg('添加关注成功', {icon: 1});
                        //设置button效果，开始计时
                        $("#btnSendCode").attr("disabled", "true");
                        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                    } else if (msg == 100) {
                        //layer.msg(rand);
                        layer.msg('不能自己关注自己', {icon: 5, time: 1000});
                    } else if (msg == 150) {
                        layer.msg('已经关注过该作者', {icon: 5, time: 1000});
                    }
                }
            });
        }

</script>

<style>
    .article-figure img {
        width: 100%;
        height: 320px;
    }

    .article-my .user-pic, .ico-article-comment, .ico-article-load, .ico-article-praise:after, .ico-article-praise:before, .ico-article-video, .ico-article-view, .ico-change, .ico-nav-draft, .ico-nav-fav, .ico-nav-send, .recommend-slogan {
        background: url("<%=basePath%>images/view.png") no-repeat;
        background-size: 130px auto;
    }
</style>
<link rel="stylesheet" href="css/commentpage.css">
</html>
