/*数据库 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_diary` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_diary`;

/*用户表 */;
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `imageName` varchar(40) DEFAULT NULL,
  `mood` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*日志类别表 */;
DROP TABLE IF EXISTS `t_diarytype`;

CREATE TABLE `t_diarytype` (
  `diaryTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`diaryTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*日志表 */;
DROP TABLE IF EXISTS `t_diary`;

CREATE TABLE `t_diary` (
  `diaryId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(60) DEFAULT NULL,
  `content` text,
  `typeId` int(11) DEFAULT NULL,
  `releaseDate` datetime DEFAULT NULL,
  PRIMARY KEY (`diaryId`),
  KEY `FK_t_diary` (`typeId`),
  CONSTRAINT `FK_t_diary` FOREIGN KEY (`typeId`) REFERENCES `t_diarytype` (`diaryTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*用户表插入数据 */;