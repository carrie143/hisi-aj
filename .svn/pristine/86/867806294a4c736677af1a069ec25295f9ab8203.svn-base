package com.hisi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.mapper.HisiParameterMapper;
import com.hisi.model.HisiParameter;
import com.hisi.service.ParameterService;
@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	private HisiParameterMapper hisiParameterMapper;
	@Override
	public int saveParameter(HisiParameter hisiParameter) {
		return hisiParameterMapper.insert(hisiParameter);
	}

}
