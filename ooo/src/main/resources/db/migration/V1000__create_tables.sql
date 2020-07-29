CREATE TABLE users (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       login VARCHAR(256) NOT NULL,
                       email VARCHAR(256) NOT NULL,
                       password VARCHAR(256) NOT NULL,
                       enabled boolean NOT NULL default true,
                       role VARCHAR(32) NOT NULL default 'USER',
                       PRIMARY KEY (id)
);
CREATE TABLE record_journal (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(256) NOT NULL,
                           description VARCHAR(256) NOT NULL,
                           time_now DATE NOT NULL,
                           time DATE NOT NULL,
                           user_id BIGINT NOT NULL,
                           status BOOLEAN,
                           PRIMARY KEY (id),
                           CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);