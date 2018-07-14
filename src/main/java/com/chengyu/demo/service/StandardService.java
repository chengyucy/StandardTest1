package com.chengyu.demo.service;


import com.chengyu.demo.entity.Standard;
import com.chengyu.demo.util.PageSupport;

public interface StandardService {

//	public PageSupport getAll(Integer pageNo,Integer pageSize);

    public PageSupport getByCondition(String condition, Integer pageNo, Integer pageSize);

    public int addStan(Standard standard);

    public int stdNumExists(String stdNum);

    public int modifyStandard(Standard standard);

    public Standard queryById(String id);

    public int delStandard(Integer[] ids);
}
