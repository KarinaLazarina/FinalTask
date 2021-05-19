CREATE DATABASE IF NOT EXISTS Hospital;
use Hospital;

CREATE TABLE doctor
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    age INT,
    specialization VARCHAR (25)
);

CREATE TABLE patient
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    date_of_birth DATE,
    doctor_id INT,
    diagnosis VARCHAR(50),
    status ENUM('awaiting appointment', 'in the process', 'discharged'),

    FOREIGN KEY (doctor_id) REFERENCES doctor (id) ON DELETE CASCADE
);

CREATE TABLE user
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR (25) UNIQUE,
    password VARCHAR(25),
    role ENUM('admin', 'doctor', 'nurse')
);

CREATE TABLE appointment
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25),
    user_id INT,
    patient_id INT,
    category ENUM('drug', 'procedure', 'operation'),

    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patient (id) ON DELETE CASCADE
);

