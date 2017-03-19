var ids = T.p("ids");
var price = parseInt(T.p("price"));
price = (price>0)?"扣除"+price:"增加"+Math.abs(price);
var title = "请输入取件人信息，"+price+"积分";
$('#note').html(title);
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"取件",
	},
	created: function() {
    },
	methods: {
		receive: function () {
			var url = "../goods/receiving";
			var param = {
					ids:ids,
					status:2,
					getname:escape($('#getname').val()),
					getnote:''
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
$('#getname').focus();