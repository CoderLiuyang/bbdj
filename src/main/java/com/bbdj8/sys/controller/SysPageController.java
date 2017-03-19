package com.bbdj8.sys.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbdj8.sys.utils.DateUtils;

/**
 * 系统页面视图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	/**
	 * 基础系统功能page sys
	 * @param url
	 * @return
	 */
	@RequestMapping("sys/{url}.html")
	public String sysPage(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
	/**
	 * 业务功能  bus
	 * @param url
	 * @return
	 */
	@RequestMapping("{bus}/{url}.html")
	public String busPage(@PathVariable("url") String url,@PathVariable("bus") String bus){
		return "bus/"+bus +"/"+ url + ".html";
	}
	/**
	 * 获取系统当前时间 yyyyMMddHHmmss
	 * @return
	 */
	@RequestMapping("sys/time")
	@ResponseBody
	public String systime(){
		return DateUtils.format(new Date(), "yyyyMMddHHmmss");
	}
}
