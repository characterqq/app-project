package com.wuxin.appservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxin.appservice.dao.DeptMapper;
import com.wuxin.appservice.model.Dept;
import com.wuxin.appservice.service.DeptService;
import com.wuxin.appservice.vo.enums.DeptVo;

/**
 * 
 * @author hk
 *
 */
@Service("DeptService")
public class DeptServiceImpl implements DeptService{
	 @Autowired
	 private DeptMapper deptMapper;

	@Override
	public List<Dept> selectDept() {
		// TODO Auto-generated method stub
		return deptMapper.selectDept();
	}

	@Override
	public Dept get(Integer did) {
		// TODO Auto-generated method stub
		return deptMapper.get(did);
	}

	@Override
	public List<DeptVo> list(DeptVo pv) {
		// TODO Auto-generated method stub
		return deptMapper.list(pv);
	}

	@Override
	public int count(DeptVo pv) {
		// TODO Auto-generated method stub
		return deptMapper.count(pv);
	}

	@Override
	public int insertSelective(Dept record) {
		// TODO Auto-generated method stub
		return deptMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Dept record) {
		// TODO Auto-generated method stub
		return deptMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer did) {
		// TODO Auto-generated method stub
		return deptMapper.deleteByPrimaryKey(did);
	}
/**
 * 根据uid查询该管理员所属部门
 */
	@Override
	public Dept uidDept(Integer uid) {
		// TODO Auto-generated method stub
		return deptMapper.uidDept(uid);
	}
}
