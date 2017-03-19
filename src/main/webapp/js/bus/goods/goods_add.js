$(function(){
	$('#goods_no').focus();
	$('#createtime').val(new Date().Format('yyyy-MM-dd HH:mm:ss'));
});
var id = T.p("id");
var express_id = T.p("express_id");
var courier_id = T.p("courier_id");
var courier_tel = T.p("courier_tel");
var express_value = T.p("express_value");
var courier_value = T.p("courier_value");
var handover_no = T.p("handover_no");
setTimeout(function() {
	// 设置添加页面带过来的初始值
	$('#express_id').val(express_id);
	$('#express').val(unescape(express_value));
	$('#courier_id').val(courier_id);
	$('#courier_tel').val(courier_tel);
	$('#courier_name').val(unescape(courier_value));
	$('#handover_no').val(unescape(handover_no));
	//加载包装类型数据
	$('#packages_id').select2({
		data:loadDict('pack','包装类型')
	});
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
}, 100);
// 设置值
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"新增",
		goods:{
			expressId:express_id,
			express:express_value,
			handoverNo:handover_no,
			courierId:courier_id,
			courierName:courier_value,
			courierTel:courier_tel,
			isBroken:0,
			createtime:new Date().Format('yyyy-MM-dd HH:mm:ss')
		}
	},
	created: function() {
		if(id != null){
			this.title = "修改";
			this.getInfo(id)
		}
    },
	methods: {
		getInfo: function(id){
			$.get("../goods/info/"+id, function(r){
                vm.goods = r.goods;
            });
		},
		saveOrUpdate: function (event) {
			var url = vm.goods.id == null ? "../goods/save" : "../goods/update";
			var tmp_pack = $('#packages_id').select2('data');
			if (tmp_pack != null && tmp_pack.length > 0) {
				vm.goods.packagesId = tmp_pack[0].id;
				vm.goods.packages = tmp_pack[0].value;
			}
			//住址
			var tmp_house = $('#house_id').select2('data');
			if (tmp_house != null && tmp_house.length > 0) {
				vm.goods.houseId = tmp_house[0].id;
				vm.goods.houseName = tmp_house[0].text;
			}
			//表单校验
			if(vm.goods.goodsNo==''||vm.goods.goodsNo==null){
				alert("请输入快递单号");
				return;
			}
			if(vm.goods.tel==''||vm.goods.tel==null){
				alert("请输入收件人电话");
				return;
			}
			if(vm.goods.tel.length!=11){
				alert("请输入11位长度电话号码");
				return;
			}
			if(vm.goods.userName==''||vm.goods.userName==null){
				alert("请输入收件人姓名");
				return;
			}
//			if(vm.goods.houseName==''||vm.goods.houseName==null){
//				alert("请输入收件人住址");
//				return;
//			}
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.goods),
			    success: function(r){
			    	if(r.code === 0){
			    		//清空表单
						vm.cleanForm();
						//获取光标
						$('#goods_no').focus();
						layer.msg('添加成功', {
							  offset: 't',
							  anim: 3
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
		},
		reload:function(){
			window.parent.location.reload();
		},
		cleanForm:function(){
			//清空表单数据
			$('#goods_no').val('');
			vm.goods.goodsNo = null;
			$('#user_name').val('');
			vm.goods.userName = null;
			$('#tel').val('');
			vm.goods.tel = null;
			$('#house_id').val('');
			vm.goods.houseId = null;
			$('#house_name').val('');
			vm.goods.houseName = null;
			$('#house_number').val('');
			vm.goods.houseNumber = null;
			$('#weight').val('');
			vm.goods.weight = null;
			$('#packages_id').val('');
			vm.goods.packagesId = null;
			$('#packages').val('');
			vm.goods.packages = null;
			$('#createtime').val(new Date().Format('yyyy-MM-dd HH:mm:ss'));
			$('input[name=isBroken]').val(0);
			vm.goods.isBroken = 0;
		},
		loadUser:function(){
			if(vm.goods.tel.length==11){
				$.ajax({
				    url: "../appuser/tel",
				    data: {tel:vm.goods.tel},
				    success: function(r){
						if(r.code == 0){
							if(r.result!=null){
								$('#user_name').val(r.result.name);
								$('#house_number').val(r.result.houseNum);
								$('#house_name').val(r.result.houseName);
								$('#house_id').val(r.result.houseId);
								$('#house_id').select2('val',r.result.houseId);
								$('#select2-house_id-container').html(r.result.houseName);
								$('#select2-house_id-container').prop('title',r.result.houseName);
								vm.goods.userName = r.result.name;
								vm.goods.houseId = r.result.houseId;
								vm.goods.houseNumber = r.result.houseNum;
								vm.goods.houseName = r.result.houseName;
							}
						}else{
							alert(r.msg);
						}
					}
				});
			}
		},
		print:function(){
			//获取参数
			var weight = $('#weight').val();
			var user_name = $('#user_name').val();
			var tel = $('#tel').val();
			var param = "courier_tel="+courier_tel+"&courier_value="+escape(courier_value)+
				"&express_value="+escape(express_value)+"&handover_no="+handover_no+"&weight="+weight+
				"&user_name="+escape(user_name)+"&tel="+tel;
			window.open('print.html?'+ param, '_blank');
		},
		readOrWrite:function(){
			var $han = $('#handover_no');
			var $ch = $('#change_han');
			if($han.prop('readonly')){
				$han.prop('readonly',false);
				$ch.val("完成");
			}else{
				$han.prop('readonly',true);
				$ch.val("修改");
			}
		}
	}
});
