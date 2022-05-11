<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
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

                <input style="border-radius:22px;" type="hidden" name="action" value="searchmovies">
                <input style="color:black;border-radius:12px;margin-top: 3px;" type="text" name="filmname"
                       placeholder="搜一下你想要看的电影吧" autocomplete="on" class="layui-input"
                       id="search">
            </li>
        </ul>
    </form>
</div>
<%--顶部导航栏结束--%>
<%--中部轮播图片开始--%>
<div class="layui-carousel" id="rollphoto">
    <div style="height: 300px;" carousel-item>
        <div><img style="width: 100%"
                  src="http://static-xl9-ssl.xunlei.com/images/201812/b937aa05442620525e9d171aa00bfc1e.jpg?w=1872&h=950">
        </div>
        <div><img style="width: 100%"
                  src="http://static-xl9-ssl.xunlei.com/images/201812/4af22591c171b3c9de9a4cdcf2e8e581.jpg?w=1872&h=950">
        </div>
        <div><img style="width: 100%"
                  src="http://static-xl9-ssl.xunlei.com/images/201812/868b96850692237beb990fd41ba824a7.jpg?w=1872&h=950">
        </div>
        <div><img style="width: 100%"
                  src="http://static-xl9-ssl.xunlei.com/images/201812/868b96850692237beb990fd41ba824a7.jpg?w=1872&h=950">
        </div>
    </div>
</div>

<div class="movie-main" style="height: 1999px">
    <div class="movie-inner">
        <div class="inner-left">
            <div class="td-tabs__title">
                <div class="td-tabs__nav">
                    <div class="td-tabs__item">
                        <div style="font-size: 16px">热映电影</div>
                    </div>
                </div>
            </div>
            <div class="movie-hot__list" style="height: 350px">
                <div class="movie-hot__list-wrap">
                    <ul id="photos">
                        <c:forEach items="${mList}" var="item">
                            <li class="movie-hot__item" id="${item.id}pho">
                                <a target="_blank" href="filmpage.action?/filmdetail&fid=${item.id}">
                                    <img src="${item.image}">
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <c:forEach items="${mList}" var="item">
                    <div class="movie-hot__detail" id="${item.id}inf" style="display: none">
                        <div class="movie-hot__detail-header">
                            <h2><p target="_blank"
                                   style="width: 200px; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                                    ${item.name}
                            </p></h2>
                            <p class="time" style="margin-top: -40px">${item.datePublished}</p>
                            <a href="#" target="_blank"
                               class="vote-number">
                                <i class="icon-vote"></i>
                                <span class="vote-number-text">豆瓣评分：${item.ratingValue}</span>
                            </a>
                        </div>

                        <div class="movie-hot__detail-related">
                        <span class="related-item">
                            <span class="tag-type tag-type--3">简介</span>
                            <p class="article-name">${item.description}</p>
                            <span class="info"></span>
                        </span>
                            <span class="related-item">
                            <span class="tag-type tag-type--3">类型</span>
                            <p class="article-name">${item.genre}</p>
                            <span class="info"></span>
                        </span>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%--影评区开始--%>
            <div class="movie-rec">
                <div class="td-tabs">
                    <div class="td-tabs__title">
                        <div class="td-tabs__nav">
                            <div class="td-tabs__item is-active">最新影评</div>
                        </div>
                    </div>
                    <div class="td-tabs__content">
                        <div class="td-tabs__pane">
                            <div class="article-list-main is-new">
                                <%--评论li元素开始--%>
                                <c:if test="${fn:length(cList) >0}">
                                    <c:forEach items="${cList}" var="cItem">
                                        <li class="article-list-box">
                                            <div class="article-image" style="position: relative">
                                                <a href="comment.action?/readcomment&id=${cItem.id}">
                                                    <img src="<%=filePath%>${cItem.image}">
                                                </a>
                                            </div>
                                            <div class="article-des">
                                                <h3 style="font-size: 15px">
                                                    <a href="comment.action?/readcomment&id=${cItem.id}"
                                                       target="_blank">${cItem.title}</a>
                                                </h3>
                                                <p class="text-article-detail">
                                                        ${cItem.fDescrip}</p>
                                                <div class="article-des-info" style="margin-top: -10px">
                                                    <div class="article-des-inner">
                                                        <a target="_blank"
                                                           href="membercenter.action?/toPersonOut&mId=${cItem.mId}"
                                                           class="user-info">
                                                    <span class="user-picture" style="position: relative" title="会员名"
                                                          alt="name">
                                                            <div>
                                                                <img src="<%=filePath%>${cItem.mImgsrc}"
                                                                     title="${cItem.mName}"
                                                                     alt="${cItem.mName}">
                                                            </div>
                                                    </span>
                                                            <span class="user-name">
                                                                    ${cItem.mName}
                                                            </span>
                                                        </a>
                                                        <span data-v-33f07678="" class="info-tit">
                                                  评《<a href="filmpage.action?/filmdetail&fid=${cItem.fId}"
                                                       class="article-title" target="_blank">${cItem.fName}</a>》
                                                </span>
                                                        <span data-v-33f07678="" class="info-time">
                                                                ${cItem.date}
                                                        </span>
                                                        &nbsp; &nbsp;
                                                        <span>
                                                        <p class="info-view"><i
                                                                class="ico-article-view"></i>${cItem.viewNum}</p>
                                                    </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <%--评论li元素结束--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--电影评论区结束--%>
        <%--电影推荐区开始--%>
        <div class="inner-right" style="height: 700px">
            <div class="movie-poster-ranking">

            </div>
            <div class="movie-rank">
                <div class="movie__title">
                    <h3>智能推荐</h3>
                    <a href="javascript:" onclick="changeTenFilms()"
                       class="link-change"><i class="layui-icon layui-icon-refresh"
                                              style="width: 15px;height: 15px; color: #a0a0a0;"></i></a>
                </div>
                <div class="movie-rank__content">
                    <div class="td-tabs">
                        <div class="td-tabs__content">
                            <div class="td-tabs__pane">
                                <ul id="tenFilms">
                                    <c:forEach begin="1" end="10" var="i">
                                        <li class="movie-rank__item">
                                            <a href="filmpage.action?/filmdetail&fid=${tFList[i-1].id}" target="_blank">
                                                <span class="number">${i}</span>
                                                <span style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"
                                                      class="title">${tFList[i-1].name}</span>
                                                <span class="data">评分${tFList[i-1].ratingValue}</span>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<style>
    .article-my .user-pic, .ico-article-comment, .ico-article-load, .ico-article-praise:after, .ico-article-praise:before, .ico-article-video, .ico-article-view, .ico-change, .ico-nav-draft, .ico-nav-fav, .ico-nav-send, .recommend-slogan {
        background: url("<%=basePath%>images/view.png") no-repeat;
        background-size: 130px auto;
    }
