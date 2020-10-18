package com.yicj.seata.common.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class AbstractResonse implements Serializable {
    private static final long serialVersionUID = 8131236360162671718L;
    private int code;
    private String msg;
}