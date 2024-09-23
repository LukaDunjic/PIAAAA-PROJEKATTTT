CREATE TABLE decorated_garden (
      id INT AUTO_INCREMENT PRIMARY KEY,
      garden_id INT NOT NULL,
      decorator_id INT NOT NULL,
      FOREIGN KEY (garden_id) REFERENCES garden(id) ON DELETE CASCADE,
      FOREIGN KEY (decorator_id) REFERENCES decorator(id) ON DELETE CASCADE
);
