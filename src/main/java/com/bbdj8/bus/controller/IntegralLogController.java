package com.bbdj8.bus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdj8.bus.entity.IntegralLogEntity;
import com.bbdj8.bus.service.IntegralLogService;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 积分变化日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
@RestController
@RequestMapping("integrallog")
public class IntegralLogController {
	@Autowired
	private IntegralLogService integralLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("integrallog:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<IntegralLogEntity> integralLogList = integralLogService.queryList(map);
		int total = integralLogService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(integralLogList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("integrallog:info")
	public R info(@PathVariable("id") Integer id){
		IntegralLogEntity integralLog = integralLogService.queryObject(id);
		
		return R.ok().put("integralLog", integralLog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("integrallog:save")
	public R save(@RequestBody IntegralLogEntity integralLog){
		integralLogService.save(integralLog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("integrallog:update")
	public R update(@RequestBody IntegralLogEntity integralLog){
		integralLogService.update(integralLog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("integrallog:delete")
	public R delete(@RequestBody Integer[] ids){
		integralLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
