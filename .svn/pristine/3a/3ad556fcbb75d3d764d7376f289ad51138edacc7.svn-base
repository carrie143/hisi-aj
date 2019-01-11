package com.hisi.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "hisi_unpack_necessary_pic")
public class HisiUnpackNecessaryPic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 运单号
	 */
	@Column(name = "tracking_number")
	private String trackingNumber;

	/**
	 * 需要开包图片地址
	 */
	@Column(name = "pic_access")
	private String picAccess;

	/**
	 * 是否已经开包，-1--否，1--是
	 */
	private Integer unpack;

	/**
	 * 通道号
	 */
	@Column(name = "channel_id")
	private String channelId;

	/**
	 * 安检员账户
	 */
	@Column(name = "verify_account")
	private String verifyAccount;

	/**
	 * 时间
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取运单号
	 *
	 * @return tracking_number - 运单号
	 */
	public String getTrackingNumber() {
		return trackingNumber;
	}

	/**
	 * 设置运单号
	 *
	 * @param trackingNumber
	 *            运单号
	 */
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	/**
	 * 获取需要开包图片地址
	 *
	 * @return pic_access - 需要开包图片地址
	 */
	public String getPicAccess() {
		return picAccess;
	}

	/**
	 * 设置需要开包图片地址
	 *
	 * @param picAccess
	 *            需要开包图片地址
	 */
	public void setPicAccess(String picAccess) {
		this.picAccess = picAccess;
	}

	/**
	 * 获取是否已经开包，-1--否，1--是
	 *
	 * @return unpack - 是否已经开包，-1--否，1--是
	 */
	public Integer getUnpack() {
		return unpack;
	}

	/**
	 * 设置是否已经开包，-1--否，1--是
	 *
	 * @param unpack
	 *            是否已经开包，-1--否，1--是
	 */
	public void setUnpack(Integer unpack) {
		this.unpack = unpack;
	}

	/**
	 * 获取通道号
	 *
	 * @return channel_id - 通道号
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * 设置通道号
	 *
	 * @param channelId
	 *            通道号
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * 获取安检员账户
	 *
	 * @return verify_account - 安检员账户
	 */
	public String getVerifyAccount() {
		return verifyAccount;
	}

	/**
	 * 设置安检员账户
	 *
	 * @param verifyAccount
	 *            安检员账户
	 */
	public void setVerifyAccount(String verifyAccount) {
		this.verifyAccount = verifyAccount;
	}

	/**
	 * 获取时间
	 *
	 * @return time - 时间
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 设置时间
	 *
	 * @param time
	 *            时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}
}