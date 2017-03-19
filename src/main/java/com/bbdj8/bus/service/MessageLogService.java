package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.MessageLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息发送日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
public interface MessageLogService {
	
	MessageLogEntity queryObject(Integer id);
	
	List<MessageLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MessageLogEntity messageLog);
	
	void update(MessageLogEntity messageLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
