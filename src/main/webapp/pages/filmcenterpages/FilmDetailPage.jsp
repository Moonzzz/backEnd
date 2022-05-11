<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
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
            <a href="filmpage.action?/movepage&pagename=types&type=all" target="_blank" class="">影视库 <span class="arrow"></span></a>
            <%--<a href="javascript:;">影视库</a>--%>
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
                <li class="layui-nav-item to-index"><a target="_blank"
                                                       href="filmpage.action?/movepage&pagename=writecomment&fId=${fdetail.id}">为此电影写影评</a>
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
<div style="margin-top: -30px">
    <div class="detail-info" style="margin-top: 0px">
        <%--中部电影影院效果开始--%>
        <div class="stage">
            <div class="stage-inner">
                <div class="table">

                </div>
                <div class="screen">

                </div>
                <div class="ceiling">

                </div>
                <div class="curtain curtant--left"></div>
                <div class="curtain curtant--right"></div>
                <div class="light">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <div class="light2">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <%-- <div class="desk">

                 </div>--%>
                <div class="desk">
                    <div class="article-vote">
                        <div class="vote-number is-more">
                            <span class="icon-vote-big"></span>
                            <span class="vote-number-text">${fdetail.ratingValue}</span>
                        </div>
                        <p class="article-vote__number">豆瓣评分(<span>${fdetail.count}</span>人投票)</p>
                    </div>
                </div>
                <div class="chair"></div>
                <div class="mask">
                </div>
                <a href="javascript:;" title="舞台开关" class="switch"></a>
                <%--电影详细信息开始--%>
                <div class="stage__content">
                    <div class="poster"><img alt="${fdetail.name}"
                                             src="${fdetail.image}">
                    </div>
                    <div class="detail">
                        <h2>${fdetail.name}</h2>
                        <div class="detail-content-list">
                            <ul>
                                <li>
                                    <span class="detail-content-list__title">日期：</span>
                                    <span class="text">${fdetail.datePublished}</span>
                                </li>
                                <li><span class="detail-content-list__title">导演：</span><span class="text">
                                  <c:forEach items="${directors}" var="li">
                                      ${li}/
                                  </c:forEach>
                            </span>
                                </li>
                                <li><span class="detail-content-list__title">主演：</span><span class="text">
                                <c:forEach items="${actors}" var="li">
                                    ${li}/
                                </c:forEach>
                            </span>
                                </li>
                                <li><span class="detail-content-list__title">剧情：</span><span
                                        class="text detail__info">${fdetail.description}</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <%--电影详细信息结束--%>
            </div>
        </div>
        <%--中部电影影院效果结束--%>
        <div class="article-inner">
            <%--相关电影影评--%>
            <div class="article-detail-content">
                <div class="article-list">
                    <div class="article-related">
                        <div style="width: 750px" class="article-heading">
                            <h2>相关影评<span class="number">${fn:length(cList)}</span></h2>
                        </div>
                        <div style="width: 750px" class="article-list-main">
                            <c:if test="${fn:length(cList) >0}">
                                <ul style="min-height: 322px;">
                                        <%--电影信息循环开始--%>
                                    <c:set var="pc" value="${param.pc eq null ? 1:param.pc}"></c:set>
                                    <c:forEach var="i" begin="${(pc-1)*2}"
                                               end="${pc*2-1 >= fn:length(cList) ? fn:length(cList)-1:pc*2-1}">
                                        <li class="article-list-box">
                                            <div class="article-image">
                                                <a href="comment.action?/readcomment&id=${cList[i].id}"
                                                   class="article-picture">
                                                    <img style="width: 180px;height: 120px;"
                                                         src="<%=filePath%>${cList[i].image}">
                                                </a>
                                            </div>
                                            <div class="article-des">
                                                <h3>
                                                    <a href="comment.action?/readcomment&id=${cList[i].id}"
                                                       class="article-name" target="_blank">
                                                        《${cList[i].fName}》
                                                    </a>
                                                </h3>
                                                <div class="text-article-detail">
                                                    <p style="overflow: hidden;text-overflow: ellipsis;">
                                                            ${cList[i].fDescrip}
                                                    </p>
                                                </div>
                                                <div class="article-des-info">
                                                    <div class="article-des-inner">
                                                        <a href="membercenter.action?/toPersonOut&mId=${cList[i].mId}"
                                                           rel="nofollow"
                                                           target="_blank" class="user-info">
                                                <span class="user-picture">
                                                 <img style="width: 24px;height: 24px;"
                                                      src="<%=filePath%>${cList[i].mImgsrc}"
                                                      alt="${cList[i].mName}">
                                                </span>
                                                            <span class="user-name">
                                                                    ${cList[i].mName}
                                                            </span>
                                                        </a>
                                                    </div>
                                                    <div class="article-des-inner">
                                                        <p class="view-number">
                                                            <i class="ico-article-view"></i>
                                                            <span>${cList[i].viewNum}</span><%--观看数量--%>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                    <div class="page">
                                        <div>
                                            <a>第${pc }页/共${tp }页</a>
                                            <c:if test="${pc>1 }">
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc-1 }'/>'>上一页</a>
                                            </c:if>
                                            <c:if test="${tp-pc>=2&&pc>2 }">
                                                ...
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc-2 }'/>'>${pc-2 }</a>
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc-1 }'/>'>${pc-1 }</a>
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc+1 }'/>'>${pc+1 }</a>
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc+2 }'/>'>${pc+2 }</a>
                                                ...
                                            </c:if>
                                            <c:if test="${pc<tp }">
                                                <a href='<c:url value='filmpage.action?/moveCommentPage&pc=${pc+1 }'/>'>下一页</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </ul>
                            </c:if>
                        </div>
                    </div>
                    <div id="comment_list"></div>
                </div>
                <%--相关电影推荐--%>
                <c:if test="${fn:length(smilFilms) >0}">
                    <div class="movie-related">
                        <div class="article-heading"><h2>同类型影片</h2><a href="javascript:" onclick="changeGroup()"
                                                                     class="link-change"><i class="layui-icon layui-icon-refresh" style="width: 15px;height: 15px; color: #a0a0a0;"></i></a>
                        </div>
                        <div class="movie-related-list">
                            <ul id="ul_similar">
                                <c:forEach items="${smilFilms}" var="fItem">
                                    <li class="movie-related-item">
                                        <div class="movie-related-picture"><a
                                                href="filmpage.action?/filmdetail&fid=${fItem.id}"
                                                target="_blank">
                                            <picture><source srcset="${fItem.image}"type="image/webp">
                                                <img alt="${fItem.name}"
                                                     src="${fItem.image}"
                                                     title="${fItem.name}"></picture>
                                        </a></div>
                                        <div class="movie-related-content"><h3><a
                                                href="filmpage.action?/filmdetail&fid=${fItem.id}"
                                                target="_blank">${fItem.name}</a>
                                        </h3><a href="javascript:;"
                                                class="vote-number"><span
                                                title="豆瓣评分" class="vote-number-text">
                                                &nbsp;&nbsp;${fItem.ratingValue}
                                        </span></a>
                                            <br><br>
                                            <p>类型：<span>${fItem.genre}</span></p>
                                            <p>上映：<span>${fItem.datePublished}</span></p></div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<input id="fId" type="hidden" value="${fdetail.id}">
