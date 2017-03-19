$(function () {
    $("#jqGrid").jqGrid({
        url: '../courier/list',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'id', width: 50, key: true ,hidden:true},
			{ label: '快递员英文名', name: 'ename', width: 80 }, 			
			{ label: '快递员姓名', name: 'name', width: 80 }, 			
			{ label: '所属快递公司ID', name: 'expressId', width: 80,hidden:true }, 			
			{ label: '所属快递公司', name: 'express', width: 80 }, 			
			{ label: '联系电话', name: 'tel', width: 80 },			
			{ label: '身份证号码', name: 'idcard', width: 80 ,hidden:true },			
			{ label: '状态 ', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
						'<span class="label label-success">正常</span>' : 
						'<span class="label label-danger">删除</span>';
				} }
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
			
			location.href = "courier_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../courier/delete",
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