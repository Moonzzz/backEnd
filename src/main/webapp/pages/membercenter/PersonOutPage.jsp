<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() ;
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
    <link rel="stylesheet" href="css/personpage.css">
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
<body class="personal-wrap">
<div style="height: 100%;">
    <div class="personal" style="background-image: url(<%=basePath%>images/background.png;);">
        <div class="per-inner">
            <div class="per-side">
                <div class="per-info">
                    <a href=""
                       class="user-pic router-link-active" target="_blank" style="display: block;">
                        <img src="<%=filePath%>${mItem.imgsrc}"
                             style="width: 100%;">
                    </a>
                    <p class="user-info"><span class="user-name">${mItem.loginName}</span> <span class="ico-boy"></span>
                    </p>

                    <div class="per-att">
                        <a href="#/users/45842055/follows?entrypage=filmlib_detailpage&amp;entry=author" class="">
                            关注<span>${concernNum}</span></a> <i class="line"></i>
                        <a href="#/users/45842055/fans?entrypage=filmlib_detailpage&amp;entry=author" class="">
                            粉丝<span>${beConcernNum}</span></a>
                    </div>
                    <!---->
                </div>

                <div class="per-content" style="float: left;margin-left: 250px;margin-top: -230px">
                    <div class="per-tab">
                        <a href="#/users/45842055/?entrypage=filmlib_detailpage&amp;entry=author"
                           class="router-link-exact-active router-link-active cur">
                            全部动态
                        </a>
                    </div>
                    <div class="per-list">
                        <c:if test="${fn:length(cList) >0}">
                            <c:forEach items="${cList}" var="cItem">
                                <div class="per-list-box">

                                    <div class="interactive">
                                        <a href="comment.action?/readcomment&id=${cItem.id}"
                                           target="_blank" class="user-pic">
                                            <img src="<%=filePath%>${cItem.mImgsrc}">
                                        </a>
                                        <p class="txt-inter">
                                            <a href="#"
                                               target="_blank" class="user-name">
                                                    ${cItem.mName}
                                            </a>
                                            发布了影评
                                        </p>
                                        <!---->
                                    </div>
                                    <div class="per-opt">
                                        <p class="txt-opt">
                                            <!---->
                                                ${cItem.date}
                                            <!---->
                                        </p>
                                        <!---->
                                    </div>
                                    <div class="per-art">
                                        <div class="per-art-des">
                                            <a href="comment.action?/readcomment&id=${cItem.id}"
                                               target="_blank" class="art-tit">
                                                    ${cItem.title}
                                            </a>
                                            <div class="art-info">
                                                <div class="art-from">
                                                    <a href="#"
                                                       class="user-name router-link-active" target="_blank">
                                                            ${cItem.mName}
                                                    </a>
                                                    评《
                                                    <a href="filmpage.action?/filmdetail&fid=${cItem.fId}"
                                                       target="_blank" class="art-name">${cItem.fName}</a>》
                                                </div>
                                                <div class="art-popular">
                                                    <p title="979" class="txt-view"><span>${cItem.viewNum}</span>人浏览</p>
                                                    <a href=""
                                                       target="_blank" class="txt-com"><span class="ico-comment"></span>1
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="per-art-pic">
                                            <a href="comment.action?/readcomment&id=${cItem.id}"
                                               target="_blank" class="pic"
                                               style="background-image: url(<%=filePath%>${cItem.image};);"></a>
                                        </div>
                                        <!---->
                                        <!---->
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <div class="d3-load" style="height: 200px; display: none;">
                            <div class="d3-load-box"><span class="ico-d3-load"></span>
                                <p>加载中</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <a href="javascript:;" title="回到顶部" class="link-top" style="display: none;"></a>
            <div></div>
        </div>
    </div>
    <script>
        var _timemarks = [new Date()]
        if (typeof Promise === 'undefined') {
            document.write('<script src="//misc.xl9.xunlei.com/d/dist/static/js/polyfill.min.js"><\/script>')
        }
    </script>
</div>
</body>
</html>
