/*
SQLyog Community Edition- MySQL GUI v7.12 
MySQL - 5.6.26 : Database - rwd_bootstrap_resource
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`rwd_bootstrap_resource` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `rwd_bootstrap_resource`;

/*Table structure for table `exception_record` */

DROP TABLE IF EXISTS `exception_record`;

CREATE TABLE `exception_record` (
  `date` datetime NOT NULL,
  `cause` varchar(1024) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `exception_record` */

insert  into `exception_record`(`date`,`cause`) values ('2016-05-12 17:00:04','Unresolved compilation problem: \n	Syntax error on token \"class\", Identifier expected\n'),('2016-05-13 14:49:36','\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null\r\n### The error may involve com.rwd.bootstrap.mapper.ExceptionRecordMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into exception_record (date, cause)     values (?, ?)\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null\n; SQL []; Column \'cause\' cannot be null; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null'),('2016-05-13 14:53:12','\r\n### Error updating database.  Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null\r\n### The error may involve com.rwd.bootstrap.mapper.ExceptionRecordMapper.insert-Inline\r\n### The error occurred while setting parameters\r\n### SQL: insert into exception_record (date, cause)     values (?, ?)\r\n### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null\n; SQL []; Column \'cause\' cannot be null; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Column \'cause\' cannot be null');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `img` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '圖片',
  `account` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '帳號',
  `password` varchar(128) COLLATE utf8_unicode_ci NOT NULL COMMENT '密碼',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `user` */

insert  into `user`(`id`,`img`,`account`,`password`) values (1,'D:\\eclipse-mars\\workspace\\SpringRestv2\\WebContent\\resources\\userImg\\admin.png','admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
