package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 发件信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-27 17:46:18
 */
public class ExpressOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//发件人电话
	private String sendTel;
	//发件人姓名
	private String sendName;
	//发件地址
	private String sendAddr;
	//收件人电话
	private String receiveTel;
	//收件人姓名
	private String receiveName;
	//收件人地址
	private String receiveAddr;
	//提交时间
	private Date createtime = new Date();
	//快递公司ID
	private Integer expressId;
	//快递公司名字
	private String expressName;
	//快递员ID
	private Integer courierId;
	//快递员名字
	private String courierName;
	//快递员电话
	private String courierTel;
	//重量
	private Double weight;
	//快递员取件时间
	private Date sendtime;
	//保价金额
	private Double valuation;
	//快递单号
	private String expressNum;
	//规格
	private String spec;
	//物品
	private String goods;
	//备注
	private String desc;
	//状态 0-快递员未签收 1-快递员已签收 2-删除 3-提交成功未处理
	private Integer status = 0;
	//快递费
	private Double money;
	//支付类型 0-现金 1-积分 2-积分+现金
	private Integer payType;
	//住址
	private String houseName;
	//住址ID
	private Integer houseId;
	//门牌号
	private String houseNumber;
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
	 * 设置：发件人电话
	 */
	public void setSendTel(String sendTel) {
		this.sendTel = sendTel;
	}
	/**
	 * 获取：发件人电话
	 */
	public String getSendTel() {
		return sendTel;
	}
	/**
	 * 设置：发件人姓名
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	/**
	 * 获取：发件人姓名
	 */
	public String getSendName() {
		return sendName;
	}
	/**
	 * 设置：发件地址
	 */
	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}
	/**
	 * 获取：发件地址
	 */
	public String getSendAddr() {
		return sendAddr;
	}
	/**
	 * 设置：收件人电话
	 */
	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}
	/**
	 * 获取：收件人电话
	 */
	public String getReceiveTel() {
		return receiveTel;
	}
	/**
	 * 设置：收件人姓名
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	/**
	 * 获取：收件人姓名
	 */
	public String getReceiveName() {
		return receiveName;
	}
	/**
	 * 设置：收件人地址
	 */
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	/**
	 * 获取：收件人地址
	 */
	public String getReceiveAddr() {
		return receiveAddr;
	}
	/**
	 * 设置：提交时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：提交时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：快递公司ID
	 */
	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
	}
	/**
	 * 获取：快递公司ID
	 */
	public Integer getExpressId() {
		return expressId;
	}
	/**
	 * 设置：快递公司名字
	 */
	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}
	/**
	 * 获取：快递公司名字
	 */
	public String getExpressName() {
		return expressName;
	}
	/**
	 * 设置：快递员ID
	 */
	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}
	/**
	 * 获取：快递员ID
	 */
	public Integer getCourierId() {
		return courierId;
	}
	/**
	 * 设置：快递员名字
	 */
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	/**
	 * 获取：快递员名字
	 */
	public String getCourierName() {
		return courierName;
	}
	/**
	 * 设置：快递员电话
	 */
	public void setCourierTel(String courierTel) {
		this.courierTel = courierTel;
	}
	/**
	 * 获取：快递员电话
	 */
	public String getCourierTel() {
		return courierTel;
	}
	/**
	 * 设置：重量
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * 获取：重量
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * 设置：快递员取件时间
	 */
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	/**
	 * 获取：快递员取件时间
	 */
	public Date getSendtime() {
		return sendtime;
	}
	/**
	 * 设置：保价金额
	 */
	public void setValuation(Double valuation) {
		this.valuation = valuation;
	}
	/**
	 * 获取：保价金额
	 */
	public Double getValuation() {
		return valuation;
	}
	/**
	 * 设置：快递单号
	 */
	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	/**
	 * 获取：快递单号
	 */
	public String getExpressNum() {
		return expressNum;
	}
	/**
	 * 设置：规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * 获取：规格
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * 设置：物品
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}
	/**
	 * 获取：物品
	 */
	public String getGoods() {
		return goods;
	}
	/**
	 * 设置：备注
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：备注
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：状态 0-快递员未签收 1-快递员已签收 2-删除 3-提交成功未处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0-快递员未签收 1-快递员已签收 2-删除 3-提交成功未处理
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：快递费
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	/**
	 * 获取：快递费
	 */
	public Double getMoney() {
		return money;
	}
	/**
	 * 设置：支付类型 0-现金 1-积分 2-积分+现金
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付类型 0-现金 1-积分 2-积分+现金
	 */
	public Integer getPayType() {
		return payType;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
}
