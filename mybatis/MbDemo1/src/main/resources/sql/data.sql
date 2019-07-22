-- 替换成自己实际的 SQL 文件
CREATE TABLE user (
  id int(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(33) DEFAULT NULL COMMENT '姓名',
  age int(3) DEFAULT NULL COMMENT '年龄',
  money double DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (id)
) ENGINE=InnoDB;