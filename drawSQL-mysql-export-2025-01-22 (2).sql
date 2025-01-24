-- User Table
CREATE TABLE `User` (
                        `user_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `user_name` VARCHAR(255) NOT NULL,
                        `email` VARCHAR(255) NOT NULL UNIQUE,
                        `password` VARCHAR(255) NOT NULL, -- Store hashed passwords
                        `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Account Table
CREATE TABLE `Account` (
                           `account_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                           `user_id` BIGINT NOT NULL,
                           `account_name` VARCHAR(255) NOT NULL,
                           `type` ENUM('personal', 'shared') NOT NULL,
                           `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE
);

-- Budget Table
CREATE TABLE `Budget` (
                          `budget_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                          `account_id` BIGINT NOT NULL,
                          `budget_name` VARCHAR(255) NOT NULL,
                          `amount` DECIMAL(10,2) NOT NULL CHECK (`amount` > 0),
                          `budget_start_date` DATE NOT NULL,
                          `budget_end_date` DATE NOT NULL CHECK (`budget_end_date` >= `budget_start_date` ),
                          `budget_frequency` ENUM('MONTHLY', 'YEARLY') NOT NULL,
                          FOREIGN KEY (`account_id`) REFERENCES `Account`(`account_id`) ON DELETE CASCADE
);


-- -- User Account Access Table
CREATE TABLE `UserAccountAccess` (
                                      `user_id` BIGINT NOT NULL,
                                      `shared_account_id` BIGINT NOT NULL,
                                      `role` ENUM('ADMIN', 'EDITOR', 'VIEWER') NOT NULL DEFAULT 'ADMIN',
                                      `created_by` BIGINT NOT NULL,
                                      PRIMARY KEY (`user_id`, `shared_account_id`),
                                      FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE,
                                      FOREIGN KEY (`shared_account_id`) REFERENCES `Account`(`account_id`) ON DELETE CASCADE
 );

-- -- Audit Logs Table
CREATE TABLE `AuditLogs` (
                              `log_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                              `user_id` BIGINT NOT NULL,
                              `shared_account_id` BIGINT NOT NULL,
                              `action` ENUM('CREATE', 'UPDATE', 'DELETE') NOT NULL,
                              `record_id` BIGINT NOT NULL, -- Affected row ID
                              `table_name` VARCHAR(255) NOT NULL, -- Affected table
                              `access_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (`user_id`) REFERENCES `User`(`user_id`) ON DELETE CASCADE,
                              FOREIGN KEY (`shared_account_id`) REFERENCES `UserAccountAccess`(`shared_account_id`) ON DELETE CASCADE
);




-- Transactions (Account-specific)
CREATE TABLE `Transaction` (
                               `transaction_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                               `account_id` BIGINT NOT NULL,
                               `date` DATETIME NOT NULL,
                               `type` ENUM('income', 'expense') NOT NULL,
                               `amount` DECIMAL(10,2) NOT NULL CHECK (`amount` > 0),
                               `category_id` BIGINT NOT NULL,
                               FOREIGN KEY (`account_id`) REFERENCES `Account`(`account_id`) ON DELETE CASCADE,
                               FOREIGN KEY (`category_id`) REFERENCES `Category`(`category_id`)
);

-- Category (Account-specific)
CREATE TABLE Category (
                          category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          scope ENUM('USER', 'ACCOUNT') NOT NULL, -- Type of category scope
                          scope_id BIGINT NOT NULL,               -- Foreign key for user or account
                          type ENUM('income', 'expense') NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          parent_id BIGINT DEFAULT NULL,
                          FOREIGN KEY (parent_id) REFERENCES Category(category_id) ON DELETE SET NULL,
                          CONSTRAINT fk_scope_user FOREIGN KEY (scope_id)
                              REFERENCES User(user_id) ON DELETE CASCADE,
                          CONSTRAINT fk_scope_account FOREIGN KEY (scope_id)
                              REFERENCES Account(account_id) ON DELETE CASCADE
);


-- Recurring Transactions (Templates)
CREATE TABLE `RecurringTransaction` (
                                        `recurring_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        `account_id` BIGINT NOT NULL,
                                        `category_id` BIGINT NOT NULL,
                                        `amount` DECIMAL(10,2) NOT NULL CHECK (`amount` > 0), -- Ensure positive recurring amount
                                        `frequency` ENUM('DAILY', 'WEEKLY', 'MONTHLY', 'YEARLY') NOT NULL,
                                        `start_date` DATE NOT NULL,
                                        `end_date` DATE DEFAULT NULL CHECK (`end_date` >= `start_date`), -- Ensure valid date range
                                        `is_active` BOOLEAN DEFAULT TRUE,
                                        FOREIGN KEY (`account_id`) REFERENCES `Account`(`account_id`) ON DELETE CASCADE,
                                        FOREIGN KEY (`category_id`) REFERENCES `Category`(`category_id`)
);

-- Category Limits (Budget-specific)
CREATE TABLE `CategoryLimit` (
                                 `limit_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 `budget_id` BIGINT NOT NULL,
                                 `category_id` BIGINT NOT NULL,
                                 `amount` DECIMAL(10,2) NOT NULL CHECK (`amount` > 0), -- Ensure limit is positive
--                                  `Limit_frequency` ENUM('WEEKLY','MONTHLY', 'YEARLY') NOT NULL
                                 `limit_start_date` DATE NOT NULL,
                                  `limit_end_date` DATE NOT NULL CHECK (`limit_start_date` >= `limit_start_date` AND `limit_end_date`)
                                  `limit_frequency` ENUM('MONTHLY', 'YEARLY') NOT NULL,
                                 FOREIGN KEY (`budget_id`) REFERENCES `Budget`(`budget_id`) ON DELETE CASCADE,
                                 FOREIGN KEY (`category_id`) REFERENCES `Category`(`category_id`) ON DELETE CASCADE
);


