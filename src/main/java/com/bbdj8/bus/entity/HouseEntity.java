package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 小区信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-21 11:59:26
 */
public class HouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//小区ID
	private Integer commId;
	//小区名称
	private String commValue;
	//小区简写；默认拼音首字母
	private String commKey;
	//楼号
	private Integer houseNo;
	//单元
	private Integer unitNo;
	//select2显示值
	private String text;

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
	 * 设置：小区ID
	 */
	public void setCommId(Integer commId) {
		this.commId = commId;
	}
	/**
	 * 获取：小区ID
	 */
	public Integer getCommId() {
		return commId;
	}
	/**
	 * 设置：小区名称
	 */
	public void setCommValue(String commValue) {
		this.commValue = commValue;
	}
	/**
	 * 获取：小区名称
	 */
	public String getCommValue() {
		return commValue;
	}
	/**
	 * 设置：小区简写；默认拼音首字母
	 */
	public void setCommKey(String commKey) {
		this.commKey = commKey;
	}
	/**
	 * 获取：小区简写；默认拼音首字母
	 */
	public String getCommKey() {
		return commKey;
	}
	/**
	 * 设置：楼号
	 */
	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}
	/**
	 * 获取：楼号
	 */
	public Integer getHouseNo() {
		return houseNo;
	}
	/**
	 * 设置：单元
	 */
	public void setUnitNo(Integer unitNo) {
		this.unitNo = unitNo;
	}
	/**
	 * 获取：单元
	 */
	public Integer getUnitNo() {
		return unitNo;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
