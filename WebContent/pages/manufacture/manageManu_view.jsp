<%@ page language="java" pageEncoding="UTF-8"%>

<section id="widget-grid" class = "">
	<div class="well" style="margin-left:15px;">
	<form id="form-search" class="form-inline" method="post">
		<div class="form-group">
			企业名<input type="text" style="width:120px;" class="form-control" name="Q_LKS_realname">
		</div>
		<div class="form-group">
		    	经营范围<input type="text" style="width:120px;" class="form-control" name="Q_LKS_scope">
		  	</div>
		  	<div class="form-group">
		    	成立时间<input type="text" style="width:120px;" class="form-control" onclick="WdatePicker()" name="Q_LED_begintime">
		  	</div>
		  	<a id="btn-submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-search"></span> 查询</a>
		  	<a id="btn-clear" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> 清除</a>
		</form>
	</div>
	
	<div class="row">
		
		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<table id="jq_grid"></table>
				<div id="jq_paper"></div>
		</article>
		<!-- WIDGET END -->
		
	</div>
	
	
</section>
<script type="text/javascript">

	var grid   = $('#jq_grid');
	var searchForm = $('#form-search');
	var submitBtn  =$('#btn-submit');
	var clearBtn   = $('#btn-clear');
	$(function() {
		// JS加载完成之后
		grid.jqGrid({
			height: $(document).height() - 300,
			url: 'manageManu/list',
			caption: '<i class="glyphicon glyphicon-list"></i>生产商企业信息列表',
			colModel: [
				{label: 'ID', name: 'id', hidden: true, key: true},
				{label: '操作', name: 'id', width: 30, formatter: function(v) {
					return '<a href="javascript:editfunc('+v+')" class="btn btn-primary btn-xs">编辑</a>' +
							'<a href="javascript:delfunc('+v+')" style="margin-left:10px;" class="btn btn-danger btn-xs">删除</a>';			
				}},
				{label: '企业名', name: 'realname', width: 40, editable: true},
				{label: '成立时间', name: 'begintime', width: 25},
				{label: '电子邮箱', name: 'email', width: 35},
				{label: '公司性质', name: 'nature', width: 15},
				{label: '经营范围', name: 'scope', width: 30,},
				{label: '供应商品', name: 'material.materialName', width: 30,},

			],
			pager: '#jq_paper',
			multiboxonly: true,
			ondblClickRow: editfunc
		});
		grid.navGrid('#jq_paper',
			{addfunc: addfunc, editfunc: editfunc, delfunc:delfunc, search: false,
			addtext:'增加', edittext:'编辑', deltext:'删除', refreshtext:'刷新'}
		);
		
		
		// 查询
		submitBtn.click(function() {
			var params = searchForm.serializeObject();
			var postData = grid.jqGrid('getGridParam', 'postData') || {};
			$.extend(postData, params);
			grid.jqGrid('setGridParam', {postData: postData}).trigger("reloadGrid", [{page: 1}]);
		});
		// 清除
		clearBtn.click(function() {
			searchForm[0].reset();
		});
		
		
		// "增加"弹出框
		function addfunc() {
			layer.open({
				type: 2,
				title: '新增客户',
				area: ['500px', '400px'],
				maxmin: true,
			    content: ['pages/manufacture/manageManu_form.jsp', 'yes'],
			    btn: ['保存', '关闭'],
			    btn1: function(index, layero) {
			    	var form = layer.getChildFrame('form', index);
			    	$.get('./manageManu/save', form.serialize(), function(json){
			    		//刷新表格
			    
			    		if(json.success) {
			    			grid.trigger("reloadGrid", [{page: 1}]);
			    			layer.msg('保存成功', {icon: 1});
			    		} else {
			    			layer.msg('保存失败', {icon: 2});
			    		}
			    	}, 'json');
			    }
			});		
		}
		// end jquery
	});
	
	// "编辑"弹出框
	function editfunc(rowid) {
		//获得编辑行, 只能选中一行
		layer.open({
			type: 2,
			title: '客户信息修改',
			area: ['500px', '350px'],
			maxmin: true,
		    content: './manageManu/get/' + rowid,
		    btn: ['保存', '关闭'],
		    btn1: function(index, layero) {
		    	var form = layer.getChildFrame('form', index);
		    	$.post('./manageManu/save', form.serialize(), function(json){
		    		if(json.success) {
		    			grid.trigger("reloadGrid", [{page: 1}]);
		    			layer.msg('保存成功', {icon: 1});
		    		} else {
		    			layer.msg('保存失败', {icon: 2});
		    		}		    		
		    	}, 'json');
		    }
		});		
	}
	
	// "删除"功能
	function delfunc(rowid) {
		layer.confirm('确定删除该条记录吗？', {icon:3, title:'温馨提示'}, function(index) {
			$.getJSON('./manageManu/del', {ids: rowid+''}, function(json) {
				if(json.success) {
					grid.trigger("reloadGrid", [{page: 1}]);
	    			layer.msg(json.text, {icon: 1});		
				} else {
					//json.text 获取Msg.text
					layer.msg(json.text, {icon: 2});					
				}
			});
			layer.close(index);
		});
	}
	
</script>