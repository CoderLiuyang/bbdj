package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.CourierEntity;

import java.util.List;
import java.util.Map;

/**
 * 快递员信息表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-22 11:35:28
 */
public interface CourierService {
	
	CourierEntity queryObject(Integer id);
	
	List<CourierEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CourierEntity courier);
	
	void update(CourierEntity courier);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
