package com.hisi.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.hisi.model.HisiUnpackNecessaryPic;

/**
 * 需要开包的运单图片返回信息
 */
@ApiModel(description = "需要开包的运单图片返回信息")
public class HisiUnpackNecessaryPicReturnInfo extends HisiUnpackNecessaryPic {

	@ApiModelProperty(value = "图片流")
	private String stream;

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

}
