package com.hisi.model.vo;

import java.util.List;

import com.hisi.model.HisiDevice;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.HisiOrderinfoPhoto;

public class SceneOrderVo {
	private HisiOrderinfoBasic hisiOrderinfoBasic;
	private List<HisiOrderinfoPhoto> photos;
	private List<HisiDevice> devices;
	public HisiOrderinfoBasic getHisiOrderinfoBasic() {
		return hisiOrderinfoBasic;
	}
	public void setHisiOrderinfoBasic(HisiOrderinfoBasic hisiOrderinfoBasic) {
		this.hisiOrderinfoBasic = hisiOrderinfoBasic;
	}
	public List<HisiOrderinfoPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<HisiOrderinfoPhoto> photos) {
		this.photos = photos;
	}
	public List<HisiDevice> getDevices() {
		return devices;
	}
	public void setDevices(List<HisiDevice> devices) {
		this.devices = devices;
	}
	
}
