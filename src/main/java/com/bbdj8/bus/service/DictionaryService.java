package com.bbdj8.bus.service;

import com.bbdj8.bus.entity.DictionaryEntity;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-13 11:33:07
 */
public interface DictionaryService {
	
	DictionaryEntity queryObject(Integer id);
	
	List<DictionaryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DictionaryEntity bbdjDictionary);
	
	void update(DictionaryEntity bbdjDictionary);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
	 * 根据数据字典的类型查询数据下属数据字典数据
	 * @param key 键
	 * @param value 指
	 * @return
	 */
	List<DictionaryEntity> findByType(String key,String value);

	/**
	 * 所有类型
	 * @return
	 */
	List<DictionaryEntity> findType();
}
