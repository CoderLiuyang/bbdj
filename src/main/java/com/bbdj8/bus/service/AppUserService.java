package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.bus.entity.ExpressOrderEntity;
import com.bbdj8.bus.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:12
 */
public interface AppUserService {
	
	AppUserEntity queryObject(Integer id);
	
	List<AppUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppUserEntity appUser);
	
	void update(AppUserEntity appUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 根据电话号码查询用户
	 * @param tel
	 * @return
	 */
	AppUserEntity findByTel(String tel);
	/**
	 * 根据收件信息保存
	 * @param goods
	 */
	void saveByGoods(GoodsEntity goods);

	/**
	 * 根据发件信息保存
	 * @param expressOrder
	 */
	void saveByExpressOrder(ExpressOrderEntity expressOrder);
}
