CREATE TABLE t_department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    code VARCHAR(255),
    number_of_employees INT
);

CREATE TABLE t_employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    id_department INT,
    FOREIGN KEY (id_department) REFERENCES t_department(id)
);