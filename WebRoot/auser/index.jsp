<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	<link type="text/css" rel="stylesheet" href="<%=path %>/css/css.css" />
	<script type="text/javascript" language="javascript">    
       function out()
       {
           window.parent.location.href="<%=path %>/login.jsp";
       }
    </script> 
	
  </head>
  
<body>
<div class="header">
  <div class="top">
    <img class="logo" src="<%=path %>/images/logo.jpg" />
    <ul class="nav">
      <c:if test="${sessionScope.userType==0}"> 
	      <li><a href="<%=path %>/admin/userinfo/userPw.jsp" target="right1111" style="font-family: 微软雅黑">修改登陆密码</a></li>
	      <li><a href="<%=path %>/userMana.action" target="right1111" style="font-family: 微软雅黑">注册信息管理</a></li>
	  </c:if>
	  <c:if test="${sessionScope.userType==1}"> 
	      <li><a href="<%=path %>/auser/userinfo/userPw.jsp" target="right1111" style="font-family: 微软雅黑">个人信息管理</a></li>
	      <li><a href="<%=path %>/shouruMana.action" target="right1111" style="font-family: 微软雅黑">收入信息管理</a></li>
	      <li><a href="<%=path %>/xiaofeiMana.action" target="right1111" style="font-family: 微软雅黑">支出信息管理</a></li>
	      <li><a href="<%=path %>/touziMana.action" target="right1111" style="font-family: 微软雅黑">理财信息模块</a></li>
	      <li><a href="<%=path %>/auser/tongji/tongjiPre.jsp" target="right1111" style="font-family: 微软雅黑">收支分析模块</a></li>
	      <li><a href="<%=path %>/kaMana.action" target="right1111" style="font-family: 微软雅黑">银行存储模块</a></li>
	      <li><a href="<%=path %>/jiehuanMana.action" target="right1111" style="font-family: 微软雅黑">借钱还钱模块</a></li>
          <li><a href="<%=path %>/getSub.action" target="right1111" style="font-family: 微软雅黑">理财产品推荐</a></li>
	  </c:if>
    </ul>
  </div>
</div>


<div class="container">
  <div class="leftbar">
    <div class="lm01"> 
      <!--  <img class="peptx" src="<%=path %>/images/nophoto.gif" width="100" height="100"/>-->
      <c:if test="${sessionScope.userType==0}">
      <div class="pepdet">
        <p class="pepname">
			 欢迎您：系统管理员
        </p>
        <p><a href="#" onclick="out()" style="text-decoration: none" style="font-size: 20px;">退出系统</a> </p>
      </div>
      </c:if>
      
      
      <c:if test="${sessionScope.userType==1}">
      <div class="pepdet">
        <p class="pepname">
			 欢迎您：${sessionScope.user.userRealname }
        </p>
        <p><a href="#" onclick="out()" style="text-decoration: none" style="font-size: 20px;">退出系统</a> </p>
      </div>
      </c:if>
      
      
      <div class="clear"></div>
    </div>
    <div class="lm02">
      <div class="detail"><iframe src="<%=path %>/rili/rili.jsp" width="220" height="220" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″ scrolling=”no” ></iframe> </div>
    </div>
    <div class="lm03">
      <div class="detail"> <iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=99" width="160" height="36" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe> </div>
    </div>
  </div>
  
  <div>
      <iframe src="<%=path %>/admin/right.jsp" width="70%" height="1000" name="right1111" frameborder="0" scrolling="auto"></iframe></td>
  </div>
</div>

</body>
</html>
