package com.single.controller;

import com.single.common.exception.CommonServiceException;
import com.single.common.vo.BaseRespVO;
import com.single.controller.respvo.AddPersonRespVO;
import com.single.service.PersonServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonServiceAPI personServiceAPI;

    @GetMapping("")
    public BaseRespVO query() throws CommonServiceException {
        return BaseRespVO.success(personServiceAPI.queryPersons());
    }

    public BaseRespVO update(String userId){
        personServiceAPI.updatePerson(userId);
        return BaseRespVO.success();
    }

    @PostMapping("/add")
    public BaseRespVO add(@RequestBody AddPersonRespVO respVO) throws CommonServiceException {
        personServiceAPI.addPerson(respVO);
        return BaseRespVO.success();
    }

}
