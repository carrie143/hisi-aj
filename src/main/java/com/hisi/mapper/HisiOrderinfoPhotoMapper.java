package com.hisi.mapper;

import java.util.List;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiOrderinfoPhoto;

public interface HisiOrderinfoPhotoMapper extends MyMapper<HisiOrderinfoPhoto> {

	int deletePath(String orderId);

	List<HisiOrderinfoPhoto> getPhoto(String orderId);

	List<HisiOrderinfoPhoto> getOrderinfoPhotoByChannelId(String channelId);

	List<Integer> getOldIds(String orderId);

	int deleteUnUsePath(Integer id);
}