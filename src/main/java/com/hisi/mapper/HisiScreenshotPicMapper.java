package com.hisi.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiScreenshotPic;

public interface HisiScreenshotPicMapper extends MyMapper<HisiScreenshotPic> {
	List<String> getScreenShotPhoto(@Param(value="orderId")String orderId,
			@Param(value="startTime")String startTime,
			@Param(value="endTime")String endTime);

}