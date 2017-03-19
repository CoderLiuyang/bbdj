package com.bbdj8.bus.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.bbdj8.bus.entity.GoodsEntity;

/**
 * 快递货物信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:13
 */
public interface GoodsService {
	
	GoodsEntity queryObject(Integer id);
	
	List<GoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsEntity goods);
	
	void update(GoodsEntity goods);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	/**
	 * 快件批量改为已通知状态，然后消息通知用户
	 * @param goods
	 */
	void notice(GoodsEntity goods);
	
	/**
	 * 计算积分
	 * @param goods
	 */
	void calcIntegral(GoodsEntity goods);
	
	/**
	 * 批量更新状态
	 * @param ids
	 * @param status
	 */
	void updateBatch(Integer[] ids,int status);

	/**
	 * 快件处理
	 * @param id
	 * @param status
	 * @param getname 取件人
	 * @exception Exception
	 * @return
	 */
	String receiving(Integer id, Integer status,String getname,String getnote) throws Exception;
	
	/**
	 * 根据快递单号查询
	 * @param goodsNo
	 * @return
	 */
	GoodsEntity findByGoodsNo(String goodsNo);

	/**
	 * 导出
	 * @param map
	 * @return
	 */
	HSSFWorkbook excel(Map<String, Object> map);

}
