package com.bbdj8.bus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.bus.service.AppUserService;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 用户表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:12
 */
@RestController
@RequestMapping("appuser")
public class AppUserController {
	@Autowired
	private AppUserService appUserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("appuser:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<AppUserEntity> appUserList = appUserService.queryList(map);
		int total = appUserService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(appUserList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("appuser:info")
	public R info(@PathVariable("id") Integer id){
		AppUserEntity appUser = appUserService.queryObject(id);
		
		return R.ok().put("appUser", appUser);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("appuser:save")
	public R save(@RequestBody AppUserEntity appUser){
		appUserService.save(appUser);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("appuser:update")
	public R update(@RequestBody AppUserEntity appUser){
		appUserService.update(appUser);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("appuser:delete")
	public R delete(@RequestBody Integer[] ids){
		appUserService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 根据电话号码查询当前人
	 * @param tel
	 * @return
	 */
	@RequestMapping("/tel")
	public R tel(@RequestParam("tel") String tel){
		AppUserEntity user = appUserService.findByTel(tel);
		return R.ok().put("result", user);
	}
}
