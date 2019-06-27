<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.model.FinancialEntity" %>
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
                document.financialEntity.submit();
            }
        </script>
	</head>
	<%
		Map map =(Map) ServletActionContext.getContext().get("request");
		FinancialEntity financialEntity = new FinancialEntity();
		try {
			financialEntity = (FinancialEntity) map.get("financialEntity");
		}catch (Exception e){

		}
	%>
	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
			<form method="post" action="<%=path %>/fiSaveOrUpdate.action" name="financialEntity">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>&nbsp;</span></td>
					    </tr>
					    <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品名称：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<input type="text" name="financialEntity.financialName" value="<%=financialEntity.getFinancialName() == null?"":financialEntity.getFinancialName()%>"  style="width: 180px;"/>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品编码：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<input type="number" name="financialEntity.financialCode" style="width: 180px;" value="<%=financialEntity.getFinancialCode() == null?"":financialEntity.getFinancialCode() %>"/>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品描述：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<textarea name="financialEntity.financialDescribe" rows="5" cols="5" style="width: 180px;" ><%=financialEntity.getFinancialDescribe() == null?"":financialEntity.getFinancialDescribe()%></textarea>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品来源：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<textarea name="financialEntity.financialSource" rows="5" cols="5" style="width: 180px;"><%=financialEntity.getFinancialSource()==null?"":financialEntity.getFinancialSource()%></textarea>
							</td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							<td width="10%" bgcolor="#FFFFFF" align="right">
								产品起售价格（分）：
							</td>
							<td width="90%" bgcolor="#FFFFFF" align="left">
								<input type="number" name="financialEntity.financialPrice" style="width: 180px;" value="<%=financialEntity.getFinancialPrice()%>"/>
							</td>
						</tr>
						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 产品最大购买价格（分）
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="number" name="financialEntity.financialMaxPrice" style="width: 180px;" value="<%=financialEntity.getFinancialMaxPrice()%>"/>
							 </td>
						 </tr>
						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 产品是否上架
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="radio" name="financialEntity.financialIfSale" checked value="0"/>是
								 <input type="radio" name="financialEntity.financialIfSale" value="1"/>否
							 </td>
						 </tr>
						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 产品单位净值
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="number" name="financialEntity.financialUnit" value="<%=financialEntity.getFinancialUnit()%>" style="width: 180px;"/>
							 </td>
						 </tr>
						 <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
							 <td width="10%" bgcolor="#FFFFFF" align="right">
								 风险等级
							 </td>
							 <td width="90%" bgcolor="#FFFFFF" align="left">
								 <input type="radio" name="financialEntity.financialLevel" checked value="A"/>A
								 <input type="radio" name="financialEntity.financialLevel" value="B"/>B
								 <input type="radio" name="financialEntity.financialLevel" value="C"/>C
								 <input type="radio" name="financialEntity.financialLevel" value="D"/>D
							 </td>
						 </tr>

						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="10%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="90%" bgcolor="#FFFFFF" align="left">
						       <input type="button" value="提交" onclick="c()"/>&nbsp; 
						       <input type="reset" value="重置"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
