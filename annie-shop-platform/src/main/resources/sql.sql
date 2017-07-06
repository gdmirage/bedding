CREATE TABLE `product` (
  `PRODUCT_ID` bigint(20) NOT NULL auto_increment COMMENT 'PRODUCT_ID',
  `PRODUCT_NAME` varchar(100) collate utf8_bin default NULL COMMENT '产品名称',
  `PRODUCT_CONTENT` text collate utf8_bin COMMENT '产品内容',
  `PRODUCT_UNIT` varchar(10) collate utf8_bin default NULL COMMENT '产品单位',
  `PRODUCT_MAIN_PIC` varchar(200) collate utf8_bin default NULL COMMENT '产品主图地址',
  `ROOT_TYPE_ID` int(11) default NULL COMMENT '一级分类ID',
  `PARENT_TYPE_ID` int(11) default NULL COMMENT '二级分类ID',
  `PRODUCT_TYPE_ID` int(11) default NULL COMMENT '三级分类ID',
  `CREATE_MAN` int(11) default NULL COMMENT '创建人',
  `CREATE_DATE` datetime default NULL COMMENT '创建时间',
  `MODIFY_MAN` int(11) default NULL COMMENT '修改人',
  `MODIFY_DATE` datetime default NULL COMMENT '修改时间',
  `IS_DELETE` char(1) collate utf8_bin default NULL COMMENT '是否删除',
  PRIMARY KEY  (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `product_type` (
  `ID` int(11) NOT NULL auto_increment,
  `TYPE_NAME` varchar(20) collate utf8_bin default NULL COMMENT '类型名称',
  `PIC_PATH` varchar(200) collate utf8_bin default NULL COMMENT '图片地址',
  `PARENT_ID` int(11) default NULL COMMENT '父ID',
  `LEVEL` int(11) default NULL COMMENT '级别',
  `IS_USE` char(1) collate utf8_bin default NULL COMMENT '是否有效',
  `IS_DELETE` char(1) collate utf8_bin default NULL COMMENT '是否删除',
  `CREATE_MAN` int(11) default NULL COMMENT '创建人',
  `CREATE_DATE` datetime default NULL COMMENT '创建时间',
  `MODIFY_MAN` int(11) default NULL COMMENT '修改人',
  `MODIFY_DATE` datetime default NULL COMMENT '修改时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;