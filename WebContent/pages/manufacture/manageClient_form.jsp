<%@page import="com.entity.Client"%>
<%@page import="java.util.List"%>
<%@page import="com.kzw.core.util.spring.SpringContextHolder"%>
<%@page import="com.service.ManageClientService"%>
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
		<base href="<%=basePath%>pages/manufacture/">
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
							<%-- <input type="hidden" name="id" value="${supply.id}"> --%>

							<table class="bs-table" >
								<tr>
	                     		<td width="70">企业名</td>
	                     		<td >	
										<select class="form-control" name="realname">
										<c:forEach var="clientName" items="${clientName}">
										<option>${clientName}</option>
										</c:forEach>
									
									</select>	
	                     			 <input name="id" id="clientId" style="display:none" value="${client.id}"> 
	                     			<%-- <input id="clientName" value="${client.realname}" class="form-control" style="width:80%;display:inline;float:left;"/> --%>
	                     			<!-- <a href="javascript:selectMember()" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> 选择</a> -->
	                     		</td>
	                     	</tr>
								<tr>
	                     			<td width="80">公司性质</td>
	                     			<td><input class="form-control" name="nature" type="text" value="${client.nature}" /></td>
	                     		</tr>
	                     		<tr>
	                     			<td width="80">经营范围</td>
									<td ><input class="form-control" name="scope" type="text"
										value="${client.scope}" /></td>
	                     		</tr>
	                     		
	                     			<tr>
	                     		<td width="90">电子邮箱</td>
	                     		<td><input class="form-control" name="email" type="text" value="${client.email}"/></td>
	                
	                     	</tr>
	                     	<tr>
	                     	<td width="80">注册时间</td>
	                     			<td>	<input class="form-control" onfocus="WdatePicker({isShowClear:false,readOnly:true})" id="begintime" name="beginTime" type="text" value="<fmt:formatDate value="${client.begintime}" pattern="yyyy-MM-dd"/>"/></td>
	                     			
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
				content: '<%=path%>/pages/gym/member_selector.jsp',
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
