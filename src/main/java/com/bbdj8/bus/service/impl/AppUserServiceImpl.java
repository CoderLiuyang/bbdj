package com.bbdj8.bus.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdj8.bus.dao.AppUserDao;
import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.bus.entity.ExpressOrderEntity;
import com.bbdj8.bus.entity.GoodsEntity;
import com.bbdj8.bus.service.AppUserService;
import com.bbdj8.bus.utils.PinyinUtil;



@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserDao appUserDao;
	
	@Override
	public AppUserEntity queryObject(Integer id){
		return appUserDao.queryObject(id);
	}
	
	@Override
	public List<AppUserEntity> queryList(Map<String, Object> map){
		return appUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appUserDao.queryTotal(map);
	}
	
	@Override
	public void save(AppUserEntity appUser){
		appUserDao.save(appUser);
	}
	
	@Override
	public void update(AppUserEntity appUser){
		appUserDao.update(appUser);
	}
	
	@Override
	public void delete(Integer id){
		appUserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		appUserDao.deleteBatch(ids);
	}

	@Override
	public AppUserEntity findByTel(String tel) {
		return appUserDao.findByTel(tel);
	}

	@Override
	public void saveByGoods(GoodsEntity goods) {
		String tel = goods.getTel();
		AppUserEntity user = this.findByTel(tel);
		if (user == null) {// 新增
			user = new AppUserEntity();
			user.setTel(tel);
			user.setName(goods.getUserName());
			user.setEname(PinyinUtil.getFirstSpell(goods.getUserName()));
			user.setHouseName(goods.getHouseName());
			user.setHouseId(goods.getHouseId());
			user.setHouseNum(goods.getHouseNumber());
			user.setNickname(goods.getUserName());
			user.setIntegral(0);
			this.save(user);
		} else {
			// 更新住址
			user.setHouseName(goods.getHouseName());
			user.setHouseId(goods.getHouseId());
			user.setHouseNum(goods.getHouseNumber());
			user.setName(goods.getUserName());
			user.setEname(PinyinUtil.getFirstSpell(goods.getUserName()));
			this.update(user);
		}
	}

	@Override
	public void saveByExpressOrder(ExpressOrderEntity expressOrder) {
		String tel = expressOrder.getSendTel();
		AppUserEntity user = this.findByTel(tel);
		if (user == null) {// 新增
			user = new AppUserEntity();
			user.setTel(tel);
			user.setName(expressOrder.getSendName());
			user.setEname(PinyinUtil.getFirstSpell(expressOrder.getSendName()));
			user.setHouseName(expressOrder.getHouseName());
			user.setHouseId(expressOrder.getHouseId());
			user.setHouseNum(expressOrder.getHouseNumber());
			user.setNickname(expressOrder.getSendName());
			user.setIntegral(0);
			this.save(user);
		} else {
			// 更新住址
			user.setHouseName(expressOrder.getHouseName());
			user.setHouseId(expressOrder.getHouseId());
			user.setHouseNum(expressOrder.getHouseNumber());
			this.update(user);
		}

	}
	
}
