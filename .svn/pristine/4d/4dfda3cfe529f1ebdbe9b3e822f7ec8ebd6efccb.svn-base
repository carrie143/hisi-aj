package com.hisi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.mapper.HisiUservoMapper;
import com.hisi.model.HisiUservo;
import com.hisi.service.UserVoService;
@Service
public class UserVoServiceImpl implements UserVoService {
	@Autowired
	private HisiUservoMapper hisiUservoMapper;
	@Override
	public List<HisiUservo> selectAll() {
		List<HisiUservo> list=hisiUservoMapper.selectAll();
		return list;
	}

}
