$(function () {
    $("#jqGrid").jqGrid({
        url: '../appuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', width: 50, key: true },
			{ label: '姓名', name: 'name', width: 80 }, 			
			{ label: '昵称', name: 'nickname', width: 80 }, 			
			{ label: '电话号码', name: 'tel', width: 80 }, 			
			{ label: '微信openid', name: 'openid', width: 80 ,hidden:true}, 			
//			{ label: '住址', name: 'houseName', width: 80 }, 			
			{ label: '住址', name: 'houseNum', width: 80 }, 			
//			{ label: '门牌号', name: 'houseNum', width: 80 }, 			
			{ label: '积分', name: 'integral', width: 80 }, 			
			{ label: '收件人英文名，默认姓名首字母', name: 'ename', width: 80 ,hidden:true}			
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
			
			location.href = "appuser_add.html?id="+id;
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../appuser/delete",
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