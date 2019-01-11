package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiCarryPerson;

public interface HisiCarryPersonMapper extends MyMapper<HisiCarryPerson> {

	List<HisiCarryPerson> findCarryPersonBycondition(@Param(value="condition")String condition);
}