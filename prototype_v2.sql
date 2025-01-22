CREATE TABLE User (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    last_active DATETIME,
    emailID VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Account (
    account_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    type ENUM('Savings', 'Current', 'Credit', 'Other') NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Transaction (
    transaction_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(500),
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    type ENUM('Credit', 'Debit') NOT NULL,
    date DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (account_id) REFERENCES Account(account_id)
);

CREATE TABLE Expense (
    expense_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(500),
    mode_of_payment ENUM('Cash', 'Card', 'Online', 'Other') NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Revenue (
    revenue_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(500),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

CREATE TABLE Category (
    category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    expense_limit BIGINT,
    FOREIGN KEY (category_id) REFERENCES Expense(expense_id)
);

CREATE TABLE Notification (
    notification_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id BIGINT NOT NULL,
    message VARCHAR(1000) NOT NULL,
    type ENUM('Info', 'Warning', 'Alert') NOT NULL,
    status BOOLEAN DEFAULT FALSE,
    read_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Account_Summary (
    account_id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    date DATE NOT NULL,
    total_expense BIGINT,
    total_revenue BIGINT,
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE AuditLog (
    log_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    action_type ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    table_name VARCHAR(255) NOT NULL,
    action_timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE Goal (
    goal_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    target_amount BIGINT NOT NULL,
    saved_amount BIGINT DEFAULT 0,
    due_date DATE,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
