<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.model.FinancialEntity" %>
<%@ page import="com.model.OrdersEntity" %>
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
        <script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script> 
        <script language="javascript">
            function c()
            {
                document.orders.submit();
            }
        </script>
	</head>
	<%
		Map map =(Map) ServletActionContext.getContext().get("request");
		OrdersEntity ordersEntity = new OrdersEntity();
		try {
			ordersEntity = (OrdersEntity) map.get("order");
		}catch (Exception e){

		}
	%>
	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
			<form method="post" action="<%=path %>/order.action" name="orders">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 订单编号：
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="text" name="ordersEntity.orderNum" value="<%=ordersEntity.getOrderNum() == null?"":ordersEntity.getOrderNum() %>"  style="width: 180px;"/>
								 <input type="hidden" name="ordersEntity.product.id" value="<%=ordersEntity.getProduct() == null?"":ordersEntity.getProduct().getId()%>"/>
							 </td>
						 </tr>
					    <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品名称
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<input type="text" name="ordersEntity.product.financialName" value="<%=ordersEntity.getProduct()== null?"":ordersEntity.getProduct().getFinancialName()%>"  style="width: 180px;"/>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品编码：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<input type="number" name="ordersEntity.product.financialCode" style="width: 180px;" value="<%=ordersEntity.getProduct() == null?"":ordersEntity.getProduct().getFinancialCode() %>"/>
							</td>
						</tr>

						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 订单金额(分)
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="number" min="100" max="100000000" name="ordersEntity.orderAmount" style="width: 180px;" value="<%=ordersEntity.getOrderAmount() == null ?"":ordersEntity.getOrderAmount()%>"/>
							 </td>
						 </tr>

						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="10%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="90%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="购买" onclick="c()"/>&nbsp;
						       <input type="reset" value="重置"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
