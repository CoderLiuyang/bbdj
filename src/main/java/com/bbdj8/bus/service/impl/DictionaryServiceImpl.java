package com.bbdj8.bus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdj8.bus.dao.DictionaryDao;
import com.bbdj8.bus.entity.DictionaryEntity;
import com.bbdj8.bus.service.DictionaryService;



@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Override
	public DictionaryEntity queryObject(Integer id){
		return dictionaryDao.queryObject(id);
	}
	
	@Override
	public List<DictionaryEntity> queryList(Map<String, Object> map){
		return dictionaryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dictionaryDao.queryTotal(map);
	}
	
	@Override
	public void save(DictionaryEntity bbdjDictionary){
		dictionaryDao.save(bbdjDictionary);
	}
	
	@Override
	public void update(DictionaryEntity bbdjDictionary){
		dictionaryDao.update(bbdjDictionary);
	}
	
	@Override
	public void delete(Integer id){
		dictionaryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dictionaryDao.deleteBatch(ids);
	}

	@Override
	public List<DictionaryEntity> findByType(String key, String value) {
		Map<String, String> param = new HashMap<>();
		param.put("key", key);
		param.put("value", value);
		return dictionaryDao.findByType(param);
	}

	@Override
	public List<DictionaryEntity> findType() {
		return dictionaryDao.findType();
	}
	
}
