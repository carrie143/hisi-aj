package com.hisi.mapper;

import java.util.List;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiPageOperate;

public interface HisiPageOperateMapper extends MyMapper<HisiPageOperate> {

	List<String> getAllPermissionsByPageName(String pageName);
}