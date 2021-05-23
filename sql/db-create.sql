DROP DATABASE IF EXISTS Hospital;
CREATE DATABASE IF NOT EXISTS Hospital;
use Hospital;

CREATE TABLE role
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(10)
);
CREATE TABLE specialization
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    specialization VARCHAR(25) UNIQUE
);

CREATE TABLE user
(
    id                       INT PRIMARY KEY AUTO_INCREMENT,
    login                    VARCHAR(25) UNIQUE,
    password                 VARCHAR(25),
    first_name               VARCHAR(25),
    last_name                VARCHAR(25),
    age                      INT,
    role_id                  INT,
    doctor_specialization_id INT,

    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (doctor_specialization_id) REFERENCES specialization (id)
);

CREATE TABLE patient
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(25),
    last_name     VARCHAR(25),
    date_of_birth DATE,
    doctor_id     INT,
    status        ENUM ('awaiting appointment', 'in the process', 'discharged'),

    FOREIGN KEY (doctor_id) REFERENCES user (id)
);

CREATE TABLE diagnosis
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    patient_id        INT,
    name_of_diagnosis VARCHAR(50),
    date              DATE,

    FOREIGN KEY (patient_id) REFERENCES patient (id)
);

CREATE TABLE prescription
(
    id                      INT PRIMARY KEY AUTO_INCREMENT,
    name_of_appointment     VARCHAR(25),
    user_id_of_execution    INT,
    user_id_of_prescription INT,
    patient_id              INT,
    category                ENUM ('drug', 'procedure', 'operation'),

    FOREIGN KEY (user_id_of_execution) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id_of_prescription) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE
);

INSERT INTO role(name)
VALUES ('admin'),
       ('doctor'),
       ('nurse');

INSERT INTO specialization(specialization)
VALUES ('surgeon'),
       ('pediatrician'),
       ('therapist'),
       ('dermatologist'),
       ('ophthalmologist');

INSERT INTO user(login, password, role_id, first_name, last_name)
VALUES ('admin', '123', 1, 'admin', 'admin');

INSERT INTO user(login, password, role_id, first_name, last_name, age, doctor_specialization_id)
VALUES ('doctor1', '123', 2, 'Tom', 'Marks', 35, 2);

INSERT INTO user(login, password, role_id, first_name, last_name, age, doctor_specialization_id)
VALUES ('doctor2', '123', 2, 'Marry', 'Smith', 40, 1);

INSERT INTO patient(first_name, last_name, date_of_birth, doctor_id, status)
VALUES ('Ann', 'Snow', '2015-02-10', 2, 'awaiting appointment');

CREATE
    TRIGGER date_of_diagnosis
    AFTER INSERT
    ON diagnosis
    FOR EACH ROW
BEGIN
    SET NEW.date = NOW();
END;


INSERT INTO diagnosis(patient_id, name_of_diagnosis)
VALUES (1, 'cold');
