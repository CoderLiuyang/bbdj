package com.bbdj8.bus.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdj8.bus.dao.ExpressOrderDao;
import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.bus.entity.CourierEntity;
import com.bbdj8.bus.entity.ExpressOrderEntity;
import com.bbdj8.bus.entity.IntegralLogEntity;
import com.bbdj8.bus.service.AppUserService;
import com.bbdj8.bus.service.CourierService;
import com.bbdj8.bus.service.DictionaryService;
import com.bbdj8.bus.service.ExpressOrderService;
import com.bbdj8.bus.service.IntegralLogService;
import com.bbdj8.bus.service.MessageLogService;



@Service("expressOrderService")
public class ExpressOrderServiceImpl implements ExpressOrderService {
	@Autowired
	private ExpressOrderDao expressOrderDao;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private IntegralLogService integralLogService;
	
	@Autowired
	private CourierService courierService;
	
	@Autowired
	private MessageLogService messageService;
	@Override
	public ExpressOrderEntity queryObject(Integer id){
		return expressOrderDao.queryObject(id);
	}
	
	@Override
	public List<ExpressOrderEntity> queryList(Map<String, Object> map){
		return expressOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return expressOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(ExpressOrderEntity expressOrder){
		expressOrderDao.save(expressOrder);
		//保存用户信息
		if (StringUtils.isNotBlank(expressOrder.getSendTel())) {
			//保存用户
			appUserService.saveByExpressOrder(expressOrder);
		}
		
	}
	
	@Override
	public void update(ExpressOrderEntity expressOrder) {
		expressOrderDao.update(expressOrder);
		// 保存用户信息
		if (StringUtils.isNotBlank(expressOrder.getSendTel())) {
			// 保存用户
			appUserService.saveByExpressOrder(expressOrder);
		}
	}
	
	@Override
	public void delete(Integer id){
		expressOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		expressOrderDao.deleteBatch(ids);
	}

	@Override
	public String take(Integer courierId, Integer id, String note) {
		String message = "操作成功";
		ExpressOrderEntity order = this.queryObject(id);
		if (order != null) {
			// 快递员
			CourierEntity courier = courierService.queryObject(courierId);
			order.setCourierId(courierId);
			order.setCourierName(courier.getName());
			order.setCourierTel(courier.getTel());
			order.setDesc(note);
			order.setStatus(1);
			expressOrderDao.update(order);
			this.calcIntegral(order);
		} else {
			message = "发件信息不存在";
		}
		return message;
	}
	/**
	 * 计算积分信息
	 * @param order
	 */
	private void calcIntegral(ExpressOrderEntity order) {
		int tmp_integral = 3;
		// 修改积分信息，发件+3积分
		AppUserEntity user = appUserService.findByTel(order.getSendTel());
		if (user != null) {
			user.setIntegral(user.getIntegral().intValue() + tmp_integral);
			appUserService.update(user);
		}
		// 添加日志
		IntegralLogEntity entity = new IntegralLogEntity();
		entity.setCreatetime(new Date());
		entity.setGoodsId(order.getId());
		entity.setGoodsNo(order.getExpressNum());
		entity.setIntegral(tmp_integral);
		entity.setNote("发件");
		entity.setUserId(user.getId());
		entity.setUserName(user.getName());
		entity.setTel(user.getTel());
		integralLogService.save(entity);
	}
}
