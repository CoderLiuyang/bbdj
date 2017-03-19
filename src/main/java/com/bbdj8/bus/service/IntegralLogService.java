package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.IntegralLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 积分变化日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
public interface IntegralLogService {
	
	IntegralLogEntity queryObject(Integer id);
	
	List<IntegralLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IntegralLogEntity integralLog);
	
	void update(IntegralLogEntity integralLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
