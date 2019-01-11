package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hisi.common.util.PasswordUtil;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiProxy;
import com.hisi.model.HisiUser;
import com.hisi.service.ProxyService;
import com.hisi.service.UserService;

@RestController
@RequestMapping("/proxy")
@Api(description = "货代管理")
public class ProxyController {
	@Autowired
	private ProxyService proxyService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordUtil passwordUtil;

	@ApiOperation(value = "添加货代", httpMethod = "POST")
	@PostMapping("/addProxy")
	public YfslResult addProxy(@RequestBody Map<String, String> map) {
		String userId = map.get("userId");
		String userName = map.get("userName");
		String manager = map.get("manager");
		String telphone = map.get("telphone");
		String cardId=map.get("cardId");
		String roleName=map.get("roleName");
		HisiUser hisiUser = userService.findUserByUserId(userId);
		if (hisiUser != null) {
			return YfslResult.fail("该货代id已存在");
		}
		HisiUser hisiUser1 = new HisiUser();
		hisiUser1.setManager(manager);
		hisiUser1.setUserid(userId);
		hisiUser1.setUsername(userName);
		hisiUser1.setTelphone(telphone);
		hisiUser1.setRoleName(roleName);
		hisiUser1.setCardId(cardId);
		hisiUser1.setStaus(0);
		Object generateScret = passwordUtil.generateScret(userId, "123456");
		String password = generateScret.toString();
		hisiUser1.setPassword(password);
		hisiUser1.setPassword1("123456");
		int i = userService.addUser(hisiUser1);
		if (i > 0) {
			return YfslResult.ok();
		} else {
			return YfslResult.fail("添加失败");
		}
	}

	@ApiOperation(value = "编辑货代", httpMethod = "POST")
	@PostMapping("/editProxy")
	public YfslResult editProxy(@RequestBody Map<String, String> map) {
		String userId = map.get("userId");
		String userName = map.get("userName");
		String manager = map.get("manager");
		String telphone = map.get("telphone");
		String cardId=map.get("cardId");
		String roleName=map.get("roleName");
		String id1 = map.get("id");
		int id = Integer.parseInt(id1);
		int i = userService.editProxy(userId,userName,manager,telphone,cardId,id,roleName);
		if (i > 0) {
			return YfslResult.ok();
		} else {
			return YfslResult.fail("编辑失败");
		}
	}

	@ApiOperation(value = "删除货代", httpMethod = "POST")
	@PostMapping("/deleteProxy")
	public YfslResult deleteProxy(
			@ApiParam(name = "id", value = "主键id", required = true) @RequestParam int id) {
		int i = userService.deleteUser(id);
		if (i > 0) {
			return YfslResult.ok();
		} else {
			return YfslResult.fail("删除失败");
		}
	}

	@ApiOperation(value = "模糊查询", httpMethod = "POST")
	@PostMapping("/findProxyByCondition")
	public YfslResult findProxyByCondition(
			@ApiParam(name = "condition", value = "条件", required = true) @RequestParam String condition,
			@ApiParam("页数，首页为1") @RequestParam(required = true) int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true) int pageSize) {
		List<HisiUser> users = userService.findProxyByCondition(pageNum, pageSize,condition);
		return YfslResult.ok(new PageInfo<HisiUser>(users));
	}

	@ApiOperation(value = "货代列表", httpMethod = "GET")
	@GetMapping("/getProxyList")
	public YfslResult getProxyList(
			@ApiParam("页数，首页为1") @RequestParam(required = true) int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true) int pageSize) {
		List<HisiUser> users = userService.getProxyList(pageNum, pageSize);
		return YfslResult.ok(new PageInfo<HisiUser>(users));
	}

}