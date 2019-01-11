/*package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hisi.common.util.YfslResult;
import com.hisi.service.PermissionService;

@RestController
@RequestMapping("/permission")
@Api(description = "权限配置")
public class PermissionPageController {
	@Autowired
	private PermissionService permissionService;
	@ApiOperation(value = "查询页面对应的操作权限",httpMethod="POST",response=YfslResult.class)
	@RequestMapping(value="/getPermission",method=RequestMethod.POST, headers = "Content-Type=application/json")
	public List<String> getPermission(@RequestParam(required = true) @ApiParam("页面名称") String pageName) {
		//List<String> list=permissionService.getPermission(pageName);
		return null;
	}
	
}
*/