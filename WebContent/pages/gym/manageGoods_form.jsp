<%@page import="com.entity.Goods"%>
<%@page import="java.util.List"%>
<%@page import="com.kzw.core.util.spring.SpringContextHolder"%>
<%@page import="com.service.GoodsService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.kzw.com/mytag" prefix="k"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html lang="en-us">	
	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>pages/gym/">
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="../../css/your_style.css">
	</head>

	<!--
	The ID "widget-grid" will start to initialize all widgets below 
	You do not need to use widgets if you dont want to. Simply remove 
	the <section></section> and you can use wells or panels instead 
	-->
<body>

<div class="container">
	<!-- row -->
	<div class="row">
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div>
				<div class="widget-body">
					<form class="form-horizontal">
						<input type="hidden" name="id" value="${goods.id}">
						
	                     <table class="bs-table">
<tr>
									<td width="90">商品</td>
									<td><input class="form-control" name="goodsName" type="text" value="${goods.goodsName }"></td>
								</tr>
								<tr>
									<td width="90">供应时间</td>
									<td><input class="form-control" name="goodsDate" type="date" value="${goods.goodsDate }"></td>
								</tr>
								<tr>
									<td width="90">供应数量</td>
									<td><input class="form-control" name="goodsCount" type="text" value="${goods.goodsCount }"></td>
								</tr> 
								<%-- <tr>
									<td width="90">生产商企业ID</td>
									<td><input class="form-control" name="manufactureId" type="text" readOnly="true" value="${goods.manufactureID }"></td>
								</tr> --%>
								<tr>
									<td width="90">生产商企业名</td>
									<td><input class="form-control" name="manufactureName" type="text" readOnly="true" value="${goods.manufactureName }"></td>
								</tr>
								<%-- <tr>
									<td width="90">客户企业ID</td>
									<td><input class="form-control" name="clientId" type="text" value="${goods.clientID }"></td>
								</tr> --%>
								<tr>
									<td width="90">客户企业名</td>
									<td><input class="form-control" name="clientName" type="text" value="${goods.clientName }"></td>
								</tr>
							
	                    
	                  
	                   
	                     </table>
						
					</form>
	
				</div>
			</div>
	
		</article>
		<!-- WIDGET END -->
		
	</div>
	<!-- end row -->
</div>
		<!-- 时间选择器 -->
		<script src="../../js/plugin/My97DatePicker/WdatePicker.js"></script>
		<!-- 附件选择器 -->
	<script src="../../js/libs/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		
		// 选择附件
		function selectFileAttach() {
			top.layer.open({
				type: 2,
				title: '文件选择器',
				content: '<%=path%>/pages/selector/fileAttach_selector_pic.jsp',
				area: ['800px', '600px'],
				maxmin: true,
				btn: ['确定', '取消'],
				yes: function(index, layero) {
					// 获得值
					var arr = layero.find('iframe')[0].contentWindow.getUrl();
					if(arr.length == 0) {
						top.layer.msg('请选择一张图片');
						return;
					}
					$('#imgUrl').val(arr[0]);
					$('#imgS').attr('src', arr[0]);
					top.layer.close(index);
				}
			});	
		}
	
	</script>
	</body>

</html>
