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
var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"取件",
	},
	created: function() {
    },
	methods: {
		take: function () {
			var url = "../expressorder/take";
			//快递员
			var courier = $('#label_courier').select2('data')[0];
			if(courier==undefined || courier==null){
				alert("请选择快递员");
				return;
			}else{
				if (courier.id < 1) {
					alert("请选择快递员");
					return;
				}
			}
			var param = {
					id:id,
					courierId:courier.id,
					note:escape($('#note').val())
			}
//			$.post(url,param,function(r){
//				if(r.code === 0){
//					alert('操作成功', function(index){
//						vm.back();
//					});
//				}else{
//					alert(r.msg);
//				}
//			});
			$.ajax({
			    url: url,
			    data: param,
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		back: function (event) {
			var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
			parent.layer.close(index);  // 关闭layer
		}
	}
});