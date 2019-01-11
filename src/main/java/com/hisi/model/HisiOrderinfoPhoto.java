package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_orderinfo_photo")
public class HisiOrderinfoPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "photo_path")
    private String photoPath;

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
     * @return photo_path
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * @param photoPath
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}