package com.hisi.mapper;

import java.util.List;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiPermission;
import com.hisi.model.HisiRolePermission;

public interface HisiRolePermissionMapper extends MyMapper<HisiRolePermission> {

	List<HisiPermission> getPermissionsByRoleId(int roleId);

	int deleteRolePermission(Integer id);

	List<HisiPermission> getPermissionsByRoleName(String roleName);
}