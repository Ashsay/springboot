package com.single.common.vo;

import com.single.common.exception.CommonServiceException;
import lombok.Data;

public abstract class BaseReqVO {

    public abstract void checkParams() throws CommonServiceException;

}
