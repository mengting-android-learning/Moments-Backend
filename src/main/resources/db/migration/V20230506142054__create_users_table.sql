CREATE TABLE `users`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `profile_image` VARCHAR(1000) NULL,
    `avatar` VARCHAR(1000) NULL,
    `nick` VARCHAR(255) NULL,
    `user_name` VARCHAR(255) NOT NULL UNIQUE KEY
);