package com.hisi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hisi.common.util.PasswordUtil;
import com.hisi.mapper.HisiProxyMapper;
import com.hisi.model.HisiProxy;
import com.hisi.model.HisiUser;
import com.hisi.service.ProxyService;
@Service
public class ProxyServiceImpl implements ProxyService {
	@Autowired
	private HisiProxyMapper hisiProxyMapper;
	@Autowired
	private PasswordUtil passwordUtil;
	@Override
	public int addProxy(HisiProxy proxy) {
		return hisiProxyMapper.insert(proxy);
		
	}
	@Override
	public int editProxy(HisiProxy proxy) {
		return hisiProxyMapper.updateByPrimaryKey(proxy);
	}
	@Override
	public int deleteProxy(int id) {
		return hisiProxyMapper.deleteByPrimaryKey(id);
	}
	@Override
	public List<HisiProxy> findProxyByCondition(int pageNum, int pageSize,String condition) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiProxyMapper.findProxyByCondition(condition);
	}
	@Override
	public HisiProxy selectProxyByProxyId(String proxyId) {
		return hisiProxyMapper.selectProxyByProxyId(proxyId);
	}
	@Override
	public List<HisiProxy> getProxyList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiProxyMapper.selectAll();
	}
	@Override
	public boolean proxyAcountLogin(String proxyId, String password) {
		HisiProxy hisiproxy=new HisiProxy();
		hisiproxy=hisiProxyMapper.selectProxyByProxyId(proxyId);
		if(hisiproxy==null){
			return false;
		}
		Object password1=passwordUtil.generateScret(proxyId,password);
		password=password1.toString();
		if(hisiproxy.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	@Override
	public int updatePassword(String proxyId, String newPassword) {
		return hisiProxyMapper.updatePassword(proxyId,newPassword);
	}
	
}
