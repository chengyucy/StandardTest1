package com.chengyu.demo.service.impl;

import com.chengyu.demo.dao.StandardMapper;
import com.chengyu.demo.entity.Standard;
import com.chengyu.demo.service.StandardService;
import com.chengyu.demo.util.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    StandardMapper mapper;

    @Override
    public PageSupport getByCondition(String condition, Integer pageNo, Integer pageSize) {
        PageSupport page = new PageSupport();
        if (pageNo < 1) {
            pageNo = 1;
        }

        page.setCurrPageNo(pageNo);
        page.setTotalCount(mapper.getCountByCondition(condition));
        page.setPageSize(pageSize);
        int totalPageCount = page.getTotalPageCount();
        page.setStandards(mapper.getByCondition(condition, (pageNo-1)*pageSize, pageSize));
        return page;
    }

    @Override
    public int addStan(Standard standard) {
        int count = mapper.addStandard(standard);
        return count;
    }

    @Override
    public int stdNumExists(String stdNum) {
        int total = mapper.stdNumExists(stdNum);
        return total;
    }

    @Override
    public int modifyStandard(Standard standard) {
        int i = mapper.modifyStandard(standard);
        return i;
    }

    @Override
    public Standard queryById(String id) {
        Standard standard = mapper.queryById(id);
        return standard;
    }

    @Override
    public int delStandard(Integer[] ids) {
        int i = mapper.delStandard(ids);
        return i;
    }
}
