package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户表
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:12
 */
public class AppUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//电话号码
	private String tel;
	//姓名
	private String name;
	//微信openid
	private String openid;
	//住址
	private String houseName;
	//门牌号
	private String houseNum;
	//积分
	private Integer integral;
	//昵称
	private String nickname;
	//收件人英文名，默认姓名首字母
	private String ename;
	//住址ID
	private Integer houseId;
	//创建时间
	private Date createtime = new Date();

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
	 * 设置：电话号码
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：电话号码
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：微信openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：住址
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	/**
	 * 获取：住址
	 */
	public String getHouseName() {
		return houseName;
	}
	/**
	 * 设置：门牌号
	 */
	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	/**
	 * 获取：门牌号
	 */
	public String getHouseNum() {
		return houseNum;
	}
	/**
	 * 设置：积分
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**
	 * 获取：积分
	 */
	public Integer getIntegral() {
		return integral;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：收件人英文名，默认姓名首字母
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * 获取：收件人英文名，默认姓名首字母
	 */
	public String getEname() {
		return ename;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
