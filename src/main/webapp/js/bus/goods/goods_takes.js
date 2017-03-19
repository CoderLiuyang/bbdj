$(function () {
    $("#jqGrid").jqGrid({
        url: '../goods/list',
        datatype: "json",
        cellEdit: true,
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true ,hidden:true},
			{ label: '快递单号', name: 'goodsNo', width: 120 }, 			
			{ label: '波次号', name: 'handoverNo', width: 80 ,hidden:true}, 			
			{ label: '包装类型ID', name: 'packagesId', width: 80,hidden:true }, 			
			{ label: '包装类型', name: 'packages', width: 80 ,hidden:true}, 			
			{ label: '收件人ID', name: 'appuserId', width: 80 ,hidden:true}, 			
			{ label: '收件人姓名', name: 'userName', width: 80 }, 			
			{ label: '收件人电话', name: 'tel', width: 100 }, 			
			{ label: '状态', name: 'status', width: 80 ,formatter:function(v,o,r){
				// 0-未通知 1-已通知未通知 2-签收 3-延迟签收 4-拒收退货  5-删除 
				var status = '未通知';
				var style = 'label-default';
				switch(v){
				case 0:
					status='未通知';
					break;
				case 1:
					status='已通知';
					style = 'label-info';
					break;
				case 2:
					status='已签收';
					style = 'label-success';
					break;
				case 3:
					status='延迟签收';
					style = 'label-primary';
					break;
				case 4:
					style = "label-warning";
					status='拒收退货';
					break;
				case 5:
					style = "label-danger";
					status='已删除';
					break;
				}
				return '<span class="label '+style+'">'+status+'</span>';
			}},			
			{ label: '存放货架', name: 'shelves', width: 80 ,hidden:true}, 			
			{ label: '快递公司ID', name: 'expressId', width: 80 ,hidden:true}, 			
			{ label: '快递公司', name: 'express', width: 120 }, 			
			{ label: '快递员ID', name: 'courierId', width: 80 ,hidden:true}, 			
			{ label: '快递员姓名', name: 'courierName', width: 80 ,hidden:true}, 			
			{ label: '物品', name: 'goods', width: 80 ,hidden:true}, 			
			//{ label: '重量', name: 'weight', width: 80 }, 			
			{ label: '图片', name: 'img', width: 80 ,hidden:true}, 			
			{ label: '收件时间', name: 'createtime', width: 120 }, 			
			{ label: '取件时间', name: 'gettime', width: 120 }, 			
			{ label: '取件人', name: 'getname', width: 80 }, 			
			{ label: '规格', name: 'spec', width: 80 ,hidden:true}, 			
			{ label: '延迟取件天数', name: 'price', width: 80 }, 			
			{ label: '是否破损', name: 'isBroken', width: 80 ,formatter:function(value, options, row){
				//0-没有 1-破损
				return value === 0 ? 
						'<span class="label label-success">正常</span>' : 
						'<span class="label label-danger">破损</span>';
			},hidden:true}, 			
			{ label: '住址', name: 'houseNumber', width: 80 },
			{ label: '收件备注', name: 'receivenote', width: 80,hidden:true}, 			
			{ label: '取件备注', name: 'getnote', width: 240,editable:true}, 	
			{ label: '操作人ID', name: 'operationId', width: 80,hidden:true},
			{ label: '操作人姓名', name: 'operationName', width: 80,hidden:true},
			{ label: '修改人ID', name: 'updateId', width: 80,hidden:true},
			{ label: '修改人姓名', name: 'updateName', width: 80,hidden:true},
			{ label: '修改时间', name: 'updateDate', width: 80,hidden:true},
			{ label: '操作', width: 160,formatter:function(v,o,r){
			var result = "";
			//状态 0-未通知 1-已通知未通知 2-签收 3-延迟签收 4-拒收退货
			var str1 = '<input type="button" class="btn btn-primary btn-xs" onClick="updateStatus(1,'+r.id+','+r.price+')" value="通知取件"/>&nbsp;';
			var str2 = '<input type="button" class="btn btn-success btn-xs" onClick="updateStatus(2,'+r.id+','+r.price+')" value="签收"/>&nbsp;';
			/*var str3 = '<input type="button" class="btn btn-primary btn-xs" onClick="updateStatus(3,'+r.id+','+r.price+')" value="延迟签收"/>&nbsp;';*/
			var str4 = '<input type="button" class="btn btn-primary btn-xs" onClick="updateStatus(4,'+r.id+','+r.price+')" value="拒收退货"/>&nbsp;';
			switch (r.status) {
			case 0:
				// 未通知，可删除
				result+=str1;
				break;
			case 1:
				result+=str2;
				result+=str4;
				break;
			case 2:
				result+=str4;
				//result+=str3;
				break;
			case 3:
				result+=str2;
				result+=str4;
				break;
			case 4:
				break;
			default:
				//result+=str1;
				break;
			}
			return result;
		}}		
        ],
		viewrecords: true,
        height: 400,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        },
        ondblClickRow:function(rowid,iRow,iCol){
        	var rowData = $("#jqGrid").jqGrid('getRowData',rowid);
        	if(rowData.status.indexOf("未通知")>=0||rowData.status.indexOf("已通知")>=0){
        		notice(rowid,0);
        	}
        },
        //editurl:'../goods/receiving',
        cellurl:'../goods/note',
        cellsubmit: "remote",
        beforeSubmitCell : function(rowid,cellname,value,iRow,iCol){
        	return JSON.stringify({'id':rowid,cellname:value});
        },
        beforeSaveCell:function(rowid, cellname, value, iRow, iCol){
        	//return JSON.stringify({'id':rowid,cellname:value})
        },
        afterEditCell:function(rowid, cellname, value, iRow, iCol){
        	//var data = {'id':rowid,cellname:value};
            //$("#jqGrid").jqGrid("setRowData", rowid, data, "");
        },
        serializeCellData: function(postdata){
        	return JSON.stringify({'id':postdata.id,'getnote':postdata.getnote});
        }
    });
    //快递公司
    var express_select2 = $('#label_express').select2({
    	data:loadDict('express','快递公司')
	});
    //状态
    var statu_select2 = $('#label_status').select2({
    	placeholder:"全部状态"
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		tel:'',
		goodsNo:'',
		status:'',
		userName:'',
		houseNumber:'',
		express:''
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			location.href = "goods_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../goods/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		query:function(){
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'tel':$('#label_tel').val(),
					'userName':escape($('#label_uname').val()),
					'houseNumber':escape($('#label_house').val()),
					'goodsNo':$('#label_goodsNo').val(),
					'expressId':$("#label_express").select2('val')==-1?'':$("#label_express").select2('val'),
					'status':$('#label_status').select2('val')==-1?'':$("#label_status").select2('val'),
					'st':$('#label_st').val(),
					'et':$('#label_et').val()
				},
				page : 1
			}).trigger("reloadGrid");
		},
		print : function() {
			//打印
		},
		receivingBatch:function(){
			var ids = getSelectedRows();
			var idStr="";
			if(ids == null){
				return ;
			}
			var totalPrice = 0;
			for(var i = 0;i<ids.length;i++){
				var rowData = $("#jqGrid").jqGrid('getRowData',ids[i]);
				if(rowData.status.indexOf("已签收")>=0){
					continue;
				}
				if(rowData.price=='0'){
					rowData.price = "-1";
				}
				idStr+=ids[i]+",";
				totalPrice+=parseInt(rowData.price);
			}
			idStr = idStr.substring(0,idStr.length-1);
			if(idStr!=''){
				receiving(idStr, totalPrice);
			}else{
				alert("您选择的快件信息已签收");
			}
//			totalPrice = (totalPrice>0)?"扣除"+totalPrice:"增加"+Math.abs(totalPrice);
//			layer.prompt({
//				title : '请输入签收人,'+totalPrice+"积分",
//				formType : 0
//			}, function(text, index) {
//				layer.close(index);
//				$.ajax({
//				    url: "../goods/receivingBatch",
//				    data: {
//				    	ids:ids,
//				    	getname:text
//				    },
//				    success: function(r){
//						if(r.code == 0){
//							alert('操作成功', function(index){
//								$("#jqGrid").trigger("reloadGrid");
//							});
//						}else{
//							alert(r.msg);
//						}
//					}
//				});
//			});
		}
	}
});
function updateStatus(v, i,p) {
	if (p == '0' || p == 0) {
		p = -1
	}
	if(v==2){//签收
		receiving(i, p);
	}else if(v==1){//通知取件
		notice(i, p);
	}else{
		// 更改状态
		$.ajax({
			url : '../goods/receiving',
			data : {
				ids : i,
				getname : '',
				getnote:'',
				status : v
			},
			success : function(data) {
				$("#jqGrid").trigger("reloadGrid");
			}
		});
	}
}
function receiving(id,p){
	//取件
	layer.open({
		  type: 2,
		  title: '取件',
		  maxmin: true,
		  area: ['550px', '40%'],
		  content: ['goods_get.html?ids='+id+"&price="+p, 'no'], //iframe的url，no代表不显示滚动条
		  end:function(){
			  //刷新表格
			  $("#jqGrid").jqGrid('setGridParam', {
					page : 1
				}).trigger("reloadGrid");
		  }
		});
}
function notice(id,p){
	//取件
	layer.open({
		  type: 2,
		  title: '通知取件',
		  maxmin: true,
		  area: ['550px', '60%'],
		  content: ['goods_note.html?id='+id, 'no'], //iframe的url，no代表不显示滚动条
		  end:function(){
			  //刷新表格
			  $("#jqGrid").jqGrid('setGridParam', {
					page : 1
				}).trigger("reloadGrid");
		  }
		});
}