package com.hisi.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hisi_orderinfo_other")
public class HisiOrderinfoOther {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "check_person")
    private String checkPerson;

    @Column(name = "open_person")
    private String openPerson;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "pause_time")
    private Date pauseTime;

    @Column(name = "channelId")
    private String channelid;

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
     * @return check_person
     */
    public String getCheckPerson() {
        return checkPerson;
    }

    /**
     * @param checkPerson
     */
    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    /**
     * @return open_person
     */
    public String getOpenPerson() {
        return openPerson;
    }

    /**
     * @param openPerson
     */
    public void setOpenPerson(String openPerson) {
        this.openPerson = openPerson;
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
     * @return pause_time
     */
    public Date getPauseTime() {
        return pauseTime;
    }

    /**
     * @param pauseTime
     */
    public void setPauseTime(Date pauseTime) {
        this.pauseTime = pauseTime;
    }

    /**
     * @return channelId
     */
    public String getChannelid() {
        return channelid;
    }

    /**
     * @param channelid
     */
    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }
}