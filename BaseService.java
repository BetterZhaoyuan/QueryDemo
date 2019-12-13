package com.tsn.service.impl;

import java.util.Map;

import com.tsn.util.Constants;

public class BaseService {
	
	/**
	 * 不使用通用分页  计算分页信息
	 * @param callBack 返回分页信息
	 * @param pageSize 每页显示多少条
	 * @param titleCount 查询记录的条数
	 * @param pageNo 当前页
	 */
	public Map<String, Integer> getPaging(Map<String, Integer> callBack,Integer pageSize,Integer titleCount,Integer pageNo){
		if(titleCount!=null && pageSize!=null  && pageNo!=null){
			int totalPage = 0;
			//计算总共有多少页数据
			if(titleCount%pageSize == 0){
				totalPage = titleCount/pageSize ;
			}else{
				totalPage = titleCount/pageSize + 1;
			}
			//计算查询数据开始记录和结束记录
			int startRow = 0;
			int endRow = 0;
			startRow = (pageNo - 1)* pageSize;
			endRow = pageNo*pageSize;
			callBack.put(Constants.GLOBAL_TOTAL_PAGES, totalPage == 0 ? 1:totalPage );
			callBack.put(Constants.GLOBAL_PAGE_NO, pageNo == 0 ? 1:pageNo);
			callBack.put(Constants.GLOBAL_TOTAL_COUNT, titleCount);
			callBack.put("startRow", startRow);
			callBack.put("pageSize", pageSize);
		}
		return callBack;
	}
}
