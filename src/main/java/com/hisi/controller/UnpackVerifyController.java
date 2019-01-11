package com.hisi.controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.github.pagehelper.PageInfo;
import com.hisi.common.util.YfslResult;
import com.hisi.common.websocket.WebSocketService;
import com.hisi.model.HisiOrderinfoOther;
import com.hisi.model.HisiScreenshotPic;
import com.hisi.model.vo.HisiTrackInfo;
import com.hisi.model.vo.HisiUnpackNecessaryInfo;
import com.hisi.model.vo.HisiUnpackNecessaryPicReturnInfo;
import com.hisi.service.CommonService;
import com.hisi.service.OrderService;
import com.hisi.service.UnpackVerifyService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@RestController
@RequestMapping("/unpackVerify")
@Api(value = "HisiUnpack", description = "需要开包的运单图片管理")
public class UnpackVerifyController {

	@Autowired
	private UnpackVerifyService unpackVerifyService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private WebSocketService webSocketService;

	@Autowired
	private OrderService orderService;

	// @ApiOperation(value = "增加需要开包的运单图片", httpMethod = "POST", response =
	// String.class, notes = "")
	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// @ApiImplicitParam(name = "hisiUnpackNecessaryInfo", value =
	// "需要开包的运单图片vo", required = true, dataType = "HisiUnpackNecessaryInfo")
	// public YfslResult addUnpackNecessary(
	// @RequestBody HisiUnpackNecessaryInfo hisiUnpackNecessaryInfo) {
	// try {
	// Integer re = unpackVerifyService
	// .insertUnpackNecessary(hisiUnpackNecessaryInfo);
	// return YfslResult.ok(re);
	// } catch (Exception e) {
	// return YfslResult.fail(e.getMessage());
	// }
	// }

