package com.hisi.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.hisi.model.HisiUnpack;

/**
 * 开包记录查询返回vo
 */
@ApiModel(description = "开包记录查询返回vo")
public class HisiUnpackReturnInfo extends HisiUnpack {

	@ApiModelProperty(value = "开包实物图片地址集合")
	private List<String> unpackedGoodsPicAccess;

	public List<String> getUnpackedGoodsPicAccess() {
		return unpackedGoodsPicAccess;
	}

	public void setUnpackedGoodsPicAccess(List<String> unpackedGoodsPicAccess) {
		this.unpackedGoodsPicAccess = unpackedGoodsPicAccess;
	}

}
