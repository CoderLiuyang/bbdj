package com.bbdj8.bus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bbdj8.bus.dao.HouseDao;
import com.bbdj8.bus.entity.HouseEntity;
import com.bbdj8.bus.service.HouseService;



@Service("houseService")
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseDao houseDao;
	
	@Override
	public HouseEntity queryObject(Integer id){
		return houseDao.queryObject(id);
	}
	
	@Override
	public List<HouseEntity> queryList(Map<String, Object> map){
		return houseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return houseDao.queryTotal(map);
	}
	
	@Override
	public void save(HouseEntity house) {
		String text = house.getCommKey() + "-" + house.getHouseNo() + "-" + house.getCommValue() + house.getHouseNo()
				+ "号楼" + house.getUnitNo() + "单元";
		house.setText(text);
		houseDao.save(house);
	}
	
	@Override
	public void update(HouseEntity house) {
		String text = house.getCommKey() + "-" + house.getHouseNo() + "-" + house.getCommValue() + house.getHouseNo()
				+ "号楼" + house.getUnitNo() + "单元";
		house.setText(text);
		houseDao.update(house);
	}
	
	@Override
	public void delete(Integer id){
		houseDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		houseDao.deleteBatch(ids);
	}
	
}
