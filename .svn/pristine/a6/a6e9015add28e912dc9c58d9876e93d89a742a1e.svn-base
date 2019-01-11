package com.hisi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hisi.mapper.HisiOrderinfoBasicMapper;
import com.hisi.mapper.HisiUnpackMapper;
import com.hisi.model.vo.ComprehensiveOrderVo;
import com.hisi.service.ComprehensiveQueryService;
@Service
public class ComprehensiveQueryServiceImpl implements ComprehensiveQueryService {
	@Autowired
	private HisiOrderinfoBasicMapper hisiOrderinfoBasicMapper;
	@Autowired
	private HisiUnpackMapper hisiUnpackMapper;
	@Override
	public List<ComprehensiveOrderVo> getComprehensiveOrderByCondition(
			String isUnpack,String createTime, String proxyName,String channelId, String condition
			,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiOrderinfoBasicMapper.getComprehensiveOrderByCondition(isUnpack,createTime,proxyName,channelId,condition);
	}
	@Override
	public List<ComprehensiveOrderVo> getOrder(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiOrderinfoBasicMapper.getOrder();
	}
	@Override
	public String getUnpackAccount(String orderId) {
		return hisiUnpackMapper.getUnpackAccount(orderId);
	}
	

}
