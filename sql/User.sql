create database if not exists lessann_cloud;

CREATE TABLE `user`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp   NOT NULL DEFAULT '0000-00-00 00:00:00',
    `delete_time` timestamp   NOT NULL DEFAULT '0000-00-00 00:00:00',
    `name`        varchar(20)          DEFAULT NULL,
    `user_name`   varchar(50) NOT NULL,
    `password`    varchar(50) NOT NULL,
    `email`       varchar(100)         DEFAULT NULL,
    `phone`       varchar(20)          DEFAULT NULL,
    `image`       varchar(100)         DEFAULT NULL,
    `status`      int(11)              DEFAULT '1',
    `code`        varchar(50)          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10002
  DEFAULT CHARSET = utf8mb4;