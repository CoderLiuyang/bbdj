package com.bbdj8.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bbdj8.bus.dao.MessageLogDao;
import com.bbdj8.bus.entity.MessageLogEntity;
import com.bbdj8.bus.service.MessageLogService;



@Service("messageLogService")
public class MessageLogServiceImpl implements MessageLogService {
	@Autowired
	private MessageLogDao messageLogDao;
	
	@Override
	public MessageLogEntity queryObject(Integer id){
		return messageLogDao.queryObject(id);
	}
	
	@Override
	public List<MessageLogEntity> queryList(Map<String, Object> map){
		return messageLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return messageLogDao.queryTotal(map);
	}
	
	@Override
	public void save(MessageLogEntity messageLog){
		messageLogDao.save(messageLog);
	}
	
	@Override
	public void update(MessageLogEntity messageLog){
		messageLogDao.update(messageLog);
	}
	
	@Override
	public void delete(Integer id){
		messageLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		messageLogDao.deleteBatch(ids);
	}
	
}
