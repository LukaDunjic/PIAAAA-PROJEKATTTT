CREATE TABLE garden (
     id INT AUTO_INCREMENT PRIMARY KEY,
     owner INT,
     type ENUM('PRIVATE', 'RESTAURANT') NOT NULL,
     total_area DECIMAL(10,2),
     pool_area DECIMAL(10,2),
     greenery_area DECIMAL(10,2),
     furniture_area DECIMAL(10,2),
     fountain_area DECIMAL(10,2),
     FOREIGN KEY (owner) REFERENCES user(id) ON DELETE CASCADE
);
