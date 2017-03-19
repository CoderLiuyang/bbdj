var expressId = getQueryString("expressId");
var courierId = getQueryString("courierId");
var handoverNo = getQueryString("handoverNo");
var status = getQueryString("status");
var status = getQueryString("status");
var sidx = getQueryString("sidx");
var order = getQueryString("order");
var goodsNo = getQueryString("goodsNo");
var tel = getQueryString("tel");
var uname = getQueryString("uname");
var house = getQueryString("house");
var express="";
$(function(){
	//加载数据
	$.ajax({
		url : '../goods/print',
		data : {
			expressId : expressId,
			courierId : courierId,
			handoverNo : handoverNo,
			order : order,
			sidx : sidx,
			status : status,
			uname : uname,
			goodsNo : goodsNo,
			house : house,
			tel : tel
		},
		success:function(data){
			var tr_tmp,b;
			var result = data.result;
			for (var i = 0; i < result.length; i++) {
				var tmp = result[i];
				express = tmp.express;
				if(i%2==0){
					b="background-color:#EAEAEA";
				}else{
					b="";
				}
				tr_tmp+="<tr style="+b+">";
				tr_tmp+="<td>"+(i+1)+"</td><td>"+tmp.goodsNo+"</td><td>"+
				(tmp.userName.substring(0,1)+'**')+"</td><td>"+tmp.tel+"</td><td>"+(tmp.houseNumber==null?"":tmp.houseNumber)+"</td><td></td><td></td>"
				tr_tmp+="</tr>";
//				tr_tmp+="<tr><td>"+(i+1)+"</td><td>"+tmp.handoverNo+"</td><td>"+tmp.goodsNo+"</td><td>"+
//				tmp.userName+"</td><td>"+tmp.tel+"</td><td></td></tr>"
			}
			$('#express').html(express);
			$('#showData').append(tr_tmp);
			$('#cur_row').html(result.length);
			$('#cur_time').html(new Date().Format('yyyy-MM-dd'));
			$('#handover_no').html(handover_no);
		}
	});
});