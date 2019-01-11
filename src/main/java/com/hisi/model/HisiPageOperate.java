package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_page_operate")
public class HisiPageOperate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "page_id")
    private Integer pageId;

    private String operation;

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
     * @return page_id
     */
    public Integer getPageId() {
        return pageId;
    }

    /**
     * @param pageId
     */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /**
     * @return operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
}