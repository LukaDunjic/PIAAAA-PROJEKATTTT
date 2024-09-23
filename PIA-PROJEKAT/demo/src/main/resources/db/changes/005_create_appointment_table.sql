CREATE TABLE appointment (
      id INT AUTO_INCREMENT PRIMARY KEY,
      owner INT,
      firm_id BIGINT,
      garden_id INT,
      appointment_date DATETIME,
      status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
      FOREIGN KEY (owner) REFERENCES user(id) ON DELETE CASCADE,
      FOREIGN KEY (firm_id) REFERENCES firm(id) ON DELETE CASCADE,
      FOREIGN KEY (garden_id) REFERENCES garden(id) ON DELETE CASCADE
);
