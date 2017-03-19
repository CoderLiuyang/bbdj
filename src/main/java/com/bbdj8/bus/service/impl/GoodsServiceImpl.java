package com.bbdj8.bus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdj8.bus.dao.GoodsDao;
import com.bbdj8.bus.entity.AppUserEntity;
import com.bbdj8.bus.entity.GoodsEntity;
import com.bbdj8.bus.entity.IntegralLogEntity;
import com.bbdj8.bus.entity.MessageLogEntity;
import com.bbdj8.bus.service.AppUserService;
import com.bbdj8.bus.service.GoodsService;
import com.bbdj8.bus.service.IntegralLogService;
import com.bbdj8.bus.service.MessageLogService;
import com.bbdj8.bus.utils.Constant;
import com.bbdj8.bus.utils.MessageUtil;
import com.bbdj8.sys.utils.DateUtils;
import com.bbdj8.sys.utils.ShiroUtils;



@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private IntegralLogService integralLogService;
	@Autowired
	private MessageLogService messageService;
	@Override
	public GoodsEntity queryObject(Integer id){
		return goodsDao.queryObject(id);
	}
	
	@Override
	public List<GoodsEntity> queryList(Map<String, Object> map){
		return goodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsEntity goods){
		String tel = goods.getTel();
		if (StringUtils.isNotBlank(tel)) {
			//保存用户
			appUserService.saveByGoods(goods);
			goods.setOperationId(ShiroUtils.getUserId());
			goods.setOperationName(ShiroUtils.getUserEntity().getUsername());
			goodsDao.save(goods);
		}
	}
	
	@Override
	public void update(GoodsEntity goods) {
		try {
			appUserService.saveByGoods(goods);
			goods.setUpdateId(ShiroUtils.getUserId());
			goods.setUpdateName(ShiroUtils.getUserEntity().getUsername());
			goods.setUpdateDate(new Date());
			goodsDao.update(goods);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void delete(Integer id){
		goodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		goodsDao.deleteBatch(ids);
	}

	@Override
	public void notice(GoodsEntity goods) {
		String tel = goods.getTel();
		if (StringUtils.isNotBlank(tel)) {
			// 电话不是空，保存用户
			appUserService.saveByGoods(goods);
			// 查询当前快件的收件号码，微信信息,发送消息
			String result = MessageUtil.sendSms(tel, Constant.SMS_MESSAGE);
			// TODO 保存消息发送日志
			MessageLogEntity messageLog = new MessageLogEntity();
			messageLog.setMessage(result);
			messageLog.setStatus("成功");
			messageLog.setType("短信");
			messageService.save(messageLog);
		}
	}

	/**
	 * TODO 并发是有问题，后续处理
	 * 计算积分
	 * @param goods
	 */
	public void calcIntegral(GoodsEntity goods) {
		//电话号码
		AppUserEntity user = appUserService.findByTel(goods.getTel());
		if (user != null) {
			// 状态 0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货 5-删除
			Integer integral = user.getIntegral();
			int tmp_integral = 0;
			String note = "";
			switch (goods.getStatus()) {
			case 2:// 加积分
				if (goods.getPrice() > 0) {
					tmp_integral = -goods.getPrice();
				}else{
					tmp_integral = 1;
				}
				note = "签收快件";
				break;
//			case 3:// 减积分
//				tmp_integral = -goods.getPrice();
//				note = "延迟签收";
//				break;
			case 4:// TODO 减积分
				tmp_integral = -goods.getPrice();
				note = "退件";
				break;
			default:
				break;
			}
			user.setIntegral(integral + tmp_integral);
			// 保存用户
			appUserService.update(user);
			// TODO 保存积分变更日志
			IntegralLogEntity entity = new IntegralLogEntity();
			entity.setCreatetime(new Date());
			entity.setGoodsId(goods.getId());
			entity.setGoodsNo(goods.getGoodsNo());
			entity.setIntegral(tmp_integral);
			entity.setNote(note);
			entity.setUserId(user.getId());
			entity.setUserName(user.getName());
			entity.setTel(user.getTel());
			integralLogService.save(entity);
		}
	}

	@Override
	public void updateBatch(Integer[] ids, int status) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("ids", ids);
		param.put("status", status);
		goodsDao.updateBatch(param);
	}

	@Override
	public String receiving(Integer id, Integer status,String getname,String getnote) throws Exception{
		String result = "操作成功";
		GoodsEntity goods = goodsDao.queryObject(id);
		//5-删除  0-未签收 1-已通知未签收 2-签收 3-延迟签收 4-拒收退货
		if (goods != null) {
			if (StringUtils.isNotBlank(getnote)) {
				goods.setGetnote(getnote);
			}
			try {
				switch (status) {
				case 1://
					goods.setStatus(1);
					this.notice(goods);
					result = "通知成功";
					break;
				case 2:
					goods.setStatus(2);
					goods.setGetname(getname);
					goods.setGettime(new Date());
					this.calcIntegral(goods);
					result = "签收快件成功";
					break;
				case 3:
					goods.setStatus(3);
					this.calcIntegral(goods);
					result = "延迟签收快件成功";
					break;
				case 4:
					goods.setStatus(4);
					this.calcIntegral(goods);
					result = "退件成功";
					break;
				default:
					break;
				}
				goodsDao.update(goods);
			} catch (Exception e) {
				throw e;
			}
		} else {
			result = "快件不存在";
		}
		return result;
	}

	@Override
	public GoodsEntity findByGoodsNo(String goodsNo) {
		return this.goodsDao.findByGoodsNo(goodsNo);
	}

	@Override
	public HSSFWorkbook excel(Map<String, Object> map) {
		List<GoodsEntity> list = this.queryList(map);
		HSSFWorkbook workbook = new HSSFWorkbook();
		if (list.size() > 0) {
			GoodsEntity entity = list.get(0);
			HSSFSheet sheet = workbook.createSheet();
			sheet.addMergedRegion(new Region(0, // first row (0-based)
					(short) 0, // first column (0-based)
					0, // last row (0-based)
					(short) 4 // last column (0-based)
			));
			sheet.addMergedRegion(new Region(1, // first row (0-based)
					(short) 0, // first column (0-based)
					1, // last row (0-based)
					(short) 4 // last column (0-based)
			));
			sheet.autoSizeColumn((short) 1, true);
			HSSFRow titleRow1 = sheet.createRow(0);
			String title1 = "快递公司：" + entity.getExpress() + "	波次号：" + entity.getHandoverNo() + "	共计：" + list.size()
					+ "件";
			titleRow1.createCell((short) 0).setCellValue(title1);
			HSSFRow titleRow2 = sheet.createRow(1);
			String dt = DateUtils.format(entity.getCreatetime(), "yyyy-MM-dd HH");
			String title2 = "交接人：管理员	打印日期：" + dt + "点";
			titleRow2.createCell((short) 0).setCellValue(title2);
			;
			HSSFRow titleRow3 = sheet.createRow(2);
			String[] titleS = new String[] { "序号", "快递单号", "收件人", "收件人电话", "住址" };
			for (int i = 0; i < titleS.length; i++) {
				String str = titleS[i];
				HSSFCell cell = titleRow3.createCell((short) i);
				cell.setCellValue(str);

			}
			for (int i = 0; i < list.size(); i++) {
				GoodsEntity goods = list.get(i);
				HSSFRow dataRow = sheet.createRow(i + 3);
				HSSFCell id = dataRow.createCell((short) 0);
				id.setCellValue(i + 1);
				HSSFCell goodsNo = dataRow.createCell((short) 1);
				goodsNo.setCellValue(goods.getGoodsNo());
				HSSFCell uname = dataRow.createCell((short) 2);
				uname.setCellValue(goods.getUserName());
				HSSFCell tel = dataRow.createCell((short) 3);
				tel.setCellValue(goods.getTel());
				HSSFCell house = dataRow.createCell((short) 4);
				house.setCellValue(goods.getHouseNumber());
			}
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_DOTTED); // 下边框
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_DOTTED);// 左边框
			cellStyle.setBorderTop(HSSFCellStyle.BORDER_DOTTED);// 上边框
			cellStyle.setBorderRight(HSSFCellStyle.BORDER_DOTTED);// 右边框
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		}
		return workbook;
	}
}
