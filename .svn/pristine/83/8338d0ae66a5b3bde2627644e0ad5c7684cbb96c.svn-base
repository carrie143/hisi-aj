package com.hisi.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiDevice;

public interface HisiDeviceMapper extends MyMapper<HisiDevice> {
	List<HisiDevice> selectByChannelId(String channelId);

	List<HisiDevice> findDeviceByStatus(String status);

	List<HisiDevice> findDeviceByDate(Date date1);

	List<HisiDevice> findDeviceByRole(String role);

	List<HisiDevice> findDeviceByConditions(@Param(value="conditions") String conditions);

	String getChannelIdByIp(String ip);

	List<HisiDevice> findDevice(@Param(value="date")String date, 
			@Param(value="role")String role,@Param(value="channelId") String channelId,
			@Param(value="conditions")String conditions,@Param(value="status")String status);
	int selectRoleIdByIp(String ip);

	String selectRoleNameByIp(String ip);

	List<HisiDevice> getCreamaDevice(String channelId);

	List<HisiDevice> findCreamaByChannelId(String channelId);

	String selectRoleTypeByIp(String ip);
}