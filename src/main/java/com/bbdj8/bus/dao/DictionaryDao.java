package com.bbdj8.bus.dao;

import java.util.List;
import java.util.Map;

import com.bbdj8.bus.entity.DictionaryEntity;
import com.bbdj8.sys.dao.BaseDao;

/**
 * 字典表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-13 11:33:07
 */
public interface DictionaryDao extends BaseDao<DictionaryEntity> {

	/**
	 * 根据类型查询下属数据字典数据
	 * 
	 * @param param
	 */
	List<DictionaryEntity> findByType(Map<String, String> param);

	/**
	 * 所有类型
	 * @return
	 */
	List<DictionaryEntity> findType();

}
