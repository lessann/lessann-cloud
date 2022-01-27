create database if not exists lessann_cloud;

CREATE TABLE `user`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `createTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp,
    `deleteTime` timestamp,
    `name`       varchar(20)          DEFAULT NULL,
    `userName`   varchar(50) NOT NULL,
    `password`   varchar(50) NOT NULL,
    `email`      varchar(100)         DEFAULT NULL,
    `phone`      varchar(20)          DEFAULT NULL,
    `image`      varchar(100)         DEFAULT NULL,
    `status`     int(11)              DEFAULT '0',
    `code`       varchar(50)          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4;