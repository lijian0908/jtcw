<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
           function shouruDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/shouruDel.action?id="+id;
               }
           }
           
           function shouruAdd()
           {
                 var url="<%=path %>/auser/shouru/shouruAdd.jsp";
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
					<td width="10%">时间</td>
					<td width="10%">金额</td>
					<td width="10%">成员</td>
					
					<td width="10%">备注</td>
					<td width="10%">操作</td>
		        </tr>	
				<s:iterator value="#request.shouruList" id="shouru" status="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#ss.index+1"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#shouru.shijian"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   <s:property value="#shouru.jine"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   <s:property value="#shouru.chengyuan"/>
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
					   <s:property value="#shouru.beizhu" escape="false"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					   <a href="#" onclick="shouruDel(<s:property value="#shouru.id"/>)" class="pn-loperator">删除</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加收入" style="width: 123px;" onclick="shouruAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