</style>
<script>
    function changeTenFilms() {
        $.ajax({
            type: "post",
            url: "filmpage.action?/changeTenFilms",
            data: {},
            dataType: "json",
            success: function (result, status) {
                $("#tenFilms  li").remove();//把先前的li标签元素全部擦除
                var data = eval(result);
                var index=1;
                for (var i = 0; i < data.length; i++) {
                    $li = $("<li class='movie-rank__item'>");
                    $a = $("<a href='filmpage.action?/filmdetail&fid=" + data[i].id + "'target='_blank'></a>");
                    $span_1 = $("<span  class='number'>" + index + "</span>");
                    $span_2 = $("<span style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;' class='title'>" + data[i].name + "</span>");
                    $span_3 = $("<span class='data'>评分" + data[i].ratingValue + "</span>");
                    $a.append($span_1).append($span_2).append($span_3);
                    $li.append($a);
                    $("#tenFilms").append($li);
                    index++;
                }
            }
        })
    }


    $("#${mList[0].id}inf").css('display', 'block');
    var flag = false;
    <c:forEach items="${mList}" var="item">
    $("#${item.id}pho").hover(function () {
        <c:if test="${item.id ne mList[0].id}">
        $("#${mList[0].id}inf").css('display', 'none');//隐藏
        </c:if>
        $("#${item.id}inf").css('display', 'block');//显示
    }, function () {
        $("#${item.id}inf").css('display', 'none');//隐藏
    });
    </c:forEach>

    $("#photos").hover(function () {
        $("#${mList[0].id}pho").hover(function () {
            $("#${mList[0].id}inf").css('display', 'block');//显示
            flag = false;
        }, function () {
            $("#${mList[0].id}inf").css('display', 'none');//隐藏
        });
    }, function () {
        $("#${mList[0].id}inf").css('display', 'block');//显示
    });

    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#rollphoto'
            , width: '100%' //设置容器宽度
            , high: '100%'
            //,arrow: 'always' //始终显示箭头
            , anim: 'default' //切换动画方式
        });
    });
    $(function () {
        $(".movie-hot__list>.movie-hot__list-wrap>ul>li>a>img").mouseenter(function () {
            $(this).stop().animate({"width": "116px", "margin": "0px"}, 200);
        }).mouseleave(function () {
            $(this).stop().animate({"width": "110px", "height": "152px", "margin": "0"}, 200);
        });
    });

    /*    $(function () {
            /!*从服务端获取json数据*!/
            $.getJSON("filmpage.action?/names", function (data) {
                $("#search").autocomplete({
                    source: data
                });
            });
        });*///
    /*    $("#search").bind('input propertychange', function () {
            /!*从服务端获取json数据*!/
            let key = $("#search").val();
            $.getJSON("filmpage/names.action?key="+key, function (data) {
                $("#search").autocomplete({
                    source: data
                });
            });
        });*/
</script>
</html>