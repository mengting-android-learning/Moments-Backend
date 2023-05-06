CREATE TABLE `tweet_images`
(
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `url` VARCHAR(200) NOT NULL,
    `tweet_id` BIGINT NOT NULL
);