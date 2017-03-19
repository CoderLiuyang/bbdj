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

import com.bbdj8.bus.entity.DictionaryEntity;
import com.bbdj8.bus.service.DictionaryService;
import com.bbdj8.sys.controller.AbstractController;
import com.bbdj8.sys.utils.PageUtils;
import com.bbdj8.sys.utils.R;


/**
 * 字典
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-13 11:33:07
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryController extends AbstractController{
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("dictionary:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<DictionaryEntity> dictionaryList = dictionaryService.queryList(map);
		int total = dictionaryService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(dictionaryList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("dictionary:info")
	public R info(@PathVariable("id") Integer id){
		DictionaryEntity dictionary = dictionaryService.queryObject(id);
		
		return R.ok().put("dictionary", dictionary);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("dictionary:save")
	public R save(@RequestBody DictionaryEntity dictionary){
		dictionaryService.save(dictionary);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("dictionary:update")
	public R update(@RequestBody DictionaryEntity dictionary){
		dictionaryService.update(dictionary);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("dictionary:delete")
	public R delete(@RequestBody Integer[] ids){
		dictionaryService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 按照类型查询数据
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping("/select2")
	public R express(String key,String value){
		List<DictionaryEntity> result = dictionaryService.findByType(key, value);
		return R.ok().put("result", result);
	}
	/**
	 * 所有字典类型
	 * @return
	 */
	@RequestMapping("/type")
	public R type() {
		List<DictionaryEntity> result = dictionaryService.findType();
		return R.ok().put("result", result);
	}
}
