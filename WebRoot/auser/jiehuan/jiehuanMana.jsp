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
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function jiehuanDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/jiehuanDel.action?id="+id;
               }
           }
           
           function jiehuanAdd()
           {
                 var url="<%=path %>/auser/jiehuan/jiehuanAdd.jsp";
				 window.location.href=url;
           }
           
           function huan(id)
           {
                 var url="<%=path %>/huan.action?id="+id;
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="25" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="5%">序号</td>
					<td width="10%">类型</td>
					<td width="10%">时间</td>
					<td width="10%">金额</td>
					
					<td width="10%">对方</td>
					<td width="10%">备注信息</td>
					<td width="10%">是否还</td>
					<td width="10%">还时间</td>
					
					
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.jiehuanList}" var="jiehuan" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${ss.index+1 }
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${jiehuan.leixing}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.shijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.jine}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.duifang}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.shifouhuan}
					   &nbsp; &nbsp;
					   <c:if test="${jiehuan.shifouhuan=='否'}">
					      <a href="#" onclick="huan(${jiehuan.id})" style="color: red">设置还钱</a>
					   </c:if>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   ${jiehuan.huanshi}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
					   <input type="button" value="删除"  onclick="jiehuanDel(${jiehuan.id})"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加借钱还钱" style="width: 123px;" onclick="jiehuanAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
