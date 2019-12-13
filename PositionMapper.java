package com.tsn.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.tsn.bean.Position;
import com.tsn.bean.PositionExample;


public interface PositionMapper {
	
    /**
     * 获取查询数据的条数
     * @param param
     * @return
     */
    int countByExample(PositionExample param);
    /**
	 * 多条件模糊分页查询
	 * @param param
	 * @return
	 */
	List<Position> selectByExample(PositionExample param,RowBounds row);

}