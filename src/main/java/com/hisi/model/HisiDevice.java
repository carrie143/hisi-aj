package com.hisi.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "hisi_device")
public class HisiDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "device_ip")
    private String deviceIp;

    @Column(name = "device_producer")
    private String deviceProducer;

    @Column(name = "device_produce_date")
    private Date deviceProduceDate;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "device_role")
    private String deviceRole;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "device_status")
    private String deviceStatus;

    @Column(name = "creama_url")
    private String creamaUrl;

    @Column(name = "playBack_url")
    private String playbackUrl;

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
     * @return device_id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * @return device_ip
     */
    public String getDeviceIp() {
        return deviceIp;
    }

    /**
     * @param deviceIp
     */
    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    /**
     * @return device_producer
     */
    public String getDeviceProducer() {
        return deviceProducer;
    }

    /**
     * @param deviceProducer
     */
    public void setDeviceProducer(String deviceProducer) {
        this.deviceProducer = deviceProducer;
    }

    /**
     * @return device_produce_date
     */
    public Date getDeviceProduceDate() {
        return deviceProduceDate;
    }

    /**
     * @param deviceProduceDate
     */
    public void setDeviceProduceDate(Date deviceProduceDate) {
        this.deviceProduceDate = deviceProduceDate;
    }

    /**
     * @return device_type
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return device_role
     */
    public String getDeviceRole() {
        return deviceRole;
    }

    /**
     * @param deviceRole
     */
    public void setDeviceRole(String deviceRole) {
        this.deviceRole = deviceRole;
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
     * @return device_status
     */
    public String getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * @param deviceStatus
     */
    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    /**
     * @return creama_url
     */
    public String getCreamaUrl() {
        return creamaUrl;
    }

    /**
     * @param creamaUrl
     */
    public void setCreamaUrl(String creamaUrl) {
        this.creamaUrl = creamaUrl;
    }

    /**
     * @return playBack_url
     */
    public String getPlaybackUrl() {
        return playbackUrl;
    }

    /**
     * @param playbackUrl
     */
    public void setPlaybackUrl(String playbackUrl) {
        this.playbackUrl = playbackUrl;
    }
}