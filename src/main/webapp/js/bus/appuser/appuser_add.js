var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		appUser:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../appuser/info/"+id, function(r){
                vm.appUser = r.appUser;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.appUser.id == null ? "../appuser/save" : "../appuser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.appUser),
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