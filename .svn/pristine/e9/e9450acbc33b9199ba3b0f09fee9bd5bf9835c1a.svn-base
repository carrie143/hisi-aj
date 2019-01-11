package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_channel")
public class HisiChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "channel_address")
    private String channelAddress;

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
     * @return channel_address
     */
    public String getChannelAddress() {
        return channelAddress;
    }

    /**
     * @param channelAddress
     */
    public void setChannelAddress(String channelAddress) {
        this.channelAddress = channelAddress;
    }
}