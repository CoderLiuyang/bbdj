var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		messageLog:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../messagelog/info/"+id, function(r){
                vm.messageLog = r.messageLog;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.messageLog.id == null ? "../messagelog/save" : "../messagelog/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.messageLog),
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