# screw-doc
这个小程序主要使用的是screw进行开发：
所用工具：
    --SpringBoot2.4.1
    --lombok
    --Hikari连接池
    --Mysql8.0
---------------------------------------------------------------
暂不支持页面操作，可使用postman进行接口访问
提交方式：POST
提交格式：JSON
地址：http://localhost:8009/screw/doc/generate.do
提交参数：
{
    "docName":"测试导出功能",      --文件名称
    "docOutDir":"D:\\doc",            --文件存储地址（文件夹）
    "docType":"doc",                     --支持导出格式：html：html格式  doc：word格式   md：markdown格式
    "ignoreTables":"",                    --忽略的表，不需要导的表。多表用逗号","隔开
    "ignorePrefix":"",                     --忽略的前缀。多表用逗号","隔开
    "ignoreSuffix":""                      --忽略的后缀。多表用逗号","隔开
}
