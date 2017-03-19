package com.bbdj8.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbdj8.bus.entity.GoodsEntity;
import com.bbdj8.bus.service.DictionaryService;
import com.bbdj8.bus.service.GoodsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring-context.xml" })
public class AppTestBean {
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private GoodsService goodsService;
	@Test
	public void testMethod() {
//		List<DictionaryEntity> list = dictionaryService.findByType("express", "快递公司");
//		for (DictionaryEntity entity : list) {
//			System.err.println(entity.getValue());
//		}
		Map<String,Object> map = new HashMap<>();
		map.put("status","0");
		List<GoodsEntity> list = goodsService.queryList(map);
		System.out.println(list.size());
	}
}
