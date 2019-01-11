package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiDevice;
import com.hisi.service.DeviceService;
import com.hisi.service.RoleService;
//import java.sql.Date;

@RestController
@RequestMapping("/device")
@Api(value = "HisiDevice", description = "设备管理")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private RoleService roleService;
	@ApiOperation(value = "添加设备",httpMethod="POST",response=YfslResult.class,notes = "添加设备")
	@RequestMapping(value="/addDevice",method=RequestMethod.POST,headers ="Content-Type=application/json")
	public YfslResult addDevice(@ApiParam(name="Map",value="接收设备信息",required=true)@RequestBody Map<String, String> map ) throws Exception {
		String deviceId = map.get("deviceId");
		String deviceIp = map.get("deviceIp");
		String deviceProducer = map.get("deviceProducer");
		String deviceType = map.get("deviceType");
		String deviceRole = map.get("deviceRole");
		String deviceProduceDate = map.get("deviceProduceDate");//"2018-01-23"
		//转换为date格式：Mon Jun 23 00:00:00 CST 2014
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(deviceProduceDate);
		String channelId = map.get("channelId");
		String deviceStatus = "";
		//判断状态
		 InetAddress address = InetAddress.getByName(deviceIp);
         //InetAddress address = InetAddress.getByName("39.108.186.102");
         if(address.isReachable(50)){
        	 deviceStatus="正常";
         }
         else {
        	 deviceStatus="异常";
		}
		HisiDevice hisiDevice=new HisiDevice();
		hisiDevice.setChannelId(channelId);
		hisiDevice.setDeviceRole(deviceRole);
		hisiDevice.setDeviceId(deviceId);
		hisiDevice.setDeviceProduceDate(date);
		hisiDevice.setDeviceProducer(deviceProducer);
		hisiDevice.setDeviceStatus(deviceStatus);
		hisiDevice.setDeviceType(deviceType);
		hisiDevice.setDeviceIp(deviceIp);
		int i=deviceService.insertDevice(hisiDevice);
		if(i>0){
			return YfslResult.ok("新增设备成功");
		}
		return YfslResult.fail("新增设备失败");
		
	}
	@ApiOperation(value = "编辑设备",httpMethod="POST",response=YfslResult.class,notes = "编辑设备")
	@RequestMapping(value="/editDevice",method=RequestMethod.POST,headers ="Content-Type=application/json")
	public YfslResult editDevice(@ApiParam(name="Map",value="接收修改后的设备信息",required=true)@RequestBody Map<String, String> map ) throws Exception{
		String id = map.get("id");
		int id1 = Integer.parseInt(id);
		String deviceId = map.get("deviceId");
		String deviceIp = map.get("deviceIp");
		String deviceProducer = map.get("deviceProducer");
		String deviceType = map.get("deviceType");
		String deviceRole = map.get("deviceRole");
		String channelId = map.get("channelId");
		String deviceProduceDate = map.get("deviceProduceDate");//"2018-01-23"
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(deviceProduceDate);
		String deviceStatus = "";
		//判断状态
		 InetAddress address = InetAddress.getByName(deviceIp);
         //InetAddress address = InetAddress.getByName("39.108.186.102");
         if(address.isReachable(500)){
        	 deviceStatus="正常";
         }
         else {
        	 deviceStatus="异常";
		}
		HisiDevice hisiDevice=new HisiDevice();
		hisiDevice.setId(id1);
		hisiDevice.setChannelId(channelId);
		hisiDevice.setDeviceRole(deviceRole);
		hisiDevice.setDeviceId(deviceId);
		hisiDevice.setDeviceProduceDate(date);
		hisiDevice.setDeviceProducer(deviceProducer);
		hisiDevice.setDeviceStatus(deviceStatus);
		hisiDevice.setDeviceType(deviceType);
		hisiDevice.setDeviceIp(deviceIp);
		int i=deviceService.editDevice(hisiDevice);
		if(i>0){
			return YfslResult.ok("编辑设备成功");
		}
		return YfslResult.fail("编辑设备失败");
		}
	@ApiOperation(value = "删除设备",httpMethod="GET",response=YfslResult.class,notes = "删除设备")
	@RequestMapping(value="/deleteDevice",method=RequestMethod.GET)
	public YfslResult deleteDevice(@ApiParam(name="id",value="设备信息主键id",required=true)
	@RequestParam Integer id ) {
		int i=deviceService.deleteDevice(id);
		if(i>0){
			return YfslResult.ok("删除成功");
		}
		return YfslResult.fail("删除失败");
		}
	@ApiOperation(value = "获取设备列表",httpMethod="GET",response=HisiDevice.class,notes = "获取设备列表")
	@RequestMapping(value="/getAllDevice",method=RequestMethod.GET)
	public YfslResult getAllDevice(@RequestParam(required = true) @ApiParam("页数，首页为1") int pageNum,
			@RequestParam(required = true) @ApiParam("每页数据条数") int pageSize) throws Exception {
		try {
			List<HisiDevice> devices=deviceService.getAllDevice(pageNum,pageSize);
			return YfslResult.ok(new PageInfo<HisiDevice>(devices));
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}
	@ApiOperation(value = "根据条件筛选设备",httpMethod="GET",response=YfslResult.class,notes = "根据条件筛选设备")
	@RequestMapping(value="/findDevice",method=RequestMethod.GET)
	public YfslResult findDevice(
			@ApiParam("页数，首页为1") @RequestParam(required = true)  int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true)  int pageSize,
			@ApiParam("生产日期")@RequestParam(required = false) String date,
			@ApiParam("角色")@RequestParam(required = false) String role,
			@ApiParam("通道号")@RequestParam(required = false) String channelId,
			@ApiParam("多条件")@RequestParam(required = false) String conditions,
			@ApiParam("状态")@RequestParam(required = false) String status) {
		try {
		PageInfo<HisiDevice> re=deviceService.findDevice(pageNum,pageSize,date,role,channelId,conditions,status);
			return YfslResult.ok(re);
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}
	@ApiOperation(value = "角色接口",httpMethod="GET",response=YfslResult.class)
	@RequestMapping(value="/findRole",method=RequestMethod.GET)
	public List<String> findAllRole() {
		List<String> roles=roleService.findAllRole();
		return roles;
	}
}
