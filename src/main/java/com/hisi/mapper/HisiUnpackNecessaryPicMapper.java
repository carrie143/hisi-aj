package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiUnpackNecessaryPic;
import com.hisi.model.vo.HisiTrackInfo;
import com.hisi.model.vo.HisiUnpackNecessaryPicReturnInfo;

public interface HisiUnpackNecessaryPicMapper extends
		MyMapper<HisiUnpackNecessaryPic> {

	List<HisiUnpackNecessaryPicReturnInfo> selectByChannelIdAndUnpack(
			@Param("channelId") String channelId);

	void updateUnpackById(@Param("unpackVerifyPicId") Integer unpackVerifyPicId);

	HisiTrackInfo selectTrackingNow(@Param("channelId") String channelId);

	List<HisiUnpackNecessaryPic> findNecessaryPic(@Param("orderId")String orderId,
			@Param("channelId")String channelId);

}