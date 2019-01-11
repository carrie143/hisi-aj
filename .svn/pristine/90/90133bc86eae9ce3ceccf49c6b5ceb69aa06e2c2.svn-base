package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiPermission;

public interface HisiPermissionMapper extends MyMapper<HisiPermission> {

	List<HisiPermission> getPermissionsByRoleName(String roleName);

	List<HisiPermission> getAllPermissionsByPageName(String pageName);

	List<HisiPermission> getPageList();

	List<HisiPermission> getPermissionsByPageName(@Param(value="roleId")int roleId,@Param(value="pageName")String pageName);

	int getPermissionId(String permissionName);
}