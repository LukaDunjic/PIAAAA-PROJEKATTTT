CREATE TABLE comment (
     id INT AUTO_INCREMENT PRIMARY KEY,
     rating INT NOT NULL,
     comment VARCHAR(255) NOT NULL,
     appointment_id INT NOT NULL,
     CONSTRAINT fk_appointment_comment FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);
