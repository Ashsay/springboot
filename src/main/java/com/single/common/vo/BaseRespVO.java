package com.single.common.vo;

import com.single.common.exception.CommonServiceException;
import lombok.Data;

@Data
public class BaseRespVO<M> {

    private Integer code;
    private String msg;
    private M data;

    public static BaseRespVO success(){
        BaseRespVO response = new BaseRespVO();
        response.setCode(200);
        response.setMsg("success");
        return  response;
    }

    public static<M> BaseRespVO success(M data){
        BaseRespVO response = new BaseRespVO();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static<M> BaseRespVO serviceException(CommonServiceException e){
        BaseRespVO response = new BaseRespVO();
        response.setCode(e.getCode());
        response.setMsg(e.getMessage());
        return response;
    }

}
