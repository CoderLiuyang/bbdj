package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.HouseEntity;

import java.util.List;
import java.util.Map;

/**
 * 小区信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-21 11:59:26
 */
public interface HouseService {
	
	HouseEntity queryObject(Integer id);
	
	List<HouseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HouseEntity house);
	
	void update(HouseEntity house);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
