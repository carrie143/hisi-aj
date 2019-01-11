package com.hisi.service;


import java.util.List;

import com.hisi.model.HisiProxy;

public interface ProxyService {

	int addProxy(HisiProxy proxy);

	int editProxy(HisiProxy proxy);

	int deleteProxy(int id);

	List<HisiProxy> findProxyByCondition(int pageNum, int pageSize,String condition);

	HisiProxy selectProxyByProxyId(String proxyId);

	List<HisiProxy> getProxyList(int pageNum, int pageSize);

	boolean proxyAcountLogin(String proxyId, String password);

	int updatePassword(String proxyId, String newPassword);

}
