package com.bbdj8.bus.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdj8.bus.dao.CourierDao;
import com.bbdj8.bus.entity.CourierEntity;
import com.bbdj8.bus.service.CourierService;
import com.bbdj8.bus.utils.PinyinUtil;



@Service("courierService")
public class CourierServiceImpl implements CourierService {
	@Autowired
	private CourierDao courierDao;
	
	@Override
	public CourierEntity queryObject(Integer id){
		return courierDao.queryObject(id);
	}
	
	@Override
	public List<CourierEntity> queryList(Map<String, Object> map){
		return courierDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return courierDao.queryTotal(map);
	}
	
	@Override
	public void save(CourierEntity courier) {
		// 英文名为空
		if (StringUtils.isNotBlank(courier.getEname())) {
			courier.setEname(PinyinUtil.getFirstSpell(courier.getEname()));
		}
		courierDao.save(courier);
	}
	
	@Override
	public void update(CourierEntity courier) {
		// 英文名为空
		if (StringUtils.isNotBlank(courier.getEname())) {
			courier.setEname(PinyinUtil.getFirstSpell(courier.getEname()));
		}
		courierDao.update(courier);
	}
	
	@Override
	public void delete(Integer id){
		courierDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		courierDao.deleteBatch(ids);
	}
	
}
