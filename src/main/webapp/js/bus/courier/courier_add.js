$(function () {
	$('#label_express_id').select2({
		language : 'zh-CN',
		data:loadDict('express','快递公司')
	});
});
var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		courier:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../courier/info/"+id, function(r){
                vm.courier = r.courier;
                var tmp_express = '<option value="'+r.courier.expressId+'" selected="selected">'+r.courier.express+'</option>';
                setTimeout(function(){
                	$('#label_express_id').append(tmp_express);
                }, 100)
            });
		},
		saveOrUpdate: function (event) {
			//选择的快递员
			var tmp_express = $('#label_express_id').select2('data');
			if (tmp_express != null && tmp_express.length > 0) {
				vm.courier.expressId = tmp_express[0].id;
				vm.courier.express = tmp_express[0].text;
			}
			if (vm.courier.expressId < 0) {
				alert("请选择快递公司");
				return;
			}
			var url = vm.courier.id == null ? "../courier/save" : "../courier/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.courier),
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
			history.go(-1);
		}
	}
});