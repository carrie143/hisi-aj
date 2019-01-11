package com.hisi.service;

import java.util.List;

import com.hisi.model.HisiPermission;
import com.hisi.model.HisiRolePermission;

public interface PermissionService {

	List<HisiPermission> getAllPermissionsByPageName(String pageName);

	int addPermission(HisiRolePermission hisiRolePermission);

	int deletePermission(HisiRolePermission hisiRolePermission);

	List<HisiPermission> getPermissionsByRoleId(int roleId);

	List<HisiPermission> getPermissionsByRoleName(String roleName);

	List<HisiPermission> getPermissionsByPageName(int roleId,String pageName);

	int getPermissionId(String permissionName);


}
