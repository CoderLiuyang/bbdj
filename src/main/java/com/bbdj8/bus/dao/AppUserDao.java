package com.bbdj8.bus.dao;

import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.sys.dao.BaseDao;

/**
 * 用户表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:12
 */
public interface AppUserDao extends BaseDao<AppUserEntity> {

	AppUserEntity findByTel(String tel);
	
}
