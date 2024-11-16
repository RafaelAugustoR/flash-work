CREATE TABLE category
(
    id   UUID        NOT NULL,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_service
(
    category_id UUID NOT NULL,
    service_id  UUID NOT NULL,
    CONSTRAINT pk_category_service PRIMARY KEY (category_id, service_id)
);

CREATE TABLE chat
(
    id         UUID NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_chat PRIMARY KEY (id)
);

CREATE TABLE education
(
    id                 UUID         NOT NULL,
    degree_type        VARCHAR(255) NOT NULL,
    year_of_completion date         NOT NULL,
    year_of_initiation date         NOT NULL,
    course             VARCHAR(100) NOT NULL,
    institution        VARCHAR(100) NOT NULL,
    created_at         TIMESTAMP WITHOUT TIME ZONE,
    user_id            UUID         NOT NULL,
    CONSTRAINT pk_education PRIMARY KEY (id)
);

CREATE TABLE messages
(
    id      UUID NOT NULL,
    content TEXT NOT NULL,
    user_id UUID NOT NULL,
    chat_id UUID NOT NULL,
    sent_at TIMESTAMP WITHOUT TIME ZONE,
    read_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_messages PRIMARY KEY (id)
);

CREATE TABLE notification
(
    id                UUID                        NOT NULL,
    content           TEXT                        NOT NULL,
    date              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_viewed         BOOLEAN                     NOT NULL,
    notification_type VARCHAR(255)                NOT NULL,
    sender_id         UUID                        NOT NULL,
    receiver_id       UUID                        NOT NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

CREATE TABLE proposal
(
    id                        UUID                        NOT NULL,
    service_id                UUID                        NOT NULL,
    freelancer_id             UUID                        NOT NULL,
    status                    VARCHAR(255)                NOT NULL,
    offer_amount              DOUBLE PRECISION            NOT NULL,
    estimated_completion_time date                        NOT NULL,
    message                   TEXT,
    requested_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    completed_at              TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_proposal PRIMARY KEY (id)
);

CREATE TABLE review
(
    id          UUID             NOT NULL,
    rating      DOUBLE PRECISION NOT NULL,
    description TEXT,
    target_id   UUID             NOT NULL,
    review_type VARCHAR(255)     NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    reviewer_id UUID             NOT NULL,
    CONSTRAINT pk_review PRIMARY KEY (id)
);

CREATE TABLE service
(
    id          UUID         NOT NULL,
    title       VARCHAR(80)  NOT NULL,
    description TEXT         NOT NULL,
    budget      VARCHAR(255) NOT NULL,
    deadline    date         NOT NULL,
    work_type   VARCHAR(255) NOT NULL,
    location    VARCHAR(50)  NOT NULL,
    status      VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    client_id   UUID         NOT NULL,
    CONSTRAINT pk_service PRIMARY KEY (id)
);

CREATE TABLE users
(
    id              UUID         NOT NULL,
    name            VARCHAR(150) NOT NULL,
    email           VARCHAR(80)  NOT NULL,
    password        VARCHAR(255) NOT NULL,
    cpf             VARCHAR(14),
    phone           VARCHAR(15),
    profession      VARCHAR(50),
    description     TEXT,
    profile_picture VARCHAR(255),
    birth_date      date,
    role            VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE users_chat
(
    chat_id UUID NOT NULL,
    user_id UUID NOT NULL
);

ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

ALTER TABLE users
    ADD CONSTRAINT uc_users_cpf UNIQUE (cpf);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_phone UNIQUE (phone);

ALTER TABLE education
    ADD CONSTRAINT FK_EDUCATION_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_CHAT FOREIGN KEY (chat_id) REFERENCES chat (id);

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_RECEIVER FOREIGN KEY (receiver_id) REFERENCES users (id);

ALTER TABLE notification
    ADD CONSTRAINT FK_NOTIFICATION_ON_SENDER FOREIGN KEY (sender_id) REFERENCES users (id);

ALTER TABLE proposal
    ADD CONSTRAINT FK_PROPOSAL_ON_FREELANCER FOREIGN KEY (freelancer_id) REFERENCES users (id);

ALTER TABLE proposal
    ADD CONSTRAINT FK_PROPOSAL_ON_SERVICE FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE review
    ADD CONSTRAINT FK_REVIEW_ON_REVIEWER FOREIGN KEY (reviewer_id) REFERENCES users (id);

ALTER TABLE service
    ADD CONSTRAINT FK_SERVICE_ON_CLIENT FOREIGN KEY (client_id) REFERENCES users (id);

ALTER TABLE category_service
    ADD CONSTRAINT fk_catser_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_service
    ADD CONSTRAINT fk_catser_on_service FOREIGN KEY (service_id) REFERENCES service (id);

ALTER TABLE users_chat
    ADD CONSTRAINT fk_usecha_on_chat FOREIGN KEY (chat_id) REFERENCES chat (id);

ALTER TABLE users_chat
    ADD CONSTRAINT fk_usecha_on_user FOREIGN KEY (user_id) REFERENCES users (id);