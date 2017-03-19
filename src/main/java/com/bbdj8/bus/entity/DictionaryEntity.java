package com.bbdj8.bus.entity;

import java.io.Serializable;



/**
 * 字典表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-13 11:33:07
 */
public class DictionaryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//key
	private String key;
	//值
	private String value;
	//字段类型key
	private String typeKey;
	//字典类型值
	private String typeValue;
	
	/**
	 * 显示文本
	 */
	private String text;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：字段类型key
	 */
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
	/**
	 * 获取：字段类型key
	 */
	public String getTypeKey() {
		return typeKey;
	}
	/**
	 * 设置：字典类型值
	 */
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	/**
	 * 获取：字典类型值
	 */
	public String getTypeValue() {
		return typeValue;
	}
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return this.key+"-"+this.value;
	}
	/**
	 * @param text
	 */
	public void setText(String text) {
		this.text = this.key+"-"+this.value;
	}
}
