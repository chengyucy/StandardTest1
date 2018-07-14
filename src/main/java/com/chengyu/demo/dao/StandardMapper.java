package com.chengyu.demo.dao;

import java.util.List;


import com.chengyu.demo.entity.Standard;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;


public interface StandardMapper {
    @Autowired
    /**
     *
     * @return
     */
    public Integer getCount();

    /**
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Standard> getAll(@Param(value = "pageNo")
                                         Integer pageNo, @Param(value = "pageSize") Integer pageSize);

    /**
     *
     * @param condition
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Standard> getByCondition(@Param(value = "condition")
                                                 String condition, @Param(value = "pageNo")
                                                 Integer pageNo, @Param(value = "pageSize") Integer pageSize);

    /**
     *
     * @param condition
     * @return
     */
    public Integer getCountByCondition(@Param(value = "condition") String condition);

    /**
     *
     * @param standard
     * @return
     */
    public int addStandard(Standard standard);

    /**
     *
     * @param stdNum
     * @return
     */
    public int stdNumExists(@Param(value = "stdNum") String stdNum);

    /**
     *
     * @param id
     * @return
     */
    public Standard queryById(String id);

    /**
     *
     * @param standard
     * @return
     */
    public int modifyStandard(Standard standard);

    /**
     *
     * @param ids
     * @return
     */
    public int delStandard(Integer[] ids);


}