</body>
<script type="text/javascript" src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>

<script>
    $(function(){
        $.ajax({
            type:"get"
            ,url:"<%=basePath%>comment/getHtmlByFilmId.action?pageNo=1&pageSize=10&filmId=${fdetail.id}"
            ,success:function (data) {
                $("#comment_list").html(data);
            }
        });
    });
    function changeGroup() {
        var fId = $("#fId").val();
        $.ajax({
            type: "post",
            url: "filmpage.action?/changeGroup",
            data: {fId: fId},
            dataType: "json",
            success: function (result, status) {
                $("#ul_similar  li").remove();//把先前的li标签元素全部擦除
                var data = eval(result);
                $("#size").attr("value", data.length);
                for (var i = 0; i < data.length; i++) {
                    var $li = $("<li class='movie-related-item'></li>");
                    var $div_1= $("<div class='movie-related-picture'></div>");
                    var $a_1 = $("<a href='filmpage.action?/filmdetail&fid="+data[i].id+"'target='_blank'></a>");
                    var $picture=$("<picture <source srcset='"+data[i].image+"'type='image/webp'></picture>");
                    var $img=$("<img alt='"+data[i].name+"'src='"+data[i].image+"'title='"+data[i].name+"'>");

                    $picture.append($img);
                    $a_1.append($picture);
                    $div_1.append($a_1);

                    var $div_2= $("<div class='movie-related-content'></div>");
                    var $h3=$("<h3><a href='filmpage.action?/filmdetail&fid="+data[i].id+"'target='_blank'>"+data[i].name+"</a></h3>");
                    var $a_2 = $("<a href='javascript:;' class='vote-number'><span title='豆瓣评分' class='vote-number-text'> &nbsp;&nbsp;"+data[i].ratingValue+"</span></a>");
                    var $p=$("<br><br><p>类型：<span>"+data[i].genre+"</span></p><p>上映：<span>"+data[i].datePublished+"</span></p>");

                    $div_2.append($h3).append($a_2).append($p);

                    $li.append($div_1).append($div_2);
                    $("#ul_similar").append($li);
                }
            }
        })
    }
</script>
<style>
    a {
        text-decoration: none
    }

    .article-my .user-pic, .ico-article-comment, .ico-article-load, .ico-article-praise:after, .ico-article-praise:before, .ico-article-video, .ico-article-view, .ico-change, .ico-nav-draft, .ico-nav-fav, .ico-nav-send, .recommend-slogan {
        background: url("<%=basePath%>images/view.png") no-repeat;
        background-size: 130px auto;
    }

    .icon-vote_search {
        width: 20px;
        height: 18px;
        display: inline-block;
        vertical-align: -6px;
        background: url("<%=basePath%>images/view.png") no-repeat;
        background-size: 130px auto;
    }

    .icon-vote_1 {
        display: inline-block;
        width: 16px;
        height: 16px;
        margin-left: 5px;
        background-size: 110px auto;
        background-position: -70px -100px;
        background: url("<%=basePath%>images/c.png") no-repeat;
    }

    .stage .article-vote .icon-vote, .stage .article-vote .icon-vote-big {
        background: url("<%=basePath%>images/c.png") no-repeat;
    }

    .stage .curtain {
        background: url("<%=basePath%>images/a.png") no-repeat;
    }

    .stage .ceiling, .stage .chair, .stage .table {
        background: url("<%=basePath%>images/table.png") no-repeat;
    }

    /*.stage .desk, */
    .stage .light2 span, .stage .light span, .stage .switch {
        background: url("<%=basePath%>images/plant.png") no-repeat;
    }

    .stage .mask {
        background: url("<%=basePath%>images/b.png") no-repeat;
    }
</style>
<%--
<link rel="stylesheet" href="css/filmsearch.css">
--%>
</html>