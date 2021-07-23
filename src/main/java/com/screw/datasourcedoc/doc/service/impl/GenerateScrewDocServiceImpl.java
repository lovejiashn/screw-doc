package com.screw.datasourcedoc.doc.service.impl;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.screw.datasourcedoc.common.ResultJson;
import com.screw.datasourcedoc.doc.entity.DataSourcePro;
import com.screw.datasourcedoc.doc.service.GenerateScrewDocService;
import com.screw.datasourcedoc.doc.vo.ScrewDocVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 11:32
 **/
@Service
public class GenerateScrewDocServiceImpl implements GenerateScrewDocService {

    @Autowired
    private DataSourcePro dataSourcePro;

    @Override
    public ResultJson generateScrewDoc(ScrewDocVO screwDocVO) {
        DataSourcePro.Hikari hikari = dataSourcePro.getHikari();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dataSourcePro.getUrl());
        hikariConfig.setDriverClassName(dataSourcePro.getDriverClassName());
        hikariConfig.setUsername(dataSourcePro.getUsername());
        hikariConfig.setPassword(dataSourcePro.getPassword());
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema","true");
        hikariConfig.setMaximumPoolSize(hikari.getMaximumPoolSize());
        hikariConfig.setMinimumIdle(hikari.getMinimumIdle());
        DataSource dataSource = new HikariDataSource(hikariConfig);
        EngineFileType fileType = Objects.equals("doc",screwDocVO.getDocType()) ? EngineFileType.WORD :
                Objects.equals("html",screwDocVO.getDocType()) ? EngineFileType.HTML : EngineFileType.MD;
        //验证文件夹是否存在
        String docOutDir = screwDocVO.getDocOutDir();
        File filePath = new File(docOutDir);
        if (!filePath.exists() && !filePath.isDirectory()){
            filePath.mkdirs();
        }
        EngineConfig engineConfig = EngineConfig.builder()
                .openOutputDir(true)
                .fileName(screwDocVO.getDocName())
                .fileOutputDir(screwDocVO.getDocOutDir())
                .fileType(fileType)
                .produceType(EngineTemplateType.freemarker).build();
        //忽略不需要导出的表名
        List<String> ignoreTabs = StringUtils.isNotEmpty(screwDocVO.getIgnoreTables())
                ? Arrays.asList(screwDocVO.getIgnoreTables().split(","))
                : new ArrayList<>();
        //忽略表的前缀
        List<String> ignoreTabPrefix = StringUtils.isNotEmpty(screwDocVO.getIgnorePrefix()) ?
                Arrays.asList(screwDocVO.getIgnorePrefix().split(",")) :
                new ArrayList<>();
        //忽略表的后缀
        List<String> ignoreTabSuffix = StringUtils.isNotEmpty(screwDocVO.getIgnoreSuffix()) ?
                Arrays.asList(screwDocVO.getIgnoreSuffix().split(","))
                : new ArrayList<>();
        //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        ProcessConfig processConfig = ProcessConfig.builder()
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                .ignoreTableName(ignoreTabs)
                .ignoreTablePrefix(ignoreTabPrefix)
                .ignoreTableSuffix(ignoreTabSuffix)
                .build();

        Configuration config = Configuration.builder()
                .version("1.0.0")
                .description("数据库设计文档生成")
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(processConfig)
                .build();
        new DocumentationExecute(config).execute();
        return ResultJson.success();
    }
}
