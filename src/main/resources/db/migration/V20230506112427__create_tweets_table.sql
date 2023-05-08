CREATE TABLE `tweets`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(3000) NULL,
    `created_on` BIGINT NOT NULL,
    `sender_id` BIGINT NOT NULL
);