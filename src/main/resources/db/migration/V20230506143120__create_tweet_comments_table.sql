CREATE TABLE `tweet_comments`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(3000) NOT NULL,
    `created_on` BIGINT NOT NULL,
    `tweet_id` BIGINT NOT NULL,
    `sender_id` BIGINT NOT NULL
);