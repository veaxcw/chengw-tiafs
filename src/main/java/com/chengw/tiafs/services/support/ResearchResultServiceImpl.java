package com.chengw.tiafs.services.support;

import com.chengw.tiafs.mapper.ResearchResultDAO;
import com.chengw.tiafs.model.po.Researchresult;
import com.chengw.tiafs.services.ResearchResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class ResearchResultServiceImpl implements ResearchResultService {

    @Resource
    private ResearchResultDAO researchResultDAO;

    public List<Researchresult> findByUserCode(String userCode) {
        return researchResultDAO.findByUserCode(userCode);
    }

    public Researchresult findByRrid(int rrid) {
        return researchResultDAO.findByRrId(rrid);
    }

    public void updateResearchResult(Researchresult researchResult) {
        researchResultDAO.updateResearchResult(researchResult);
    }

    public void deleteResearchResult(int rrid) {
        researchResultDAO.deleteResearchResult(rrid);

    }

    public void saveResearchResult(Researchresult researchResult) {
        researchResultDAO.saveResearchResult(researchResult);
    }
}
