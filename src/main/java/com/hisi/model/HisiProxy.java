package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_proxy")
public class HisiProxy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "proxy_id")
    private String proxyId;

    private String password;

    @Column(name = "proxy_name")
    private String proxyName;

    private String manager;

    private String telphone;

    @Column(name = "role_name")
    private String roleName;

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
     * @return proxy_id
     */
    public String getProxyId() {
        return proxyId;
    }

    /**
     * @param proxyId
     */
    public void setProxyId(String proxyId) {
        this.proxyId = proxyId;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * @param manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * @return telphone
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * @param telphone
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}