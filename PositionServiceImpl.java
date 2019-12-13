package com.tsn.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.tsn.bean.City;
import com.tsn.bean.County;
import com.tsn.bean.Position;
import com.tsn.bean.PositionExample;
import com.tsn.bean.Province;
import com.tsn.bean.ThirdToken;
import com.tsn.mapper.PositionMapper;
import com.tsn.service.PositionService;
@Service
public class PositionServiceImpl extends BaseService implements PositionService{
	@Resource
	private PositionMapper postitiMapper;


	@Override
	public List<Position> selectByExample(Map<String, Integer> callback,PositionExample param) {
		Integer totalCount = postitiMapper.countByExample(param);
		callback = this.getPaging(callback, callback.get("pageSize") == null? 15:callback.get("pageSize"),
				totalCount, callback.get("pageNo") == null? 1:callback.get("pageNo"));
		List<Position> list = postitiMapper.selectByExample(param, new RowBounds(
				callback.get("startRow"), callback.get("pageSize")));
		return list;
	}

}
