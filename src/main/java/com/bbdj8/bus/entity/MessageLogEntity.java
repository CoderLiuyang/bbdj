package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息发送日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
public class MessageLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//消息类型
	private String type;
	//发送状态
	private String status;
	//消息
	private String message;

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
	 * 设置：消息类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：消息类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：发送状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：发送状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：消息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：消息
	 */
	public String getMessage() {
		return message;
	}
}
