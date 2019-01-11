package com.hisi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hisi.mapper.HisiRoleMapper;
import com.hisi.mapper.HisiRolePermissionMapper;
import com.hisi.model.HisiRole;
import com.hisi.model.HisiRolePermission;
import com.hisi.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private HisiRoleMapper hisiRoleMapper;
	@Autowired
	private HisiRolePermissionMapper hisiRolePermissionMapper;
	@Override
	public int addRole(String roleType,String roleName, String roleDesc) {
		HisiRole hisiRole=new HisiRole();
		hisiRole.setRoleName(roleName);
		hisiRole.setRoleDesc(roleDesc);
		hisiRole.setRoleType(roleType);
		int i = hisiRoleMapper.insert(hisiRole);
		return i;
	}
	@Override
	public int editRole(Integer id, String roleName, String roleDesc,String roleType) {
		int i=hisiRoleMapper.editRole(id,roleName,roleDesc,roleType);
		return i;
	}
	@Override
	public List<HisiRole> getRole(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<HisiRole> list = hisiRoleMapper.selectAll();
		return list;
	}
	@Override
	public int deleteRole(Integer id) {
		int i=hisiRoleMapper.deleteById(id);
		return i;
	}
	@Override
	public List<String> findAllRole() {
		List<HisiRole> roles = hisiRoleMapper.selectAll();
		List<String> list = new ArrayList<String>();
		for(int i=0;i<roles.size();i++){
			list.add(roles.get(i).getRoleName());
		}
		return list;
	}
	@Override
	public List<HisiRole> getRoles() {
		return hisiRoleMapper.getRoles();
	}
	@Override
	public int deleteRolePermission(Integer id) {
		return hisiRolePermissionMapper.deleteRolePermission(id);
	}
	@Override
	public List<String> getRoleTypeList_user() {
		return hisiRoleMapper.getRoleTypeList_user();
	}
	@Override
	public List<String> getRoleTypeList_device() {
		return hisiRoleMapper.getRoleTypeList_device();
	}
	@Override
	public List<String> getRoleTypeList_proxy() {
		return hisiRoleMapper.getRoleTypeList_proxy();
	}
	@Override
	public List<String> getRoleListByType(String roleType) {
		return hisiRoleMapper.getRoleListByType(roleType);
	}
	@Override
	public int getRoleId(String userRoleName) {
		return hisiRoleMapper.getRoleId(userRoleName);
	}
	@Override
	public List<HisiRole> getAllRoles() {
		return hisiRoleMapper.getAllRoles();
	}
	@Override
	public String findRoleTypeByName(String userRoleName1) {
		return hisiRoleMapper.findRoleTypeByName(userRoleName1);
	}
}
