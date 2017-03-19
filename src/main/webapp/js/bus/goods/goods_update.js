$(function() {
	$('#goods_no').focus();
});
var id = T.p("id");
var packages_data = loadDict('pack', '包装类型');
var express_data = loadDict('express', '快递公司');
function loadSelect2() {
	// 加载包装类型数据
	$('#packages_id').select2({
		// placeholder : "全部包装类型",
		language : 'zh-CN',
		data : packages_data
	});
	// 快递公司
	$('#label_express').select2({
		placeholder : "全部快递公司",
		language : 'zh-CN',
		data : express_data
	});
	// 住址数据
	$.ajax({
		url : '../house/select2',
		dataType : 'json',
		success : function(data) {
			$('#house_id').select2({
				placeholder : "请选择住址",
				language : 'zh-CN',
				data : data.result
			});
		}
	});
}
// 设置值
var vm = new Vue(
		{
			el : '#rrapp',
			data : {
				title : "新增",
				goods : {}
			},
			created : function() {
				if (id != null) {
					this.title = "修改";
					this.getInfo(id)
				}
			},
			methods : {
				getInfo : function(id) {
					$.get("../goods/info/" + id, function(r) {
						loadSelect2();
						vm.goods = r.goods;
						var tmp_house = '<option value="'+r.goods.houseId+'" selected="selected">'+r.goods.houseName+'</option>';
						$('#house_id').append(tmp_house);
						var tmp_pack = '<option value="'+r.goods.packagesId+'" selected="selected">'+r.goods.packages+'</option>';
						$('#packages_id').append(tmp_pack);
						var tmp_express = '<option value="'+r.goods.expressId+'" selected="selected">'+r.goods.express+'</option>';
						$('#label_express').append(tmp_express);
						
					});
				},
				saveOrUpdate : function(event) {
					var url = vm.goods.id == null ? "../goods/save"
							: "../goods/update";
					var tmp_pack = $('#packages_id').select2('data');
					if (tmp_pack != null && tmp_pack.length > 0) {
						vm.goods.packagesId = tmp_pack[0].id;
						vm.goods.packages = tmp_pack[0].text;
					}
					// 住址
					var tmp_house = $('#house_id').select2('data');
					if (tmp_house != null && tmp_house.length > 0) {
						vm.goods.houseId = tmp_house[0].id;
						vm.goods.houseName = tmp_house[0].text;
					}
					var tmp_express = $('#label_express').select2('data');
					if (tmp_express != null && tmp_express.length > 0) {
						vm.goods.expressId = tmp_express[0].id;
						vm.goods.express = tmp_express[0].text;
					}
					/*if (vm.goods.houseName == '' || vm.goods.houseName == null) {
						alert("请输入收件人住址");
						return;
					}*/
					//时间
					vm.goods.createtime = $('#createtime').val();
					$.ajax({
						type : "POST",
						url : url,
						data : JSON.stringify(vm.goods),
						success : function(r) {
							if (r.code === 0) {
								alert('操作成功', function(index) {
									vm.back();
								});
							} else {
								alert(r.msg);
							}
						}
					});
				},
				back : function(event) {
					history.go(-1);
				},
				reload : function() {
					window.parent.location.reload();
				}
			}
		});