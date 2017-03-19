package com.bbdj8.bus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdj8.bus.entity.ExpressOrderEntity;
import com.bbdj8.bus.service.ExpressOrderService;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;
import com.bbdj8.sys.utils.string.StringUtil;


/**
 * 发件信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-27 17:46:18
 */
@RestController
@RequestMapping("expressorder")
public class ExpressOrderController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExpressOrderService expressOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("expressorder:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ExpressOrderEntity> expressOrderList = expressOrderService.queryList(map);
		int total = expressOrderService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(expressOrderList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("expressorder:info")
	public R info(@PathVariable("id") Integer id){
		ExpressOrderEntity expressOrder = expressOrderService.queryObject(id);
		
		return R.ok().put("expressOrder", expressOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("expressorder:save")
	public R save(@RequestBody ExpressOrderEntity expressOrder){
		expressOrderService.save(expressOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("expressorder:update")
	public R update(@RequestBody ExpressOrderEntity expressOrder){
		expressOrderService.update(expressOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("expressorder:delete")
	public R delete(@RequestBody Integer[] ids){
		expressOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 快递员取件
	 * @param expressId 快递公司ID
	 * @param courierId 快递员ID
	 * @param id 订单ID
	 * @param note 备注信息
	 * @return
	 */
	@RequestMapping("/take")
	public R take(Integer id, Integer courierId, String note) {
		if (StringUtils.isNotBlank(note)) {
			note = StringUtil.unescape(note);
		}
		// 取件快递员信息
		String message = expressOrderService.take(courierId, id, note);
		return R.ok(message);
	}
	
}
