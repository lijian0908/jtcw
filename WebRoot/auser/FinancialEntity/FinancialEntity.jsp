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
            function goPay(id)
            {
                window.location.href="<%=path %>/fiOrder.action?financialEntity.id="+id;
            }
            function replace(){
                window.location.href="<%=path %>/fiDelWeight.action";
			}

		</script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="12" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td>产品名称</td>
					<td>产品编码</td>
					<td>产品描述</td>
					<td>产品来源</td>
					<td>产品起售价格（分）</td>
					<td>产品最大购买价格（分）</td>
					<td>产品是否上架</td>
					<td>产品单位净值</td>
					<td>风险等级</td>
					<td>新增时间</td>
					<td>修改时间</td>
					<td>操作</td>
		        </tr>	
				<s:iterator value="#request.list" id="financialEntity">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialName"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#financialEntity.financialCode"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialDescribe"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialSource"/>
					</td>
					
					
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialPrice"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#financialEntity.financialMaxPrice"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialIfSale"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialUnit"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.financialLevel"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.createTime"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#financialEntity.updateTime"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="goPay(<s:property value="#financialEntity.id"/>)" class="pn-loperator">购买</a>
					</td>
				</tr>
				</s:iterator>
			</table>
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
				<tr>
					<td>
						<input type="button" value="重置推荐" style="width: 123px;" onclick="replace()" />
					</td>
				</tr>
			</table>


	</body>
</html>
