package com.tsn.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsn.bean.Position;
import com.tsn.bean.PositionExample;
import com.tsn.service.PositionService;
import com.tsn.util.Constants;
import com.tsn.util.TimeString;

/**
 * 多条件分页模糊查询Demo
 * @author zhaoyuanyuan
 *
 */
@Controller
@RequestMapping("/select")
public class PositionController extends BaseController{
	
	@Resource
	private PositionService positionService;
	
	@Override
	protected String getFunctionName() {
		return "selectPages/";
	}
	
	@RequestMapping("/listAjax")
	public String listAjax(Model model,HttpServletRequest req,HttpServletResponse rep,PositionExample param,Integer pageNo,Integer pageSize){	  
		//获取模糊查询字段
		String proId = req.getParameter("proId");
		if(StringUtils.isNotBlank(proId)){
			int pId = Integer.parseInt(proId);
			param.setProvinceId(pId);
		}
		String ctiId = req.getParameter("ctiId");
		if(StringUtils.isNotBlank(ctiId)){
			int ciId = Integer.parseInt(ctiId);
			param.setCityId(ciId);
		}
		String couId = req.getParameter("couId");
		if(StringUtils.isNotBlank(couId)){
			int coId = Integer.parseInt(couId);
			param.setCountyId(coId);
		}
		String fromUserName = req.getParameter("fuName");
		param.setFromUserName("%"+fromUserName+"%");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		//转换时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(StringUtils.isNotBlank(start)){
				start +=" 00:00:00";
				param.setStartTime(sdf.parse(start));
			}
			if(StringUtils.isNotBlank(end)){
				end +=" 23:59:00";
				param.setEndTime(sdf.parse(end));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//放入当前页码和每页数量
		Map<String, Integer> callback = new HashMap<String, Integer>();
		callback.put("pageSize", pageSize);
		callback.put("pageNo", pageNo);
		List<Position> list = positionService.selectByExample(callback,param);
		model.addAttribute(Constants.GLOBAL_PAGE_NO, callback.get(Constants.GLOBAL_PAGE_NO));
	    model.addAttribute(Constants.GLOBAL_TOTAL_PAGES, callback.get(Constants.GLOBAL_TOTAL_PAGES));
	    model.addAttribute(Constants.GLOBAL_TOTAL_COUNT, callback.get(Constants.GLOBAL_TOTAL_COUNT));
	    model.addAttribute(Constants.GLOBAL_PAGE_SIZE, callback.get(Constants.GLOBAL_PAGE_SIZE));
	    model.addAttribute("list", list);
		return getFunctionName()+"listAjax";
	}
}
