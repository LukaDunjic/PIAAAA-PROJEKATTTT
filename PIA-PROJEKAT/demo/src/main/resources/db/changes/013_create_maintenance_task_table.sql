CREATE TABLE maintenance_task (
      id INT AUTO_INCREMENT PRIMARY KEY,
      appointment_id INT,
      num_pools INT DEFAULT 0,
      num_fountains INT DEFAULT 0,
      estimated_completion_time DATETIME,
      booking_time DATETIME DEFAULT NOW(),
      status ENUM('PENDING', 'APPROVED', 'FINISHED') DEFAULT 'PENDING',
      FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);
