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
		<script src="<%=path %>/jquery/jquery-1.2.6.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="12" background="<%=path %>/img/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td>题目号</td>
					<td>题目名称</td>
					<td>题目选项</td>
		        </tr>
				<%int i =0;%>
				<s:iterator value="#request.list" id="SubjectEntity">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<s:property value="#SubjectEntity.id"/>
						<% i++;%>
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <s:property value="#SubjectEntity.subjectName"/>
					</td>
					<td bgcolor="#FFFFFF" align="left">
						A:<input type="radio" class="radio" name="option<%=i%>" value="A"/><s:property value="#SubjectEntity.optionA"/><br/>
						B:<input type="radio" class="radio" name="option<%=i%>" value="B"/><s:property value="#SubjectEntity.optionB"/><br/>
						C:<input type="radio" class="radio" name="option<%=i%>" value="C"/><s:property value="#SubjectEntity.optionC"/><br/>
						D:<input type="radio" class="radio" name="option<%=i%>" value="D"/><s:property value="#SubjectEntity.optionD"/><br/>
					</td>
				</tr>
				</s:iterator>
			</table>

			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
				<tr>
					<td>
						<input id="button" type="button" value="点击提交" style="width: 123px;" onclick="b()" />
					</td>
				</tr>
			</table>

			<form method="post" action="<%=path %>/setSub.action" name="setsub">
				<input id="A" type="hidden" name="A"/>
				<input id="B" type="hidden" name="B"/>
				<input id="C" type="hidden" name="C"/>
				<input id="D" type="hidden" name="D"/>
			</form>
		<script>
			function b(){
                var a =0;
				var b =0;
				var c =0;
				var d =0;
                $("input[class='radio']:checked").each(function(){
					if("A" === $(this).val() ){
					    a=a+1;
					}
                    if("B" === $(this).val() ){
                        b=b+1;
                    }
                    if("C" === $(this).val() ){
                        c=c+1;
                    }
                    if("D" === $(this).val() ){
                        d=d+1;
                    }

                })

				$("#A").val(a)
				$("#B").val(b)
				$("#C").val(c)
				$("#D").val(d)

				console.log($("#A").val())
                document.setsub.submit();
			}


		</script>

	</body>
</html>
