package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 积分变化日志表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-19 11:27:24
 */
public class IntegralLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//快递单号
	private String goodsNo;
	//变化的积分，正数加 负数减
	private Integer integral;
	//消费时间
	private Date createtime;
	//消费备注
	private String note;
	//账户ID
	private Integer userId;
	//用户电话号码
	private String tel;
	//用户姓名
	private String userName;
	//快件ID
	private Integer goodsId;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：快递单号
	 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	/**
	 * 获取：快递单号
	 */
	public String getGoodsNo() {
		return goodsNo;
	}
	/**
	 * 设置：变化的积分，正数加 负数减
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**
	 * 获取：变化的积分，正数加 负数减
	 */
	public Integer getIntegral() {
		return integral;
	}
	/**
	 * 设置：消费时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：消费时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：消费备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：消费备注
	 */
	public String getNote() {
		return note;
	}
	/**
	 * 设置：账户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：账户ID
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：用户电话号码
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：用户电话号码
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
}
