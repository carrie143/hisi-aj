package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiRole;

public interface HisiRoleMapper extends MyMapper<HisiRole> {
	int editRole(@Param(value="id")Integer id,@Param(value="roleName") String roleName, 
			@Param(value="roleDesc")String roleDesc,@Param(value="roleType")String roleType);

	int deleteById(Integer id);

	List<String> getRoleTypeList_user();

	List<String> getRoleTypeList_device();

	List<String> getRoleTypeList_proxy();

	List<String> getRoleListByType(String roleType);

	List<HisiRole> getRoles();

	int getRoleId(String userRoleName);

	List<HisiRole> getAllRoles();

	String findRoleTypeByName(String userRoleName1);
}