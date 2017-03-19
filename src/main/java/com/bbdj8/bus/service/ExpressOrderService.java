package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.ExpressOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 发件信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-27 17:46:18
 */
public interface ExpressOrderService {
	
	ExpressOrderEntity queryObject(Integer id);
	
	List<ExpressOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ExpressOrderEntity expressOrder);
	
	void update(ExpressOrderEntity expressOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
	 * 快递员取件
	 * @param courierId
	 * @param id
	 * @param note
	 * @return
	 */
	String take(Integer courierId,Integer id,String note);
}
