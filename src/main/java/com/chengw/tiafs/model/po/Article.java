package com.chengw.tiafs.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chengw
 */
@Data
public class Article implements Serializable {


    private static final long serialVersionUID = -8675120166231446838L;
    private int aId;
    private String aName;
    private String aPath;
    private String publishDate;
    private String publishReader;
    private String uploadDate;
    private String summary;
    private String userCode;


}
