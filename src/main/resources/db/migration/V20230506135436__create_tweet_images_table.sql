CREATE TABLE `tweet_images`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `url` VARCHAR(255) NOT NULL,
    `tweet_id` BIGINT NOT NULL
);