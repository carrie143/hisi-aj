package com.hisi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.mapper.HisiGroupMapper;
import com.hisi.model.HisiDepartment;
import com.hisi.model.HisiGroup;
import com.hisi.service.GroupService;
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private HisiGroupMapper hisiGroupMapper;
	@Override
	public int addGroup(String departmentName, String groupName) {
		int n=0;
		List<HisiGroup> groups=hisiGroupMapper.selectByDepartmentName(departmentName);
		for(int i=0;i<groups.size();i++){
			if(groups.get(i).getGroupName().equals(groupName)){
				return n;
			}
		}
		if(departmentName.equals(groupName)){
			return n;
		}
		HisiGroup hisiGroup=new HisiGroup();
		hisiGroup.setDepartmentName(departmentName);
		hisiGroup.setGroupName(groupName);
	    n = hisiGroupMapper.insert(hisiGroup);
		return n;
	}
	@Override
	public int deleteGroup(String groupName) {
		int i=hisiGroupMapper.deleteByGroupName(groupName);
		return i;
	}
	
}
