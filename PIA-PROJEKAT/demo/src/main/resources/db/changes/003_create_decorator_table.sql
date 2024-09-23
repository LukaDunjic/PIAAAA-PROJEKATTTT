CREATE TABLE decorator (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    firm_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (firm_id) REFERENCES firm(id) ON DELETE CASCADE
);