	@ApiOperation(value = "增加需要开包的运单图片", httpMethod = "POST", response = String.class, notes = "")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiImplicitParam(name = "hisiUnpackNecessaryInfo", value = "需要开包的运单图片vo", required = true, dataType = "HisiUnpackNecessaryInfo")
	public YfslResult addUnpackNecessaryTest(
			@RequestParam("file") CommonsMultipartFile file,
			@RequestParam(required = false) @ApiParam("图片所属模块") String filePath,
			@RequestParam(required = true) @ApiParam("通道号") String channelId,
			@RequestParam(required = true) @ApiParam("安检人员") String verifyAccount,
			HttpServletRequest request) {
		try {
			HisiUnpackNecessaryInfo hisiUnpackNecessaryInfo = new HisiUnpackNecessaryInfo();
			HisiTrackInfo hisiTrackInfo = unpackVerifyService
					.getTrackingNow(channelId);
			if (hisiTrackInfo == null)
				return YfslResult.fail("当前通道并无正在安检的运单");
			String picAccess = commonService.pictureUpload(file, request,
					filePath);
			if (picAccess == null)
				return YfslResult.fail("图片上传失败");
			hisiUnpackNecessaryInfo.setChannelId(channelId);
			hisiUnpackNecessaryInfo.setPicAccess(picAccess.replaceAll("\\\\",
					"//"));
			hisiUnpackNecessaryInfo.setTrackingNumber(hisiTrackInfo
					.getTrackingNumber());
			hisiUnpackNecessaryInfo.setUnpack(-1);
			hisiUnpackNecessaryInfo.setVerifyAccount(verifyAccount);
			Integer re = unpackVerifyService
					.insertUnpackNecessary(hisiUnpackNecessaryInfo);
			return YfslResult.ok(re);
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}

	// @ApiOperation(value = "删除需要开包的运单图片", httpMethod = "DELETE", response =
	// String.class, notes = "")
	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// public YfslResult deleteUnpackNecessary(@PathVariable int id) {
	// try {
	// Integer re = unpackVerifyService.deleteUnpackNecessary(id);
	// return YfslResult.ok(re);
	// } catch (Exception e) {
	// return YfslResult.fail(e.getMessage());
	// }
	// }

	@ApiOperation(value = "查询需要开包的运单图片", httpMethod = "GET", response = String.class, notes = "")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public YfslResult getUnpackNecessary(
			@RequestParam(required = true) @ApiParam("页数，首页为1") int page,
			@RequestParam(required = true) @ApiParam("每页数据条数") int num,
			@RequestParam(required = true) @ApiParam("开包台号") String channelId) {
		try {
			PageInfo<HisiUnpackNecessaryPicReturnInfo> re = unpackVerifyService
					.getUnpackNecessary(page, num, channelId);
			return YfslResult.ok(re);
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}

	// @ApiOperation(value = "查询该通道正在安检的运单信息", httpMethod = "GET", response =
	// String.class, notes = "")
	// @RequestMapping(value = "/getTrackingNow", method = RequestMethod.GET)
	// public YfslResult getTrackingNow(
	// @RequestParam(required = true) @ApiParam("通道号") String channelId) {
	// try {
	// HisiTrackInfo re = unpackVerifyService.getTrackingNow(channelId);
	// return YfslResult.ok(re);
	// } catch (Exception e) {
	// return YfslResult.fail(e.getMessage());
	// }
	// }

	@ApiOperation(value = "增加需要开包的运单图片信息", httpMethod = "POST", response = String.class, notes = "")
	@RequestMapping(value = "/addP", method = RequestMethod.POST)
	public YfslResult addUnpackNecessary(
			@RequestParam(required = true) @ApiParam("图片名") String name,
			@RequestParam(required = true) @ApiParam("通道号") String channelId,
			@RequestParam(required = true) @ApiParam("安检人员") String verifyAccount) {
		try {
			HisiUnpackNecessaryInfo hisiUnpackNecessaryInfo = new HisiUnpackNecessaryInfo();
			HisiTrackInfo hisiTrackInfo = unpackVerifyService
					.getTrackingNow(channelId);
			if (hisiTrackInfo == null)
				return YfslResult.fail("当前通道并无正在安检的运单");
			String picAccess = name;
			if (picAccess == null)
				return YfslResult.fail("图片上传失败");
			hisiUnpackNecessaryInfo.setChannelId(channelId);
			hisiUnpackNecessaryInfo.setPicAccess(picAccess.replaceAll("\\\\",
					"/"));
			hisiUnpackNecessaryInfo.setTrackingNumber(hisiTrackInfo
					.getTrackingNumber());
			hisiUnpackNecessaryInfo.setUnpack(-1);
			hisiUnpackNecessaryInfo.setVerifyAccount(verifyAccount);
			Integer re = unpackVerifyService
					.insertUnpackNecessary(hisiUnpackNecessaryInfo);
			webSocketService.send2Users(hisiUnpackNecessaryInfo.getChannelId(),
					hisiUnpackNecessaryInfo);
			return YfslResult.ok(re);
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public YfslResult test(String user, String mes) {
		HisiUnpackNecessaryInfo e = new HisiUnpackNecessaryInfo();
		e.setChannelId("001");
		e.setPicAccess("564654555");
		e.setTrackingNumber("xxx");
		e.setUnpack(-1);
		e.setVerifyAccount("aaa");
		webSocketService.send2Users(user, e);
		return YfslResult.ok();
	}

	@ApiOperation(value = "增加截屏卡不间断截取的图片", httpMethod = "POST")
	@PostMapping(value = "/addScreenShotPhoto")
	public YfslResult addScreenShotPhoto(
			@RequestParam(required = true) @ApiParam("通道号") String channelId,
			@RequestParam(required = true) @ApiParam("photoPath") String photoPath,
			@RequestParam(required = true) @ApiParam("timeStamp") String timeStamp) {
		int i = 0;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(timeStamp); 
			HisiOrderinfoOther hisiOrderinfoOther = orderService
					.findCheckingOrder(channelId);
			if(hisiOrderinfoOther==null){
				return YfslResult.fail("该通道未开始运单");
			}else{
				HisiScreenshotPic hisiScreenshotPic = new HisiScreenshotPic();
				hisiScreenshotPic.setOrderId(hisiOrderinfoOther.getOrderId());
				hisiScreenshotPic.setChannelId(channelId);
				hisiScreenshotPic.setPicPath(photoPath);
				hisiScreenshotPic.setTimestamp(date);
				i = unpackVerifyService.addScreenShotPhoto(hisiScreenshotPic);
				return YfslResult.ok(i);
			}
		} catch (Exception e) {
			return YfslResult.fail(e.getMessage());
		}
	}

}
