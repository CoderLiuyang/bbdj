package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 快递员信息表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-22 11:35:28
 */
public class CourierEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//快递员英文名
	private String ename;
	//快递员姓名
	private String name;
	//所属快递公司ID
	private Integer expressId;
	//所属快递公司
	private String express;
	//状态 0-正常 1-删除
	private Integer status;
	//联系电话
	private String tel;
	/**
	 * select2 下拉选择显示文本
	 */
	private String text;
	//身份证号码
	private String idcard;

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：快递员英文名
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：快递员英文名
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * 设置：快递员姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：快递员姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：所属快递公司ID
	 */
	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}
	/**
	 * 获取：所属快递公司ID
	 */
	public Integer getExpressId() {
		return expressId;
	}
	/**
	 * 设置：所属快递公司
	 */
	public void setExpress(String express) {
		this.express = express;
	}
	/**
	 * 获取：所属快递公司
	 */
	public String getExpress() {
		return express;
	}
	/**
	 * 设置：状态 0-正常 1-删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0-正常 1-删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：联系电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @return
	 */
	public String getText() {
		return this.ename+"-"+this.name;
	}
	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = this.ename+"-"+this.name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
}
