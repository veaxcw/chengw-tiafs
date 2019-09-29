package com.chengw.tiafs.mapper;
import com.chengw.tiafs.model.po.Research;
import com.chengw.tiafs.model.po.Researchresult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface ResearchResultDAO {
    List<Researchresult> findByUserCode(@Param("userCode") String userCode);//根据UserCode查找

    Researchresult findByRrId(@Param("rrId") int rrId);

    void updateResearchResult(@Param("research") Research research);

    void deleteResearchResult(@Param("rrId") int rrId);

    void saveResearchResult(@Param("research") Research research);
}
