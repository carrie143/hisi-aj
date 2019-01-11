package com.hisi.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "hisi_unpack")
public class HisiUnpack {
	/**
	 * 自增id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 运单号
	 */
	@Column(name = "tracking_number")
	private String trackingNumber;

	/**
	 * 开包图片地址
	 */
	@Column(name = "unpacked_pic_access")
	private String unpackedPicAccess;

	/**
	 * 通道号
	 */
	@Column(name = "channel_id")
	private String channelId;

	/**
	 * 开包台号
	 */
	@Column(name = "unpack_channel")
	private String unpackChannel;

	/**
	 * 开包账户
	 */
	@Column(name = "unpack_account")
	private String unpackAccount;

	/**
	 * 时间
	 */
	@Column(name = "time")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;

	/**
	 * 获取自增id
	 *
	 * @return id - 自增id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置自增id
	 *
	 * @param id
	 *            自增id
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
	 * 获取开包图片地址
	 *
	 * @return unpacked_pic_access - 开包图片地址
	 */
	public String getUnpackedPicAccess() {
		return unpackedPicAccess;
	}

	/**
	 * 设置开包图片地址
	 *
	 * @param unpackedPicAccess
	 *            开包图片地址
	 */
	public void setUnpackedPicAccess(String unpackedPicAccess) {
		this.unpackedPicAccess = unpackedPicAccess;
	}

	/**
	 * 获取通道号
	 *
	 * @return channel_id - 获取通道号
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
	 * 获取开包台号
	 *
	 * @return unpack_channel - 获取开包台号
	 */
	public String getUnpackChannel() {
		return unpackChannel;
	}

	/**
	 * 设置开包台号
	 *
	 * @param unpackChannel
	 *            开包台号
	 */
	public void setUnpackChannel(String unpackChannel) {
		this.unpackChannel = unpackChannel;
	}

	/**
	 * 获取开包账户
	 *
	 * @return unpack_account - 开包账户
	 */
	public String getUnpackAccount() {
		return unpackAccount;
	}

	/**
	 * 设置开包账户
	 *
	 * @param unpackAccount
	 *            开包账户
	 */
	public void setUnpackAccount(String unpackAccount) {
		this.unpackAccount = unpackAccount;
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