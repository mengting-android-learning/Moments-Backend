CREATE TABLE `tweets`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(255) NULL,
    `created_on` TIMESTAMP NOT NULL,
    `sender_id` BIGINT NOT NULL
);