package com.single.controller;

import com.single.common.vo.BaseRespVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaController {

    @PostMapping("/upload")
    public BaseRespVO uploadMidea(String userId, MultipartFile file){
        return BaseRespVO.success();
    }

    @GetMapping("")
    public BaseRespVO queryMedia(){
        return BaseRespVO.success();
    }

    @GetMapping("/:id")
    public BaseRespVO queryMediaById(){
        return BaseRespVO.success();
    }

}
