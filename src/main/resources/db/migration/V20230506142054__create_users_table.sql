CREATE TABLE `users`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `profile_image` VARCHAR(200) NULL,
    `avatar` VARCHAR(200) NULL,
    `nick` VARCHAR(10) NULL,
    `user_name` VARCHAR(10) NOT NULL UNIQUE KEY
);