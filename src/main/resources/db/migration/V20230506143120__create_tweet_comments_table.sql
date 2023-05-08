CREATE TABLE `tweet_comments`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(255) NOT NULL,
    `created_on` TIMESTAMP NOT NULL,
    `tweet_id` BIGINT NOT NULL,
    `sender_id` BIGINT NOT NULL
);