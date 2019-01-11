package com.hisi.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hisi_orderinfo_basic")
public class HisiOrderinfoBasic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "flight_id")
    private String flightId;

    private Integer num;

    @Column(name = "carry_person")
    private String carryPerson;

    @Column(name = "proxy_name")
    private String proxyName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "last_pause_time")
    private Date lastPauseTime;

    /**
     * 0:等待安检 1：安检结束 3：暂停安检
     */
    private Integer status;

    @Column(name = "end_time")
    private Date endTime;

    /**
     * 0:未开包  1:已开包
     */
    @Column(name = "isUnpack")
    private String isunpack;

    /**
     * 安检人
     */
    @Column(name = "check_person")
    private String checkPerson;

    /**
     * 开包人
     */
    @Column(name = "unpack_account")
    private String unpackAccount;

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
     * @return channel_id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @param channelId
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return flight_id
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * @param flightId
     */
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    /**
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return carry_person
     */
    public String getCarryPerson() {
        return carryPerson;
    }

    /**
     * @param carryPerson
     */
    public void setCarryPerson(String carryPerson) {
        this.carryPerson = carryPerson;
    }

    /**
     * @return proxy_name
     */
    public String getProxyName() {
        return proxyName;
    }

    /**
     * @param proxyName
     */
    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return last_pause_time
     */
    public Date getLastPauseTime() {
        return lastPauseTime;
    }

    /**
     * @param lastPauseTime
     */
    public void setLastPauseTime(Date lastPauseTime) {
        this.lastPauseTime = lastPauseTime;
    }

    /**
     * 获取0:等待安检 1：安检结束 3：暂停安检
     *
     * @return status - 0:等待安检 1：安检结束 3：暂停安检
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:等待安检 1：安检结束 3：暂停安检
     *
     * @param status 0:等待安检 1：安检结束 3：暂停安检
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取0:未开包  1:已开包
     *
     * @return isUnpack - 0:未开包  1:已开包
     */
    public String getIsunpack() {
        return isunpack;
    }

    /**
     * 设置0:未开包  1:已开包
     *
     * @param isunpack 0:未开包  1:已开包
     */
    public void setIsunpack(String isunpack) {
        this.isunpack = isunpack;
    }

    /**
     * 获取安检人
     *
     * @return check_person - 安检人
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     * 设置安检人
     *
     * @param checkPerson 安检人
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    /**
     * 获取开包人
     *
     * @return unpack_account - 开包人
     */
    public String getUnpackAccount() {
        return unpackAccount;
    }

    /**
     * 设置开包人
     *
     * @param unpackAccount 开包人
     */
    public void setUnpackAccount(String unpackAccount) {
        this.unpackAccount = unpackAccount;
    }
}