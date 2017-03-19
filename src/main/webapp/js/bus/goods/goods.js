var sidx;
var order="desc";
$(function () {
    $("#jqGrid").jqGrid({
        url: '../goods/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true,hidden:true },
			{ label: '波次号', name: 'handoverNo', width: 80 }, 			
			{ label: '快递单号', name: 'goodsNo', width: 80 }, 			
			{ label: '收件人ID', name: 'appuserId', width: 80, hidden:true}, 			
			{ label: '收件人姓名', name: 'userName', width:70 }, 			
			{ label: '收件人电话', name: 'tel', width: 90}, 			
			{ label: '状态', name: 'status', width: 60 ,formatter:function(v,o,r){
				// 0-未通知 1-已通知未通知 2-签收 3-延迟签收 4-拒收退货  5-删除 
				var status = '未通知';
				var style = 'label-default';
				switch (v) {
				case 5:
					style = "label-danger";
					status = '已删除';
					break;
				case 0:
					style = 'label-default';
					status = '未通知';
					break;
				case 1:
					status = '已通知';
					style = 'label-info';
					break;
				case 2:
					status = '已签收';
					style = 'label-success';
					break;
				case 3:
					status = '延迟签收';
					style = 'label-primary';
					break;
				case 4:
					style = "label-warning";
					status = '拒收退货';
					break;
				}
				return '<span class="label '+style+'" status="'+v+'">'+status+'</span>';
			}}, 	
			{ label: '包装类型ID', name: 'packagesId', width: 80 ,hidden:true},
			{ label: '包装类型', name: 'packages', width: 80 ,hidden:true},
			{ label: '存放货架', name: 'shelves', width: 80 , hidden:true},
			{ label: '快递员ID', name: 'courierId', width: 80 , hidden:true}, 			
			{ label: '快递员姓名', name: 'courierName', width: 70},
			{ label: '快递员电话', name: 'courierTel', width: 90, hidden:true},
			{ label: '快递公司ID', name: 'expressId', width: 80 , hidden:true}, 			
			{ label: '快递公司', name: 'express', width: 60 }, 			
			{ label: '物品', name: 'goods', width: 80 , hidden:true}, 			
			{ label: '重量', name: 'weight', width: 80, hidden:true }, 			
			{ label: '图片', name: 'img', width: 80 ,hidden:true}, 			
			{ label: '收件时间', name: 'createtime', width: 80}, 			
			{ label: '取件时间', name: 'gettime', width: 80 , hidden:true}, 			
			{ label: '取件人', name: 'getname', width: 80 , hidden:true}, 			
			{ label: '规格', name: 'spec', width: 80 , hidden:true}, 			
			{ label: '住址ID', name: 'houseId', width: 80,hidden:true}, 			
//			{ label: '住址', name: 'houseName', width: 150}, 			
			{ label: '住址', name: 'houseNumber', width: 120}, 			
			{ label: '收件备注', name: 'receivenote', width: 80}, 			
			{ label: '取件备注', name: 'getnote', width: 80,hidden:true}, 			
			{ label: '门牌号', name: 'houseNumber', width: 80 ,hidden:true}, 			
			{ label: '是否破损', name: 'isBroken', width: 80 ,formatter:function(value, options, row){
				//0-没有 1-破损
				return value === 0 ? 
						'<span class="label label-success">正常</span>' : 
						'<span class="label label-danger">破损</span>';
			},hidden:true},
			{ label: '操作人ID', name: 'operationId', width: 80,hidden:true},
			{ label: '操作人姓名', name: 'operationName', width: 80,hidden:true},
			{ label: '修改人ID', name: 'updateId', width: 80,hidden:true},
			{ label: '修改人姓名', name: 'updateName', width: 80,hidden:true},
			{ label: '修改时间', name: 'updateDate', width: 80,hidden:true}
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
        onSortCol:function(index,iCol,sortorder){
        	sidx = index;
        	order = sortorder;
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
    //快递员
    $.ajax({
    	url:'../courier/select2',
		dataType:'json',
		success : function(data) {
			var all = {
				id : -1,
				text : '全部快递员',
			};
			data.result.unshift(all);
			$('#label_courier').select2({
				data : data.result
			});
		}
    });
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		express:null,
		courier:null,
		handoverNo:null,
		goodsNo:null,
		status:null,
		houseNumber:null,
		userName:null,
		tel:null
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			var rowObj =$("#jqGrid").getRowData(id);
			if(rowObj.status.indexOf("未通知")>0
				||rowObj.status.indexOf("已通知")>0){
				location.href = "goods_update.html?id="+id;
			}else{
				alert("你选中的数据在该状态下不允许修改");
			}
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
		notice: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			$.ajax({
				type: "POST",
			    url: "../goods/notice",
			    data: JSON.stringify(ids),
			    success: function(r){
					if(r.code == 0){
						alert(r.msg, function(index){
							$("#jqGrid").trigger("reloadGrid");
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		query : function() {
			$("#jqGrid").jqGrid('setGridParam', {
				postData : {
					'expressId':$("#label_express").select2('val')==-1?'':$("#label_express").select2('val'),
					'courierId':$("#label_courier").select2('val')==-1?'':$("#label_courier").select2('val'),
					'handoverNo':$('#label_handoverNo').val(),
					'goodsNo':$('#label_goodsNo').val(),
					'tel':$('#label_tel').val(),
					'userName':escape($('#label_uname').val()),
					'houseNumber':escape($('#label_house').val()),
					'status':$('#label_status').select2('val')==-1?'':$("#label_status").select2('val'),
					'st':$('#label_st').val(),
					'et':$('#label_et').val()
				},
				page : 1
			}).trigger("reloadGrid");
		},
		add:function(){
			//TODO 判断选择快递公司和快递员
			//TODO 需要传递页面的参数
			//1、快递公司ID，名称 2、快递员ID，姓名 3、一个UUID标示是本次
			var express = $("#label_express").select2('data')[0];
			var courier = $('#label_courier').select2('data')[0];
			if(express==undefined||express==null||express.id==-1){
				alert("请选择快递公司");
				return;
			}
			if(courier==undefined||courier==null){
				alert("请选择快递员");
				return;
			}
			//波次号
			var ran = random(3);
			var handover_no = express.key+(systime().substring(0,8))+random(3);
			var param = 'express_id='+express.id+'&express_value='+escape(express.text)+
						'&courier_id='+courier.id+'&courier_value='+escape(courier.name)+
						"&handover_no="+handover_no+"&courier_tel="+courier.tel;
			//$('#label_handoverNo').val(handover_no);
			layer.open({
				type: 2,
				  title: '交接快件',
				  maxmin: true,
				  area: ['600px', '90%'],
				  content: ['goods_add.html?'+param, 'no'], //iframe的url，no代表不显示滚动条
//				  end:function(){
//					  //刷新表格
//					  $("#jqGrid").jqGrid('setGridParam', {
//							postData : {
//								'expressId':$("#label_express").select2('val')==-1?'':$("#label_express").select2('val'),
//								'courierId':$("#label_courier").select2('val')==-1?'':$("#label_courier").select2('val'),
//								'handoverNo':$('#label_handoverNo').val(),
//								'goodsNo':$('#label_goodsNo').val(),
//								'status':$('#label_status').select2('val')
//							},
//							page : 1
//						}).trigger("reloadGrid");
//				  }
				});
		},
		print : function() {
			var param = getparam();
			if(param!=""){
				window.open('print_list.html?' + param, '_blank');
			}
		},
		exportExcel:function(){
			var param = getparam();
			if(param!=""){
				window.open('../goods/excel?' + param, '_blank');
			}
		}
	}
});
function getparam() {
	var express = $("#label_express").select2('data')[0];
	var courier = $('#label_courier').select2('data')[0];
	var status = $('#label_status').select2('val');
	var handoverNo = $('#label_handoverNo').val();
	var goodsNo = $('#label_goodsNo').val();
	var tel = $('#label_tel').val();
	var house = escape($('#label_house').val());
	var uname = escape($('#label_uname').val());
	var expressId, courierId;
	if (express == null || express == undefined || express == 'undefined') {
		expressId = '';
	} else {
		expressId = express.id == -1 ? '' : express.id;
	}
	if (courier == null || courier == undefined || courier == 'undefined') {
		courierId = '';
	} else {
		courierId = courier.id == -1 ? '' : courier.id;
	}
	if (handoverNo == null || handoverNo == undefined
			|| handoverNo == 'undefined') {
		handoverNo = '';
	}
	if (goodsNo == null || goodsNo == undefined || goodsNo == 'undefined') {
		goodsNo = '';
	}
	if (tel == null || tel == undefined || tel == 'undefined') {
		tel = '';
	}
	if (status == null || status == undefined || status == 'undefined') {
		status = '';
	}
	if (uname == null || uname == undefined || uname == 'undefined') {
		uname = '';
	}
	if (house == null || house == undefined || house == 'undefined') {
		house = '';
	}
//	if (handoverNo == '') {
//		alert("请输入波次号");
//		return "";
//	}
	return 'expressId=' + expressId + '&courierId='
	+ courierId + '&status=' + status
	+ "&handoverNo=" + handoverNo+"&sidx="+sidx+"&order="+order
	+"&tel="+tel+"&house="+house+"&goodsNo="+goodsNo+"&uname="+uname;
}