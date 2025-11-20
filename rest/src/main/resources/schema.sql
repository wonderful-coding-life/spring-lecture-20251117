CREATE TABLE member (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    age INT,
    password VARCHAR(255),
    enabled BOOLEAN,
    PRIMARY KEY (id)
);

CREATE TABLE article (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255),
    description VARCHAR(255),
    created DATETIME,
    updated DATETIME,
    member_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_article_member
        FOREIGN KEY (member_id)
        REFERENCES member(id)
);