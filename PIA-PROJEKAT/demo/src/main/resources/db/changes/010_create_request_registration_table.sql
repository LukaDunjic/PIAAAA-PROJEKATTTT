CREATE TABLE request_registration (
      id INT AUTO_INCREMENT PRIMARY KEY,
      user_id INT NOT NULL,
      status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
      FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
