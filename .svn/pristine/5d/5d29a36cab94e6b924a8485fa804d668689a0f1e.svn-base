package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiUnpack;
import com.hisi.model.vo.HisiTrackInfo;
import com.hisi.model.vo.HisiUnpackReturnInfo;

public interface HisiUnpackMapper extends MyMapper<HisiUnpack> {

	List<HisiUnpackReturnInfo> selectByGlobal(@Param("global") String global);

	HisiTrackInfo selectTracking(@Param("trackingNumber") String trackingNumber);

	List<String> selectTrackingPictures(
			@Param("trackingNumber") String trackingNumber);

	List<HisiUnpack> findOpenDetail(String orderId);

	String getUnpackAccount(String orderId);

	List<String> getUnpackPhoto(@Param("orderId")String orderId,@Param("startTime")String startTime,
			@Param("endTime")String endTime);

	void updateOrderBasisToUnpack(
			@Param("trackingNumber") String trackingNumber,
			@Param("account") String account);

}