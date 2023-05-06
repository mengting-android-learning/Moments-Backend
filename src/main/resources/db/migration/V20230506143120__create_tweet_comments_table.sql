CREATE TABLE `tweet_comments`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `content` VARCHAR(200) NOT NULL,
    `created_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    `tweet_id` BIGINT NOT NULL,
    `sender_id` BIGINT NOT NULL
);