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

import com.bbdj8.bus.entity.CourierEntity;
import com.bbdj8.bus.service.CourierService;
import com.bbdj8.sys.controller.AbstractController;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 快递员信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-22 11:35:28
 */
@RestController
@RequestMapping("courier")
public class CourierController extends AbstractController{
	@Autowired
	private CourierService courierService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("courier:list")
	public R list(Integer page, Integer limit,String name){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("name", name);
		
		//查询列表数据
		List<CourierEntity> courierList = courierService.queryList(map);
		int total = courierService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(courierList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("courier:info")
	public R info(@PathVariable("id") Integer id){
		CourierEntity courier = courierService.queryObject(id);
		
		return R.ok().put("courier", courier);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("courier:save")
	public R save(@RequestBody CourierEntity courier){
		courierService.save(courier);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("courier:update")
	public R update(@RequestBody CourierEntity courier){
		courierService.update(courier);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("courier:delete")
	public R delete(@RequestBody Integer[] ids){
		courierService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * select2
	 * @return
	 */
	@RequestMapping("/select2")
	public R select2(){
		List<CourierEntity> entities = courierService.queryList(null);
		return R.ok().put("result",entities);
	}
}
