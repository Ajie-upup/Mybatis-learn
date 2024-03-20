-- `db-mybatis`.`order` definition

CREATE TABLE `order`
(
    `id`        int NOT NULL AUTO_INCREMENT,
    `ordertime` timestamp NULL DEFAULT NULL,
    `mount` double DEFAULT NULL,
    `uid`       int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;


-- `db-mybatis`.`role` definition

CREATE TABLE `role`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `rolename` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;


-- `db-mybatis`.`user` definition

CREATE TABLE `user`
(
    `id`       int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;


-- `db-mybatis`.user_role definition

CREATE TABLE `user_role`
(
    `user_id` int DEFAULT NULL,
    `role_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


INSERT INTO `db-mybatis`.`order` (id, ordertime, mount, uid)
VALUES (1, '2024-03-12 08:26:24', 100.0, 1),
       (2, '2024-03-13 08:26:24', 20.0, 1),
       (3, '2024-03-14 08:26:24', 30.0, 2),
       (4, '2024-03-15 08:26:24', 400.0, 3);
INSERT INTO `db-mybatis`.`role` (id, rolename)
VALUES (1, 'CEO'),
       (2, 'Worker'),
       (3, 'CFO'),
       (4, 'CFF');
INSERT INTO `db-mybatis`.`user` (id, username, password)
VALUES (1, 'tom', '123456'),
       (2, 'jack', '456789'),
       (3, 'user3', '123'),
       (4, 'testUpdate', 'testUpdate');
INSERT INTO `db-mybatis`.user_role (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 3);