var id = T.p("id");
//住址数据
$.ajax({
	url : '../house/select2',
	dataType : 'json',
	success : function(data) {
		$('#house_id').select2({
			placeholder : "请选择住址",
			language : 'zh-CN',
			data:data.result
		});
	}
});
$(function () {
	$('#label_express_id').select2({
		language : 'zh-CN',
		data:loadDict('express','快递公司')
	});
});
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
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		expressOrder:{}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../expressorder/info/"+id, function(r){
                vm.expressOrder = r.expressOrder;
            });
		},
		saveOrUpdate: function (event) {
			/*if(vm.expressOrder.sendTel==''||vm.expressOrder.sendTel==null){
				//发件人电话
				alert("请输入发件人电话");
				return;
			}
			if(vm.expressOrder.sendName==''||vm.expressOrder.sendName==null){
				//发件人姓名
				alert("请输入发件人姓名");
				return;
			}
			var tmp_house = $('#house_id').select2('data');
			if (tmp_house != null && tmp_house.length > 0) {
				vm.expressOrder.houseId = tmp_house[0].id;
				vm.expressOrder.houseName = tmp_house[0].text;
			}else{
				if(vm.expressOrder.houseId==''||vm.expressOrder.houseId==null){
					alert("请选择发件住址");
					return;
				}
			}
			vm.expressOrder.sendAddr = vm.expressOrder.houseName+"-"+vm.expressOrder.houseNumber;
			if(vm.expressOrder.receiveTel==''||vm.expressOrder.receiveTel==null){
				//收件人电话
				alert("请输入收件人电话");
				return;
			}
			if(vm.expressOrder.receiveName==''||vm.expressOrder.receiveName==null){
				//收件人姓名
				alert("请输入收件人姓名");
				return;
			}
			//判断省市选择情况
			var pro_val = $('#loc_province').val();
			var city_val = $('#loc_city').val();
			var town_val = $('#loc_town').val();
			var pro_text = $('#loc_province').select2('data').text;
			var city_text = $('#loc_city').select2('data').text;
			var town_text = $('#loc_town').select2('data').text;
			if(pro_val==''||city_val==''||town_val==''){
				alert("请选择收件人省市");
				return;
			}
			var tmp_addr = pro_text+"-"+city_text+"-"+town_text;
			if(vm.expressOrder.receiveAddr=='' || vm.expressOrder.receiveAddr==null){
				alert("请输入收件人详细地址");
				return;
			}
			vm.expressOrder.receiveAddr = tmp_addr+"-"+vm.expressOrder.receiveAddr;
			//快递公司
			var tmp_express = $('#label_express_id').select2('data');
			if(tmp_express != null && tmp_express.length > 0){
				vm.expressOrder.expressId = tmp_express[0].id;
				vm.expressOrder.expressName = tmp_express[0].text;
			}else{
				alert("请选择快递公司");
				return;
			}*/
			if(vm.expressOrder.money==''||vm.expressOrder.money==null){
				//快递费
				alert("请输入快递费");
				return;
			}
			var url = vm.expressOrder.id == null ? "../expressorder/save" : "../expressorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.expressOrder),
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