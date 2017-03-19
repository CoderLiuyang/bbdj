package com.bbdj8.bus.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbdj8.bus.entity.GoodsEntity;
import com.bbdj8.bus.service.GoodsService;
import com.bbdj8.sys.utils.DateUtils;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;
import com.bbdj8.sys.utils.string.StringUtil;


/**
 * 快递货物信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:13
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("goods:list")
	public R list(Integer page, Integer limit, String express, 
			String courier,String handoverNo,String status,
			String expressId,String courierId,String tel,
			String goodsNo,String sidx,String order,
			String userName,String houseNumber,String st,String et) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//快递公司
		map.put("expressId", expressId);
		map.put("express", express);
		//快递员
		map.put("courierId", courierId);
		map.put("courier", courier);
		//波次号
		map.put("handoverNo", handoverNo);
		//状态
		map.put("status", status);
		//电话号码
		map.put("tel", tel);
		//快递单号
		map.put("goodsNo", goodsNo);
		map.put("st", st);
		map.put("et", et);
		//排序
		this.getQueryStr(map, sidx, order);
		//收件人
		if (StringUtils.isNotBlank(userName)) {
			userName = StringUtil.unescape(userName);
			map.put("userName",userName);
		}
		//住址
		if (StringUtils.isNotBlank(houseNumber)) {
			userName = StringUtil.unescape(houseNumber);
			map.put("houseNumber",houseNumber);
		}
		// 查询列表数据
		List<GoodsEntity> goodsList = goodsService.queryList(map);
		int total = goodsService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(goodsList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * TODO 查询字段判断，暂时性这样处理，之后完善
	 * @param map
	 * @param sidx
	 * @param order
	 */
	private void getQueryStr(Map<String, Object> map, String sidx, String order) {
		String str = null;
		switch (sidx) {
		case "houseNumber":
			str = "house_number";
			break;
		case "goodsNo":
			str = "goods_no";
			break;
		case "handoverNo":
			str = "handover_no";
			break;
		case "userName":
			str = "user_name";
			break;
		case "courierName":
			str = "courier_name";
			break;
		}
		if (StringUtils.isNotBlank(str)) {
			map.put("sidx", str);
			map.put("order", order);
		}
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("goods:info")
	public R info(@PathVariable("id") Integer id){
		GoodsEntity goods = goodsService.queryObject(id);
		
		return R.ok().put("goods", goods);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("goods:save")
	public R save(@RequestBody GoodsEntity goods) {
		// 表单验证
		if (goods == null) {
			return R.error("提交数据异常，表单为空");
		}
		if (StringUtils.isBlank(goods.getGoodsNo())) {
			return R.error("快递单号为空");
		}
		GoodsEntity tmp = goodsService.findByGoodsNo(goods.getGoodsNo());
		if (tmp != null) {
			return R.error("快递单号已存在");
		}
		if (StringUtils.isBlank(goods.getTel())) {
			return R.error("电话号码为空");
		}
		if (StringUtils.isBlank(goods.getHandoverNo())) {
			return R.error("波次号为空");
		}
		goodsService.save(goods);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("goods:update")
	public R update(@RequestBody GoodsEntity goods){
		goodsService.update(goods);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("goods:delete")
	public R delete(@RequestBody Integer[] ids){
//		goodsService.updateBatch(ids,5);
		goodsService.deleteBatch(ids);
		return R.ok();
	}
	/**
	 * 处理更改状态等业务
	 * @param status
	 * @return
	 */
	@RequestMapping("/receiving")
	public R receiving(String ids, String getname, String getnote,Integer status) {
		String result = "系统忙，请稍后再试...";
		if (StringUtils.isNotBlank(ids)) {
			try {
				String[] idStr = ids.split(",");
				if (StringUtils.isNotBlank(getname)) {
					getname = StringUtil.unescape(getname);
				}
				if (StringUtils.isNotBlank(getnote)) {
					getnote = StringUtil.unescape(getnote);
				}
				for (int i = 0; i < idStr.length; i++) {
					result = goodsService.receiving(Integer.valueOf(idStr[i]), status, getname, getnote);
				}
			} catch (Exception e) {
				logger.error("签收快件异常", e);
			}
		}
		return R.ok(result);
	}
	/**
	 * 添加备注
	 * @param id
	 * @param getnote
	 * @return
	 */
	@RequestMapping(value="/note",method = RequestMethod.POST)
	public R note(@RequestBody Map<String,String> param) { 
		String result = "系统忙，请稍后再试...";
		Integer id = Integer.valueOf(param.get("id"));
		String getnote = param.get("getnote");
		if (null!=id) {
			try {
				if (StringUtils.isNotBlank(getnote)) {
					getnote = StringUtil.unescape(getnote);
				}
				GoodsEntity goods = goodsService.queryObject(id);
				if(goods!=null){
					goods.setGetnote(getnote);
					goodsService.update(goods);
				}
				result="修改备注成功";
			} catch (Exception e) {
				logger.error("签收快件异常", e);
			}
		}
		return R.ok(result);
	}
	/**
	 * 快件批量改为已通知状态，然后消息通知用户
	 * @param ids 
	 * @return
	 */
	@RequestMapping("/notice")
	public R notice(@RequestBody Integer[] ids) {
		String msg = "成功通知取件人数%s人，失败人数%s人";
		int i = 0, j = 0;
		for (Integer id : ids) {
			GoodsEntity goods = goodsService.queryObject(id);
			if (goods != null && 0 == goods.getStatus()) {
				// 0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货 5删除
				goods.setStatus(1);
				goodsService.update(goods);
				// 发送信息
				goodsService.notice(goods);
				i++;
			} else {
				j++;
			}
		}
		return R.ok(String.format(msg, i, j));
	}
	/**
	 * 批量签收快件
	 * @param ids
	 * @param getname
	 * @return
	 */
	@RequestMapping("/receivingBatch")
	public R receivingBatch(String ids, String getname,String getnote) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idArr = ids.split(",");
			if (StringUtils.isNotBlank(getname)) {
				getname = StringUtil.unescape(getname);
			}
			if (StringUtils.isNotBlank(getnote)) {
				getnote = StringUtil.unescape(getnote);
			}
			for (String id : idArr) {
				try {
					goodsService.receiving(Integer.valueOf(id), 2, getname,getnote);
				} catch (Exception e) {
					logger.error("批量签收快件异常", e);
				}
			}
			return R.ok("签收快件成功");
		} else {
			return R.ok("快件信息为空");
		}
	}
	/**
	 * 打印单数据查询
	 * @param handoverNo
	 * @param expressId
	 * @param courierId
	 * @param status
	 * @return
	 */
	@RequestMapping("/print")
	public R handover(String handoverNo,String expressId,
			String courierId, String status,String order,String sidx,
			String uname,String house,String tel,String goodsNo,String st,String et) {
		Map<String, Object> map = new HashMap<>();
		map.put("handoverNo", handoverNo);
		map.put("expressId", expressId);
		map.put("courierId", courierId);
		map.put("status", status);
		this.getQueryStr(map, sidx, order);
		map.put("tel", tel);
		map.put("goodsNo", goodsNo);
		map.put("houseNumber", house);
		map.put("userName", uname);
		map.put("st", st);
		map.put("et", et);
		return R.ok().put("result", goodsService.queryList(map));
	}
	/**
	 * 导出excel
	 * @param handoverNo
	 * @param expressId
	 * @param courierId
	 * @param status
	 * @param order
	 * @param sidx
	 * @param uname
	 * @param house
	 * @param tel
	 * @param goodsNo
	 */
	@RequestMapping("/excel")
	public void exportExcel(String handoverNo,String expressId,
			String courierId, String status,String order,String sidx,
			String uname,String house,String tel,String goodsNo,
			HttpServletResponse response,String st,String et){
		Map<String, Object> map = new HashMap<>();
		map.put("handoverNo", handoverNo);
		map.put("expressId", expressId);
		map.put("courierId", courierId);
		map.put("status", status);
		this.getQueryStr(map, sidx, order);
		map.put("tel", tel);
		map.put("goodsNo", goodsNo);
		map.put("houseNumber", house);
		map.put("userName", uname);
		map.put("st", st);
		map.put("et", et);
		HSSFWorkbook book = goodsService.excel(map);
		try {
			response.setHeader("content-disposition", "attachment;filename=" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls");  
			book.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error("导出excel",e);
		}
	}
}

