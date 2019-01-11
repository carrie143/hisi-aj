package com.hisi.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 运单信息vo
 */
@ApiModel(description = "运单信息")
public class HisiTrackInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4967248350975847382L;

	@ApiModelProperty(value = "运单id")
	private Integer id;

	@ApiModelProperty(value = "运单号")
	private String trackingNumber;

	@ApiModelProperty(value = "运单货物件数")
	private Integer num;

	@ApiModelProperty(value = "航班号")
	private String flightNo;

	@ApiModelProperty(value = "安检人")
	private String verifyPerson;

	@ApiModelProperty(value = "承运人")
	private String carryPerson;
	
	@ApiModelProperty(value = "代理人")
	private String proxyName;
	
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	@ApiModelProperty(value = "开始时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@ApiModelProperty(value = "最近开始时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastStartTime;

	@ApiModelProperty(value = "最近暂停时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastPauseTime;

	@ApiModelProperty(value = "运单图片")
	private List<String> pictures;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getVerifyPerson() {
		return verifyPerson;
	}

	public void setVerifyPerson(String verifyPerson) {
		this.verifyPerson = verifyPerson;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCarryPerson() {
		return carryPerson;
	}

	public void setCarryPerson(String carryPerson) {
		this.carryPerson = carryPerson;
	}

	public String getProxyName() {
		return proxyName;
	}

	public void setProxyName(String proxyName) {
		this.proxyName = proxyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getLastStartTime() {
		return lastStartTime;
	}

	public void setLastStartTime(Date lastStartTime) {
		this.lastStartTime = lastStartTime;
	}

	public Date getLastPauseTime() {
		return lastPauseTime;
	}

	public void setLastPauseTime(Date lastPauseTime) {
		this.lastPauseTime = lastPauseTime;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

}
