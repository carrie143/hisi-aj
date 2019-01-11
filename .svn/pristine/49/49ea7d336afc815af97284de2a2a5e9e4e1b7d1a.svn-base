package com.hisi.service;


import com.hisi.common.util.UserVo;
import com.hisi.model.HisiLoginRecord;
import com.hisi.model.HisiUser;



public interface LoginService{

	public UserVo compareImage(String imageUrl)throws Exception;


	public boolean acountLogin(String userId,String password);

	public HisiUser selectStatus(String userId);

	public int addLoginRecord(HisiLoginRecord loginRecord);

	public String findLoginUserNameByChannelId(String channelId);

}
