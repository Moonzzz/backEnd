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

<div class="search-result">
    <div class="article-inner">
        <div style="margin-left: 30px;width: 1100px;" class="article-list search-result__content">
            <%--搜索结果开始栏--%>
            <div class="td-tabs">
                <div class="td-tabs__title">
                    <div class="td-tabs__nav">
                        <div class="td-tabs__item is-active">
                            <div>
                                影视
                                <span>${fn:length(mList)}</span><!----></div>
                        </div>
                    </div>
                    <div class="td-tabs__item" style="display:none;">
                        <div>
                            影评
                            <!----><span>109</span></div>
                    </div>
                </div>
                <div class="td-tabs__content">
                    <div class="td-tabs__pane">
                        <div>
                            <div class="search-result__list">
                                <ul>
                                    <c:if test="${fn:length(mList) >0}">
                                        <%--电影信息循环开始--%>
                                    <c:set var="pc" value="${param.pc eq null ? 1:param.pc}"></c:set>
                                    <c:forEach var="i" begin="${(pc-1)*3}"
                                               end="${pc*3-1 >= fn:length(mList) ? fn:length(mList)-1:pc*3-1}">

                                        <%--<c:forEach items="${filmsList}" var="item">--%>
                                        <li class="art-search__item">
                                            <a href="filmpage.action?/filmdetail&fid=${mList[i].id }"
                                               class="result-item"
                                               target="_blank">
                                                <div class="art-search__poster">
                                                    <img style="width: 76px;height: 100px" class="imgarea"
                                                         src="${mList[i].image }"
                                                         alt="${mList[i].name }">
                                                </div>
                                                <div class="art-search__content">
                                                    <p class="art-search__subtitle">
                                                        <span class="movie-name"><em
                                                                class="tag-key"></em>${mList[i].name }</span>
                                                        <span class="tag-type">[电影]</span>
                                                    </p>
                                                    <ul class="art-search__detail">
                                                            <%-- <li>导演：Sue Judd / Vladimir Jurowski</li>--%>
                                                        <li>简介：${mList[i].description }
                                                        </li>
                                                        <li>类型：${mList[i].genre }</li>
                                                    </ul>
                                                </div>
                                            </a><!----><a title="豆瓣评分" class="vote-number" style=""><i
                                                class="icon-vote_search"></i><span
                                                class="vote-number-text">${mList[i].ratingValue }</span></a>
                                        </li>
                                        <%--电影信息结束循环--%>
                                    </c:forEach>
                                    <div class="page">
                                        <div>
                                            <a>第${pc }页/共${tp }页</a>
                                            <c:if test="${pc>1 }">
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc-1 }'/>'>上一页</a>
                                            </c:if>
                                            <c:if test="${tp-pc>=2&&pc>2 }">
                                                ...
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc-2 }'/>'>${pc-2 }</a>
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc-1 }'/>'>${pc-1 }</a>
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc+1 }'/>'>${pc+1 }</a>
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc+2 }'/>'>${pc+2 }</a>
                                                ...
                                            </c:if>
                                            <c:if test="${pc<tp }">
                                                <a href='<c:url value='filmpage.action?/moveSearchPage&pc=${pc+1 }'/>'>下一页</a>
                                            </c:if>
                                            </c:if>
                                        </div>
                                    </div>
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
    /*
        .article-list {
            margin-left: 130px;
            width: 1100px;
        }

        a {
            text-decoration: none
        }

        .imgarea {
            width: 76px;
            height: auto;
        }
    */

    .icon-vote_search {
        /*display: inline-block;
        width: 16px;
        height: 16px;
        margin-left: 5px;*/
        background: url("<%=basePath%>images/tong.png") no-repeat;
        /*        background-size: 110px auto;
                background-position: -70px -100px;*/
    }
</style>
<%--<link rel="stylesheet" href="css/filmsearch.css">--%>
</html>