package com.bbdj8.bus.entity;

import java.io.Serializable;
import java.util.Date;

import com.bbdj8.sys.utils.DateUtils;



/**
 * 快递货物信息
 * 
 * @author liwenjun
 * @email 18623682292@163.com
 * @date 2017-01-15 22:43:13
 */
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer id;
	//快递单号
	private String goodsNo;
	//波次号
	private String handoverNo;
	//包装类型ID
	private String packagesId;
	//包装类型
	private String packages;
	//收件人ID
	private Integer appuserId;
	//收件人姓名
	private String userName;
	//收件人电话
	private String tel;
	//状态  5-删除  0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货
	private Integer status = 0;
	//存放货架
	private String shelves;
	//快递公司
	private String express;
	//快递公司ID
	private Integer expressId;
	//快递员ID
	private Integer courierId;
	//快递员电话
	private String courierTel;
	//快递员姓名
	private String courierName;
	//物品
	private String goods;
	//重量
	private Double weight;
	//图片
	private String img;
	//收件时间
	private Date createtime = new Date();;
	//取件时间
	private Date gettime;
	//取件人
	private String getname;
	//规格
	private String spec;
	//是否破损 0-没有 1-破损
	private Integer isBroken;
	//住址
	private String houseName;
	//住址ID
	private Integer houseId;
	//门牌号
	private String houseNumber;
	//操作人姓名
	private String operationName;
	//操作人ID
	private Long operationId;
	//修改人姓名
	private String updateName;
	//修改人ID
	private Long updateId;
	//修改时间
	private Date updateDate;
	//扣取的积分
	private int price = 0;
	//收件备注
	private String receivenote;
	//取件备注
	private String getnote;
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
	 * 设置：波次号
	 */
	public void setHandoverNo(String handoverNo) {
		this.handoverNo = handoverNo;
	}
	/**
	 * 获取：波次号
	 */
	public String getHandoverNo() {
		return handoverNo;
	}
	/**
	 * 设置：包装类型
	 */
	public void setPackages(String packages) {
		this.packages = packages;
	}
	/**
	 * 获取：包装类型
	 */
	public String getPackages() {
		return packages;
	}
	/**
	 * 设置：收件人ID
	 */
	public void setAppuserId(Integer appuserId) {
		this.appuserId = appuserId;
	}
	/**
	 * 获取：收件人ID
	 */
	public Integer getAppuserId() {
		return appuserId;
	}
	/**
	 * 设置：收件人姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：收件人姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：收件人电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：收件人电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：状态 0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货  5-删除 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货  5-删除 
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：存放货架
	 */
	public void setShelves(String shelves) {
		this.shelves = shelves;
	}
	/**
	 * 获取：存放货架
	 */
	public String getShelves() {
		return shelves;
	}
	/**
	 * 设置：快递公司
	 */
	public void setExpress(String express) {
		this.express = express;
	}
	/**
	 * 获取：快递公司
	 */
	public String getExpress() {
		return express;
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
	 * 设置：快递员姓名
	 */
	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}
	/**
	 * 获取：快递员姓名
	 */
	public String getCourierName() {
		return courierName;
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
	 * 设置：图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：收件时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：收件时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：取件时间
	 */
	public void setGettime(Date gettime) {
		this.gettime = gettime;
	}
	/**
	 * 获取：取件时间
	 */
	public Date getGettime() {
		return gettime;
	}
	/**
	 * 设置：取件人
	 */
	public void setGetname(String getname) {
		this.getname = getname;
	}
	/**
	 * 获取：取件人
	 */
	public String getGetname() {
		return getname;
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
	 * 设置：是否破损 0-没有 1-破损
	 */
	public void setIsBroken(Integer isBroken) {
		this.isBroken = isBroken;
	}
	/**
	 * 获取：是否破损 0-没有 1-破损
	 */
	public Integer getIsBroken() {
		return isBroken;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public Integer getExpressId() {
		return expressId;
	}
	public void setExpressId(Integer expressId) {
		this.expressId = expressId;
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
	public String getCourierTel() {
		return courierTel;
	}
	public void setCourierTel(String courierTel) {
		this.courierTel = courierTel;
	}
	public String getPackagesId() {
		return packagesId;
	}
	public void setPackagesId(String packagesId) {
		this.packagesId = packagesId;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public Long getOperationId() {
		return operationId;
	}
	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	public Long getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getPrice() {
		int price = 0;
		if (this.createtime != null) {
			price = DateUtils.getMonthSpace(this.createtime, new Date());
		}
		return price;
	}

	public void setPrice(int price) {
		if (this.createtime != null) {
			price = DateUtils.getMonthSpace(this.createtime, new Date());
		}
		this.price = price;
	}
	public String getReceivenote() {
		return receivenote;
	}
	public void setReceivenote(String receivenote) {
		this.receivenote = receivenote;
	}
	public String getGetnote() {
		return getnote;
	}
	public void setGetnote(String getnote) {
		this.getnote = getnote;
	}
}
