package com.chengw.tiafs.model.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author veax
 */
@Data
public class Role  implements Serializable {

    private static final long serialVersionUID = 6595799095866973601L;
    private Long id;
    private String name;

}
