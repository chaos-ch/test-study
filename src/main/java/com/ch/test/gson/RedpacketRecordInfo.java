package com.ch.test.gson;

import java.io.Serializable;

/**
 * User: ralf.cao
 * Date: 14-4-23 下午10:11
 */
public class RedpacketRecordInfo implements Serializable{
	/**
	 * 用户ID *
	 */
	private String userId;
	/**
	 * wrapperId *
	 */
	private String wrapperId;
	/**
	 * 红包数量 *
	 */
	private String redpacketCount;
	/**
	 * 多返的返现金额 *
	 */
	private String returnAmount;
	/**
	 * 直降的价格 *
	 */
	private String lapsePrice;
	/**
	 * 红包流水号 *
	 */
	private String redpacketNo;
	/**
	 * 给客户展示的红包备注信息 *
	 */
	private String remark;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWrapperId() {
		return wrapperId;
	}

	public void setWrapperId(String wrapperId) {
		this.wrapperId = wrapperId;
	}

	public String getRedpacketCount() {
		return redpacketCount;
	}

	public void setRedpacketCount(String redpacketCount) {
		this.redpacketCount = redpacketCount;
	}

	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getLapsePrice() {
		return lapsePrice;
	}

	public void setLapsePrice(String lapsePrice) {
		this.lapsePrice = lapsePrice;
	}

	public String getRedpacketNo() {
		return redpacketNo;
	}

	public void setRedpacketNo(String redpacketNo) {
		this.redpacketNo = redpacketNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
