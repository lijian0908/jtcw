<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
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
        <link rel="stylesheet" href="<%=path %>/jquery/ui.datepicker.css" type="text/css" media="screen"  charset="utf-8" />
	
		<script src="<%=path %>/jquery/jquery-1.2.6.js" type="text/javascript" charset="utf-8"></script>	
		<script src="<%=path %>/jquery/ui.datepicker.js" type="text/javascript" charset="utf-8"></script>	
		<script src="<%=path %>/jquery/ui.datepicker-zh-CN.js" type="text/javascript" charset="utf-8"></script>

		<script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
		<script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
		<script src="https://code.highcharts.com.cn/highcharts/modules/oldie.js"></script>
		<script src="https://code.highcharts.com.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<style type="text/css">
		.container{
			float: left;
			width: 500px;
		}
	</style>

	</head>
	<body leftmargin="2" topmargin="9" background='<%=path %>/img/allbg.gif'>
	<div style="margin: 30px" width="10%" >
		选择年份： <select id="se">
					<option value ="2025">2025</option>
					<option value ="2024">2024</option>
					<option value="2023">2023</option>
					<option value="2022">2022</option>
					<option value="2021">2021</option>
					<option value="2020">2020</option>
					<option value="2019" selected>2019</option>
					<option value="2018">2018</option>
					<option value="2017">2017</option>
					<option value="2016">2016</option>
					<option value="2015">2015</option>
					<option value="2014">2014</option>
					<option value="2013">2013</option>
					<option value="2012">2012</option>
					<option value="2011">2011</option>
					<option value="2010">2010</option>
				</select>

		选择月份： <select id="se1">
		<option value ="01">一月</option>
		<option value ="02">二月</option>
		<option value="03">三月</option>
		<option value="04">四月</option>
		<option value="05">五月</option>
		<option value="06">六月</option>
		<option value="07" selected>七月</option>
		<option value="08">八月</option>
		<option value="09">九月</option>
		<option value="10">十月</option>
		<option value="11">十一月</option>
		<option value="12">十二月</option>
		</select>
	</div>
	</div>
	<div class="container"> <div id="container1" style="max-width:500px;height:400px"></div></div>
	<div class="container"> <div id="container2" style="max-width:500px;height:400px"></div></div>
	<div id="container" style="min-width:400px;height:400px"></div>
	<script>

        $(document).ready(function(){
            var chart  =  null
			var chart1 = null
			a();
            $("#se").change(function () {
				a();
            })
            $("#se1").change(function () {
                a();
            })
			function a(){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    async: false,
					data:{
                        "year": $("#se").children('option:selected').val(),
						"month":$("#se1").children('option:selected').val()
					},
                    url: "${contextPath}/tongjiRes.action",
                    success : function(data){
                        chart = new Highcharts.chart('container', {
                            chart: {
                                type: 'line'
                            },
                            title: {
                                text: '月收支分析'
                            },
                            subtitle: {
                                text: ''
                            },
                            xAxis: {
                                categories: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
                            },
                            yAxis: {
                                title: {
                                    text: '人民币（¥）'
                                }
                            },
                            plotOptions: {
                                line: {
                                    dataLabels: {
                                        // 开启数据标签
                                        enabled: true
                                    },
                                    // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                                    enableMouseTracking: false
                                }
                            },
                            series: [{
                                name: '收入',
                            }, {
                                name: '支出',
                            }]
                        });
                        data = eval('('+data+')')
                        chart.series[0].setData(data.shouList);
                        chart.series[1].setData(data.zhiList) ;

                        $("#container1").highcharts({
                            chart: {
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false,
                                type: 'pie'
                            },
                            title: {
                                text: '收入占比'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: false
                                    },
                                    showInLegend: true
                                }
                            },
                            series: [{
                                name: '收入占比',
                                colorByPoint: true,
                                data:data.shouru2
                            }]
                        });

                        $("#container2").highcharts({
                            chart: {
                                plotBackgroundColor: null,
                                plotBorderWidth: null,
                                plotShadow: false,
                                type: 'pie'
                            },
                            title: {
                                text: '支出占比'
                            },
                            tooltip: {
                                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                            },
                            plotOptions: {
                                pie: {
                                    allowPointSelect: true,
                                    cursor: 'pointer',
                                    dataLabels: {
                                        enabled: true,
                                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                        style: {
                                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                        }
                                    }
                                }
                            },
                            series: [{
                                name: '支出占比',
                                colorByPoint: true,
                                data:data.xiaofei2
                            }]
                        });


                    }
                })
			}

		})
	</script>
   </body>
</html>
