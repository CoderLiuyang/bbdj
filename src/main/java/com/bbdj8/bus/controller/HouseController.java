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

import com.bbdj8.bus.entity.HouseEntity;
import com.bbdj8.bus.service.HouseService;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 小区信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-21 11:59:26
 */
@RestController
@RequestMapping("house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("house:list")
	public R list(Integer page, Integer limit, String text) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("text", text);
		// 查询列表数据
		List<HouseEntity> houseList = houseService.queryList(map);
		int total = houseService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(houseList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("house:info")
	public R info(@PathVariable("id") Integer id){
		HouseEntity house = houseService.queryObject(id);
		
		return R.ok().put("house", house);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("house:save")
	public R save(@RequestBody HouseEntity house){
		houseService.save(house);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("house:update")
	public R update(@RequestBody HouseEntity house){
		houseService.update(house);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("house:delete")
	public R delete(@RequestBody Integer[] ids){
		houseService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * select2
	 * @return
	 */
	@RequestMapping("/select2")
	public R select2() {
		List<HouseEntity> houses = houseService.queryList(null);
		return R.ok().put("result", houses);
	}
	
}
