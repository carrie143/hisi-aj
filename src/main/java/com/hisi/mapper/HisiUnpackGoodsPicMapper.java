package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiUnpackGoodsPic;

public interface HisiUnpackGoodsPicMapper extends MyMapper<HisiUnpackGoodsPic> {
	List<String> selectByUnpackId(@Param("unpackId") Integer unpackId);

	List<String> getGoodsPhoto(@Param("orderId")String orderId,@Param("startTime")String startTime,
			@Param("endTime")String endTime);

	void updateUnpackId(@Param("id") Integer id, @Param("url") String url);
	
}