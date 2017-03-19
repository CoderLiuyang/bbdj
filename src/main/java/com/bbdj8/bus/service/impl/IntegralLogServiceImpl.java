package com.bbdj8.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bbdj8.bus.dao.IntegralLogDao;
import com.bbdj8.bus.entity.IntegralLogEntity;
import com.bbdj8.bus.service.IntegralLogService;



@Service("integralLogService")
public class IntegralLogServiceImpl implements IntegralLogService {
	@Autowired
	private IntegralLogDao integralLogDao;
	
	@Override
	public IntegralLogEntity queryObject(Integer id){
		return integralLogDao.queryObject(id);
	}
	
	@Override
	public List<IntegralLogEntity> queryList(Map<String, Object> map){
		return integralLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return integralLogDao.queryTotal(map);
	}
	
	@Override
	public void save(IntegralLogEntity integralLog){
		integralLogDao.save(integralLog);
	}
	
	@Override
	public void update(IntegralLogEntity integralLog){
		integralLogDao.update(integralLog);
	}
	
	@Override
	public void delete(Integer id){
		integralLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		integralLogDao.deleteBatch(ids);
	}
	
}
