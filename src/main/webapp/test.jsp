<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
<body>

<div class="fly-header layui-bg-black">
      
    <div class="layui-container">
            <a class="fly-logo"  href="{:U('device/getinfo')}">
              <img src="__RES__/images/logo.png"  alt="layui">
            </a>
            

            
        <ul class="layui-nav fly-nav-user">
                  <!-- 未登入的状态 -->
                  
            <li class="layui-nav-item">
                        <a class="iconfont icon-touxiang layui-hide-xs"  href="user/login.html"></a>
                      
            </li>
                  
            <li class="layui-nav-item">
                        <a href="{:U('device/login')}">登入</a>
                      
            </li>
                  
            <li class="layui-nav-item">
                        <a href="javascript::void;">注册</a>
                      
            </li>
                  
                
        </ul>
          
    </div>
</div>

<div class="layui-container fly-marginTop">
      
    <div class="fly-panel fly-panel-user"  pad20>
            
        <div class="layui-tab layui-tab-brief"  lay-filter="user">
                  
            <ul class="layui-tab-title">
                        
                <li><a href="{:U('device/login')}">登入</a></li>
                        
                <li class="layui-this">注册</li>
                      
            </ul>
                  
            <div class="layui-form layui-tab-content"  id="LAY_ucm"  style="padding: 20px 0;">
                        
                <div class="layui-tab-item layui-show">
                              
                    <div class="layui-form layui-form-pane">
                                    
                        <form method="post">
                                          
                            <div class="layui-form-item">
                                              <label for="L_username"  class="layui-form-label">手机</label>
                                              
                                <div class="layui-input-inline">
                                                  <input type="text"  id="signuppassdata"  name="phone"
                                                          required lay-verify="required"  autocomplete="off"
                                                          class="layui-input">
                                                  
                                </div>
                                              
                            </div>

                                          
                            <div class="layui-form-item">
                                                <label for="L_username"  class="layui-form-label">卡号</label>
                                                
                                <div class="layui-input-inline">
                                                      <input type="text"  id="cardno"  name="cardno"
                                                               required lay-verify="required"  autocomplete="off"
                                                              class="layui-input">
                                                    
                                </div>
                                              
                            </div>


                                          
                            <div class="layui-form-item">
                                                
                                                
                                <button class="layui-btn"  type="submit"  lay-filter="reg"  lay-submit=""
                                         id="signuppass"  >立即注册
                                </button>

                                              
                            </div>
                                          
                                        
                        </form>
                                  
                    </div>
                            
                </div>
                      
            </div>
                
        </div>
          
    </div>

</div>
<script>

    //监听提交
    layui.use('form', function () {
        var form = layui.form;
        form.on('submit(reg)', function (data) {
            // layer.msg(JSON.stringify(data.field));//弹出json格式所有表单的值
            var phone = data.field.phone;
            var cardno = data.field.cardno;
            if (phone.length != 11) {
                layer.msg('请输入有效的手机号码', {icon: 2});
                return false;
            }
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if (!myreg.test(phone)) {
                layer.msg('请输入有效的手机号码', {icon: 2});
                return false;
            }
            if (cardno.length != 8) {
                layer.msg('请输入有效的卡片内码！', {icon: 2});
                return false;
            }
            $.ajax({
                type: "POST",
                url: "index.php?m=Home&c=Device&a=ajax_reg",
                data: "phone=" + phone + "&cardno=" + cardno,
                dataType: "json",
                success: function (data) {
                    if (data.status == 'yes') {
                        layer.msg('注册成功！', {icon: 1});
                        var url = "{:U('device/getinfo')}"; //成功跳转的url
                        setTimeout(window.location.href = url, 2000);
                    } else {
                        layer.msg(data.msg, {icon: 2});
                    }
                },
            });
            return false;
        });
    });

</script>

</body>
</html>
