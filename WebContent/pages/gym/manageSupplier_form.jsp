<%@page import="com.entity.Supply"%>
<%@page import="java.util.List"%>
<%@page import="com.kzw.core.util.spring.SpringContextHolder"%>
<%@page import="com.service.ManageSupplierService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.kzw.com/mytag" prefix="k"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8">
<base href="<%=basePath%>pages/gym/">
<link rel="stylesheet" type="text/css" media="screen"
	href="../../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="../../css/your_style.css">
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
	                     		<td colspan="3">
	                     			<input name="supply.id" id="supplyId" type="hidden" value="${supply.id}">
	                     			<input id="supplyName" value="${supply.realname}" readonly="readonly" class="form-control" style="width:80%;display:inline;float:left;"/>
	                     			<a href="javascript:selectMember()" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> 选择</a>
	                     		</td>
	                     	</tr>
								<tr>
	                     			<td width="80">公司性质</td>
	                     			<td><input class="form-control" name="nature" type="text" value="${supply.nature}" /></td>
	                     		</tr>
	                     		<tr>
	                     			<td width="80">经营范围</td>
									<td ><input class="form-control" name="scope" type="text"
										value="${supply.scope}" /></td>
	                     		</tr>
	                     		
	                     			<tr>
	                     		<td width="90">电子邮箱</td>
	                     		<td><input class="form-control" name="email" type="text" value="${supply.email}"/></td>
	                
	                     	</tr>
	                     	<tr>
	                     	<td width="80">注册时间</td>
	                     			<td>	<input class="form-control" onfocus="WdatePicker({isShowClear:false,readOnly:true})" id="beginTime" name="beginTime" type="text" value="<fmt:formatDate value="${supply.beginTime}" pattern="yyyy-MM-dd"/>"/></td>
	                     			
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
	<!-- 部门选择器 -->
	<script src="../../js/libs/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		function selectMember() {
			// 传递的参数
			var id = $('#memberId').val();
			if(id) {
				top.layer.params = {
					selectId: id
				};
			}
		
			top.layer.open({
				type: 2,
				title: '供应商企业选择器',
				content: '<%=path%>/pages/gym/member_selector.jsp',
				area: ['420px', '620px'],
				maxmin: true,
				btn: ['确定', '取消'],
				yes: function(index, layero) {
					// 调用iframe页面中的函数
					var arr = layero.find('iframe')[0].contentWindow.getSelectMember();
					if(arr != null) {
						$('#memberId').val(arr[0]);
						$('#memberName').val(arr[1]);
						top.layer.close(index);	
						return;	
					}
					top.layer.msg('请选择供应商！', {icon: 5});		
				}
			});
		}
		
	
	</script>
</body>

</html>
