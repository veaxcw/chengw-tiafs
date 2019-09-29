package com.chengw.tiafs.model.po;

import lombok.Data;

@Data
public class Research {
    private int rId;
    private String rName;
    private String rPath;
    private String publishDate;
    private String publishReader;
    private String uploadDate;
    private String summary;
    private String funds;
    private String userCode;

}
