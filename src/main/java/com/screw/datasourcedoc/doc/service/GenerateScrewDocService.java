package com.screw.datasourcedoc.doc.service;

import com.screw.datasourcedoc.common.ResultJson;
import com.screw.datasourcedoc.doc.vo.ScrewDocVO;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 11:31
 **/
public interface GenerateScrewDocService {

    /**
     * 生成数据库表设计文档
     * @param screwDocVO 表设计实体
     * @return 返回结果
     */
    ResultJson generateScrewDoc(ScrewDocVO screwDocVO);
}
