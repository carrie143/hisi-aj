package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiRole;
import com.hisi.service.RoleService;

@RestController
@RequestMapping("/role")
@Api(description = "角色管理")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@ApiOperation(value = "新增角色", httpMethod="GET",response=YfslResult.class,notes = "新增角色")
	@RequestMapping(value="/addRole",method=RequestMethod.GET)
	public YfslResult addRole(@ApiParam(name="roleType",value="角色类型",required=true)String roleType,
			@ApiParam(name="roleName",value="角色名称",required=true)String roleName,
			@ApiParam(name="roleDesc",value="角色描述",required=true)String roleDesc){
		int i=roleService.addRole(roleType,roleName,roleDesc);
		if(i>0){
			return YfslResult.ok("新增成功");
		}
		return YfslResult.fail("新增失败");
	}
	@ApiOperation(value = "编辑角色", httpMethod="GET",response=YfslResult.class,notes = "编辑角色")
	@RequestMapping(value="/editRole",method=RequestMethod.GET)
	public YfslResult editRole(@ApiParam(name="id",value="主键id",required=true)Integer id,
			@ApiParam(name="roleName",value="角色名称",required=true)String roleName,
			@ApiParam(name="roleDesc",value="角色描述",required=true)String roleDesc,
			@ApiParam(name="roleType",value="角色类型",required=true)String roleType){
		int i=roleService.editRole(id,roleName,roleDesc,roleType);
		if(i>0){
			return YfslResult.ok("编辑成功");
		}
		return YfslResult.fail("编辑失败");
	}
	@ApiOperation(value = "得到角色列表", httpMethod="GET",response=YfslResult.class,notes = "得到角色列表")
	@RequestMapping(value="/getRole",method=RequestMethod.GET)
	public YfslResult getRole(@RequestParam(required = true) @ApiParam("页数，首页为1") int pageNum,
			@RequestParam(required = true) @ApiParam("每页数据条数") int pageSize){
		List<HisiRole> hisiRoles=roleService.getRole(pageNum,pageSize);
		return YfslResult.ok(new PageInfo<HisiRole>(hisiRoles));
	}

	@ApiOperation(value = "删除角色", httpMethod="GET",response=YfslResult.class,notes = "删除角色")
	@RequestMapping(value="/deleteRole",method=RequestMethod.GET)
	public YfslResult deleteRole(@ApiParam(name="id",value="主键id",required=true)int id){
		/*int id=Integer.parseInt(id1); String id1*/
		int i=roleService.deleteRole(id);
		int j=roleService.deleteRolePermission(id);//删除对应的权限关系
		if(i>0||j>0){
			return YfslResult.ok("删除成功");
		}
		return YfslResult.fail("删除失败");
	}
	@ApiOperation(value = "得到角色列表-人员管理", httpMethod="GET")
	@GetMapping(value="/getRoleTypeList_user")
	public List<String> getRoleTypeList_user(){
		List<String> list=roleService.getRoleTypeList_user();
		return list;
	}
	@ApiOperation(value = "得到角色列表-设备管理", httpMethod="GET")
	@GetMapping(value="/getRoleTypeList_device")
	public List<String> getRoleTypeList_device(){
		List<String> list=roleService.getRoleTypeList_device();
		return list;
	}
	@ApiOperation(value = "得到角色列表-货代管理", httpMethod="GET")
	@GetMapping(value="/getRoleTypeList_proxy")
	public List<String> getRoleTypeList_proxy(){
		List<String> list=roleService.getRoleTypeList_proxy();
		return list;
	}
	@ApiOperation(value = "根据类型得到角色列表", httpMethod="GET")
	@GetMapping(value="/getRoleListByType")
	public List<String> getRoleListByType(@ApiParam(name="roleType",value="通道号",required=true)
	@RequestParam String roleType){
		List<String> list=roleService.getRoleListByType(roleType);
		return list;
	}
}
