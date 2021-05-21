DROP DATABASE IF EXISTS Hospital;
CREATE DATABASE IF NOT EXISTS Hospital;
use Hospital;

# CREATE TABLE doctor
# (
#     id    INT PRIMARY KEY AUTO_INCREMENT,
#     first_name VARCHAR(25),
#     last_name VARCHAR(25),
#     age INT,
#     specialization VARCHAR (25)
# );
CREATE TABLE user
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    login                 VARCHAR(25) UNIQUE,
    password              VARCHAR(25),
    first_name            VARCHAR(25),
    last_name             VARCHAR(25),
    age                   INT,
    role                  ENUM ('admin', 'doctor', 'nurse'),
    doctor_specialization VARCHAR(25)

#     FOREIGN KEY (doctor_description_id) REFERENCES doctor_description (id)
);

# CREATE TABLE doctor_description
# (
#     id             INT PRIMARY KEY AUTO_INCREMENT,
#     specialization VARCHAR(25),
#     phone_number   VARCHAR(10)
# );

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

INSERT INTO user(login, password, role, first_name, last_name)
VALUES ('admin', '123', 'admin', 'admin', 'admin');

INSERT INTO user(login, password, role, first_name, last_name, age, doctor_specialization)
VALUES ('doctor1', '123', 'doctor', 'Tom', 'Marks', 35, 'pediatrician');

INSERT INTO user(login, password, role, first_name, last_name, age, doctor_specialization)
VALUES ('doctor2', '123', 'doctor', 'Marry', 'Smith', 40, 'surgeon');

INSERT INTO patient(first_name, last_name, date_of_birth, doctor_id, status)
VALUES ('Ann', 'Snow', '2015-02-patient10', 2, 'awaiting appointment');

INSERT INTO diagnosis(patient_id, name_of_diagnosis)
VALUES (1, 'cold');
