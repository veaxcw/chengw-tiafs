package com.chengw.tiafs.mapper;

import com.chengw.tiafs.model.po.Research;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface ResearchDAO {

    List<Research> findByUserCode(@Param("userCode") String userCode);//根据UserCode查找

    List<Research> findByRid(@Param("rid") int rid);

    List<Research> findByCondition(@Param("rid") int rid, @Param("rname") String rname);

    void updateResearch(@Param("research") Research research);

    void deleteResearch(@Param("rid") int rid);

    void saveResearch(@Param("research") Research research);

}
