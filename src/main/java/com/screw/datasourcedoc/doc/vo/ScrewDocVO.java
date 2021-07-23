package com.screw.datasourcedoc.doc.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 14:26
 **/
@Data
@Accessors(chain = true)
public class ScrewDocVO {
    /**
     * 导出文件名称
     */
    @NotEmpty(message = "请填写导出文件名称")
    private String docName;
    /**
     * 导出文件路径
     */
    @NotEmpty(message = "请填写导出文件路径")
    private String docOutDir;
    /**
     * 导出文件类型：html:html格式  doc：word格式   md:markdown格式
     */
    @NotEmpty(message = "请填写导出文件类型")
    private String docType;
    /**
     * 忽略数据库表
     */
    private String ignoreTables;
    /**
     * 忽略表前缀
     */
    private String ignorePrefix;
    /**
     * 忽略表后缀
     */
    private String ignoreSuffix;
}
