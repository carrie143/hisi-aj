package com.hisi.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.model.HisiChannel;

public interface ChannelService {

	int insertChannel(HisiChannel hisiChannel);

	List<HisiChannel> getAllChannel(Integer pageNum,Integer pageSize);

	int deleteChannel(String channelId);

	int updateChannel(int id, String channelId, String channelAddress);

	List<HisiChannel> getChannelByStatus(Integer pageNum,Integer pageSize,String status);

	List<String> getALlChannelId();

	List<HisiChannel> findChannel(String channelId, String channelAddress);





}
