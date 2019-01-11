package com.hisi.service;

import java.util.List;

import com.hisi.model.vo.ComprehensiveOrderVo;

public interface ComprehensiveQueryService {


	List<ComprehensiveOrderVo> getComprehensiveOrderByCondition(
			String isUnpack,String createTime,String proxyName, String channelId, String condition
			,int pageNum,int pageSize);

	List<ComprehensiveOrderVo> getOrder(int pageNum,int pageSize);

	String getUnpackAccount(String orderId);


}
