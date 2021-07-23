package com.screw.datasourcedoc.doc.controller;

import com.screw.datasourcedoc.common.ResultJson;
import com.screw.datasourcedoc.doc.service.GenerateScrewDocService;
import com.screw.datasourcedoc.doc.vo.ScrewDocVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 11:27
 **/
@RestController
@RequestMapping("/doc")
public class GenerateScrewDocRestController {

    @Autowired
    private GenerateScrewDocService screwDocService;
    @PostMapping("/generate.do")
    public ResultJson generateScrewDoc(@Validated @RequestBody ScrewDocVO screwDocVO){
       return screwDocService.generateScrewDoc(screwDocVO);
    }
}
