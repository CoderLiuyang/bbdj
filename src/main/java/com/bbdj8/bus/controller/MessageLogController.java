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

import com.bbdj8.bus.entity.MessageLogEntity;
import com.bbdj8.bus.service.MessageLogService;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 消息发送日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
@RestController
@RequestMapping("messagelog")
public class MessageLogController {
	@Autowired
	private MessageLogService messageLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("messagelog:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<MessageLogEntity> messageLogList = messageLogService.queryList(map);
		int total = messageLogService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(messageLogList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("messagelog:info")
	public R info(@PathVariable("id") Integer id){
		MessageLogEntity messageLog = messageLogService.queryObject(id);
		
		return R.ok().put("messageLog", messageLog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("messagelog:save")
	public R save(@RequestBody MessageLogEntity messageLog){
		messageLogService.save(messageLog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("messagelog:update")
	public R update(@RequestBody MessageLogEntity messageLog){
		messageLogService.update(messageLog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("messagelog:delete")
	public R delete(@RequestBody Integer[] ids){
		messageLogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
