CREATE TABLE user (
      id INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(50) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL,
      first_name VARCHAR(100) NOT NULL,
      last_name VARCHAR(100) NOT NULL,
      gender ENUM('M', 'F') NOT NULL,
      address TEXT,
      contact_phone VARCHAR(20),
      email VARCHAR(100) NOT NULL UNIQUE,
      profile_picture BLOB,
      credit_card_number VARCHAR(20),
      user_type VARCHAR(50)
);
