package com.hisi.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hisi.model.HisiScreenshotPic;
import com.hisi.model.vo.HisiTrackInfo;
import com.hisi.model.vo.HisiUnpackNecessaryInfo;
import com.hisi.model.vo.HisiUnpackNecessaryPicReturnInfo;

public interface UnpackVerifyService {

	PageInfo<HisiUnpackNecessaryPicReturnInfo> getUnpackNecessary(int pageNum,
			int pageSize, String channelId);

	Integer deleteUnpackNecessary(int id);

	Integer insertUnpackNecessary(
			HisiUnpackNecessaryInfo hisiUnpackNecessaryInfo);

	HisiTrackInfo getTrackingNow(String channelId);
	int addScreenShotPhoto(HisiScreenshotPic hisiScreenshotPic);

	List<String> getScreenShotPhoto(String orderId,int pageNum,int pageSize,String startTime,String endTime);

	List<String> getGoodsPhoto(String orderId,String startTime,String endTime);

}
