package com.hisi.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.HisiOrderinfoOther;

public interface HisiOrderinfoOtherMapper extends MyMapper<HisiOrderinfoOther> {
	int deleteOther(String orderId);

	int addOther(String orderId, Date date);

	List<HisiOrderinfoOther> selectByOrderId(String orderId);

	int updateOtherEndTime(@Param(value="orderId")String orderId, @Param(value="date")Date date,@Param(value="userName")String userName);

	int updateOtherPauseTime(@Param(value="orderId")String orderId, @Param(value="date")Date date);

	HisiOrderinfoOther findCheckingOrder(String channelId);

	List<String> getCheckingChannelId();

	List<String> getUnCheckingChannelId();
}