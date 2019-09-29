package com.chengw.tiafs.services;


import com.chengw.tiafs.model.po.Researchresult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResearchResultService {
    List<Researchresult> findByUserCode(@Param("userCode") String userCode);//根据UserCode查找

    Researchresult findByRrid(@Param("rrid") int rrid);

    void updateResearchResult(@Param("researchResult") Researchresult researchResult);

    void deleteResearchResult(@Param("rrid") int rrid);

    void saveResearchResult(@Param("researchResult") Researchresult researchResult);
}
