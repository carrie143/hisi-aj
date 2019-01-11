package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiProxy;

public interface HisiProxyMapper extends MyMapper<HisiProxy> {
	List<HisiProxy> findProxyByCondition(@Param(value="condition")String condition);

	HisiProxy selectProxyByProxyId(String proxyId);

	HisiProxy selectByProxyId(String proxyId);

	int updatePassword(@Param(value="proxyId")String proxyId, @Param(value="newPassword")String newPassword);
}