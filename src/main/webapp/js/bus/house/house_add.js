var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		house:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../house/info/"+id, function(r){
                vm.house = r.house;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.house.id == null ? "../house/save" : "../house/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.house),
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