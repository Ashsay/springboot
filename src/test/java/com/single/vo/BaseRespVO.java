package com.single.vo;

import lombok.Data;

@Data
public class BaseRespVO<M> {

    private Integer code;
    private String msg;
    private M data;

    public BaseRespVO(){};

    public static<M> BaseRespVO success(M data){
        BaseRespVO response = new BaseRespVO();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

}
