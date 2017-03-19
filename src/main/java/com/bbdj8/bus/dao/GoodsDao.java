package com.bbdj8.bus.dao;

import java.util.Map;

import com.bbdj8.bus.entity.GoodsEntity;
import com.bbdj8.sys.dao.BaseDao;

/**
 * 快递货物信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:13
 */
public interface GoodsDao extends BaseDao<GoodsEntity> {

	void updateBatch(Map<String, Object> map);

	GoodsEntity findByGoodsNo(Object value);
	
}
