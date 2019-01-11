package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiParameter;
import com.hisi.service.ParameterService;

@RestController
@Api(description = "一般参数")
public class ParameterController {
	@Autowired
	private ParameterService parameterService;
	@ApiOperation(value = "设置参数", httpMethod="POST",response=YfslResult.class,notes = "设置参数")
	@RequestMapping(value="/saveParameter",method=RequestMethod.POST,headers ="Content-Type=application/json")
	public YfslResult saveParameter(@RequestBody Map<String, String> map){
		String videoSaveTime = map.get("videoSaveTime");
		String photoSaveTime = map.get("photoSaveTime");
		String screenIntervalTime = map.get("screenIntervalTime");
		HisiParameter hisiParameter=new HisiParameter();
		hisiParameter.setVideoSaveTime(videoSaveTime);
		hisiParameter.setPhotoSaveTime(photoSaveTime);
		hisiParameter.setScreenIntervalTime(screenIntervalTime);
		int i=parameterService.saveParameter(hisiParameter);
		if(i>0){
			return YfslResult.ok("参数保存成功");
		}
		return YfslResult.ok("参数保存失败");
	}
}
