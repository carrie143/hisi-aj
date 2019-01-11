package com.hisi.mapper;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiLoginRecord;

public interface HisiLoginRecordMapper extends MyMapper<HisiLoginRecord> {
	String findLoginUserNameByChannelId(String channelId);
}