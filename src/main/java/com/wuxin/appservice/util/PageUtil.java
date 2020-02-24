package com.wuxin.appservice.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil implements Serializable{
/*	//当前第几页
	private Integer indexPage;
	//一页显示多少数据
	private Integer pageSize;
	//总数据量
	private Integer totalSize;
	//总页数
	private Integer pageCount;
	//请求参数集合
	private Map<String,Object> parameterMap = new HashMap<String,Object>();
	*/
	//获取分页结果
	private List<?> list = new ArrayList();
	
	private Integer start;
	
	private Integer limit;
	
	private Integer total;
	
	private Map<String,Object> resultMap;
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Integer getStart() {
		return start;
	}
	
	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public PageUtil() {
		if(start == null){
			start = new Integer(0);
		}
		
		if(limit == null){
			limit = new Integer(10);
		}
	}

	
	
}
