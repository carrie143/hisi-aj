package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_page")
public class HisiPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "page_name")
    private String pageName;

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
     * @return page_name
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}