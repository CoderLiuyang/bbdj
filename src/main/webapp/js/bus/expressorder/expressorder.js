$(function () {
    $("#jqGrid").jqGrid({
        url: '../expressorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '发件人电话', name: 'sendTel', width: 80 }, 			
			{ label: '发件人姓名', name: 'sendName', width: 80 }, 			
			{ label: '发件地址', name: 'sendAddr', width: 80 ,hidden:true}, 			
			{ label: '收件人电话', name: 'receiveTel', width: 80 }, 			
			{ label: '收件人姓名', name: 'receiveName', width: 80 }, 			
			{ label: '收件人地址', name: 'receiveAddr', width: 80 ,hidden:true}, 			
			{ label: '提交时间', name: 'createtime', width: 80 }, 			
			{ label: '快递公司ID', name: 'expressId', width: 80 ,hidden:true}, 			
			{ label: '快递公司名字', name: 'expressName', width: 80 }, 			
			{ label: '快递员ID', name: 'courierId', width: 80 ,hidden:true}, 			
			{ label: '快递员名字', name: 'courierName', width: 80 }, 			
			{ label: '快递员电话', name: 'courierTel', width: 80 }, 			
			{ label: '重量', name: 'weight', width: 80 ,hidden:true}, 			
			{ label: '快递员取件时间', name: 'sendtime', width: 80 ,hidden:true}, 			
			{ label: '保价金额', name: 'valuation', width: 80 ,hidden:true}, 			
			{ label: '快递单号', name: 'expressNum', width: 80 }, 			
			{ label: '规格', name: 'spec', width: 80 ,hidden:true}, 			
			{ label: '物品', name: 'goods', width: 80 ,hidden:true}, 			
			{ label: '备注', name: 'desc', width: 80 ,hidden:true}, 			
			{ label: '状态', name: 'status', width: 80 ,formatter:function(v,o,r){
				//  0-快递员未签收 1-快递员已签收 2-删除 3-提交成功未处理
				var status = '未取件';
				var style = 'label-info';
				switch (v) {
				case 0:
					style = 'label-info';
					status = '未取件';
					break;
				case 1:
					status = '已取件';
					style = 'label-success';
					break;
				case 2:
					style = "label-warning";
					status = '删除';
					break;
				}
				return '<span class="label '+style+'" status="'+v+'">'+status+'</span>';
			}}, 			
			{ label: '快递费', name: 'money', width: 80 ,hidden:true}, 			
			{ label: '支付类型', name: 'payType', width: 80 ,formatter:function(v,o,r){
				//  0-现金 1-积分 2-积分+现金
				var status = '现金';
				var style = 'label-info';
				switch (v) {
				case 0:
					style = 'label-info';
					status = '现金';
					break;
				case 1:
					status = '积分';
					style = 'label-success';
					break;
				}
				return '<span class="label '+style+'" status="'+v+'">'+status+'</span>';
			
			}},
			{ label: '操作', width: 80,formatter:function(v,o,r){
				if(r.status==0)
					return '<input type="button" class="btn btn-primary btn-xs" onClick="take('+r.id+')" value="取件"/>';
				else
					return "";
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
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		
	},
	methods: {
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			
			location.href = "expressorder_update.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../expressorder/delete",
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
		}
	}
});
/**
 * 快递员取件
 * @param id
 */
function take(id){
	layer.open({
		  type: 2,
		  title: '取件',
		  maxmin: true,
		  area: ['550px', '90%'],
		  content: ['expressorder_take.html?id='+id, 'no'], //iframe的url，no代表不显示滚动条
		  end:function(){
			  //刷新表格
			  $("#jqGrid").jqGrid('setGridParam', {
					page : 1
				}).trigger("reloadGrid");
//			  $("#jqGrid").jqGrid('setGridParam', {
//				  postData : {
//					  'expressId':$("#label_express").select2('val')==-1?'':$("#label_express").select2('val'),
//							  'courierId':$("#label_courier").select2('val')==-1?'':$("#label_courier").select2('val'),
//									  'handoverNo':$('#label_handoverNo').val(),
//									  'goodsNo':$('#label_goodsNo').val(),
//									  'status':$('#label_status').select2('val')
//				  },
//				  page : 1
//			  }).trigger("reloadGrid");
		  }
		});
}