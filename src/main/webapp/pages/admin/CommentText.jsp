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
                        <p class="vjs-no-js"> To view this video please enable JavaScript, and consider upgrading to a web
                            browser that
                            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>
                    </video>
                </div>
            </c:if>
            <%--文本内容开始--%>
            <div class="comment">
                ${cItem.content}
            </div>
            <%--文本内容结束--%>
            <div style="height: 500px">

            </div>
        </div>
</body>
</html>
