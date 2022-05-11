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
<div class="library-wrap movie-inner">
    <div class="library-type">
        <li class="library-type__item is-more">
            <span class="title">地区：</span>
            <div class="library-type__list">
                <a href="javascript:;" onclick="aa(this)">全部</a>
                <a href="javascript:;" onclick="aa(this)">中国大陆</a>
                <a href="javascript:;" onclick="aa(this)">台湾</a>
                <a href="javascript:;" onclick="aa(this)">美国</a>
                <a href="javascript:;" onclick="aa(this)">日本</a>
                <a href="javascript:;" onclick="aa(this)">韩国</a>
                <a href="javascript:;" onclick="aa(this)">印度</a>
                <a href="javascript:;" onclick="aa(this)">香港</a>
                <a href="javascript:;" onclick="aa(this)">法国</a>
                <a href="javascript:;" onclick="aa(this)">西班牙</a>
                <a href="javascript:;" onclick="aa(this)">俄罗斯</a>
                <a href="javascript:;" onclick="aa(this)">德国</a>
                <a href="javascript:;" onclick="aa(this)">意大利</a>
                <a href="javascript:;" onclick="aa(this)">加拿大</a>
                <a href="javascript:;" onclick="aa(this)">澳大利亚</a>
                <a href="javascript:;" onclick="aa(this)">丹麦</a>
            </div>
        </li>
        <li class="library-type__item is-more">
            <span class="title">类型：</span>
            <div class="library-type__list">
                <a href="javascript:;" onclick="bb(this)">全部</a>
                <c:forEach items="${types}" var="li">
                    <a href="javascript:;" onclick="bb(this)">${li}</a>
                </c:forEach>
            </div>
        </li>
        <li class="library-type__item is-more">
            <span class="title">年代：</span>
            <div class="library-type__list">
                <a href="javascript:;" class="" onclick="cc(this)">全部</a>
                <a href="javascript:;" class="" onclick="cc(this)">2019</a>
                <a href="javascript:;" class="" onclick="cc(this)">2018</a>
                <a href="javascript:;" class="" onclick="cc(this)">2017</a>
                <a href="javascript:;" class="" onclick="cc(this)">2016</a>
                <a href="javascript:;" class="" onclick="cc(this)">2015-2010</a>
                <a href="javascript:;" class="" onclick="cc(this)">2010-2000</a>
                <a href="javascript:;" class="" onclick="cc(this)">90年代</a>
                <a href="javascript:;" class="" onclick="cc(this)">80年代</a>
                <a href="javascript:;" class="" onclick="cc(this)">70年代</a>
                <a href="javascript:;" class="" onclick="cc(this)">60年代</a>
                <a href="javascript:;" class="" onclick="cc(this)">更早</a>
            </div>
        </li>
    </div>
    <div class="library-main__list">
        <ul id="ul_movies">
            <c:if test="${fn:length(films) >0}">
            <c:set var="pc" value="${param.pc eq null ? 1:param.pc}"></c:set>
            <c:forEach var="i" begin="${(pc-1)*15}"
                       end="${pc*15-1 >= fn:length(films) ? fn:length(films)-1:pc*15-1}">
                <li class="library-main__item">
                    <a href="filmpage.action?/filmdetail&fid=${films[i].id}" title="${films[i].name}" target="_blank"
                       class="picture">
                        <img src="${films[i].image}" alt="${films[i].name}" title="${films[i].name}">
                    </a>
                    <div class="info"><a href="filmpage.action?/filmdetail&fid=${films[i].id}" target="_blank"
                                         class="name">${films[i].name}</a>
                        <a href="javascript:;" title="豆瓣评分" class="vote-number">
                            <i class="icon-vote"></i><span class="vote-number-text">${films[i].ratingValue}</span>
                        </a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="elPage" class="page">
        <div>
            <a>第${pc }页/共${tp }页</a>
            <c:if test="${pc>1 }">
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc-1 }'/>'>上一页</a>
            </c:if>
            <c:if test="${tp-pc>=2&&pc>2 }">
                ...
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc-2 }'/>'>${pc-2 }</a>
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc-1 }'/>'>${pc-1 }</a>
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc+1 }'/>'>${pc+1 }</a>
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc+2 }'/>'>${pc+2 }</a>
                ...
            </c:if>
            <c:if test="${pc<tp }">
                <a href='<c:url value='filmpage.action?/moveTypePage&pc=${pc+1 }'/>'>下一页</a>
            </c:if>
            </c:if>
        </div>
    </div>
</div>
<link rel="stylesheet" href="css/filmtype.css">
<script>
    var nation;
    var type;
    var data;
    function aa(e) {
        $(e).toggleClass("is-current");
        $(e).siblings().removeClass("is-current");
        nation = e.text;
        loadSource();
    }
    function bb(e) {
        $(e).toggleClass("is-current");
        $(e).siblings().removeClass("is-current");
        type = e.text;
        loadSource();
    }

    function cc(e) {
        $(e).toggleClass("is-current");
        $(e).siblings().removeClass("is-current");
        data = e.text;
        loadSource();
    }

    function loadSource() {
        $.ajax({
            type: "post",
            url: "filmpage.action?/typefilm",
            data: {/*page: 2, size: 15,*/nation:nation,type: type, year: data},
            dataType: "json",
            success: function (result, status) {
                $("#elPage").remove();//el表达式分页标签移除
                $("#ul_movies li").remove();//把先前的li标签元素全部移除
                var data = eval(result);
                $("#size").attr("value", data.length);
                // var select="<option value='' selected='selected'>--请选择--</option>";;
                for (var i = 0; i < data.length; i++) {
                    var $li = $("<li class='library-main__item'></li>");
                    $li.append("<a  href='filmpage.action?/filmdetail&fid=" + data[i].id + "' target='_blank' class='picture'>" + "<img  src='" + data[i].image + "'alt='" + data[i].name + "'title='" + data[i].name + "'></a>");
                    $div = $("<div  class='info'></div>");
                    $div.append("<a href='filmpage.action?/filmdetail&fid=" + data[i].id + "'target='_blank' class='name'>" + data[i].name + "</a>");

                    $a_link = $("<a href='javascript:;' title='豆瓣评分' class='vote-number'></a>");
                    $a_link.append("<i class='icon-vote'></i><span class='vote-number-text'>" + data[i].ratingValue + "</span>");
                    $div.append($a_link);
                    $li.append($div);
                    $("#ul_movies").append($li);
                }
            }
        })
    }
</script>
</body>