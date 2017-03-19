var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"通知取件",
	},
	created: function() {
    },
	methods: {
		note: function () {
			var url = "../goods/receiving";
			var param = {
					ids:id,
					status:$('input[name=status]:checked').val(),
					getnote:escape($('#getnote').val()),
					getname:''
			}
			$.ajax({
			    url: url,
			    data: param,
			    success: function(r){
			    	if(r.code === 0){
			    		vm.back();
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
$('#getnote').focus();