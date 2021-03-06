var id = T.p("id");
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		dictionary:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../dictionary/info/"+id, function(r){
                vm.dictionary = r.dictionary;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.dictionary.id == null ? "../dictionary/save" : "../dictionary/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dictionary),
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