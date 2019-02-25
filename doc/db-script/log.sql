-- 建议表名和项目名称一致
CREATE TABLE `LOGGER_INFO` (
`LOGID`  bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
`CLASS`  varchar(200) NULL COMMENT '全限定类名',
`METHOD`  varchar(100) NULL COMMENT '方法名' ,
`LOGLEVEL` varchar(50) NULL COMMENT '日志级别',
`MSG` mediumtext null	COMMENT '日志内容',
`CREATETIME`  TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY (`LOGID`)
);
