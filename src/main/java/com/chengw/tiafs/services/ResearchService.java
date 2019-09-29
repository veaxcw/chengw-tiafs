package com.chengw.tiafs.services;


import com.chengw.tiafs.model.po.Research;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResearchService {
    List<Research> findByUserCode(@Param("userCode") String userCode);
    List<Research> findByRid(@Param("rId") int rid);
    List<Research> findByCondition(@Param("rId") int rid, @Param("rName") String rname);
    void updateResearch(@Param("research") Research research);
    void deleteResearch(@Param("rId") int rid);
    void saveResearch(@Param("research") Research research);
}
