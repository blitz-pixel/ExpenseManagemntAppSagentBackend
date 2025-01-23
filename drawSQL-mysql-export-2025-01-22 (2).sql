-- Profile Table
CREATE TABLE `Profile` (
                           `profile_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `account_id` BIGINT NOT NULL,
                           `profile_name` VARCHAR(255) NOT NULL,
                           `total_limit` DECIMAL(10, 2) DEFAULT NULL,
                           `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `currency_notation` ENUM('₹', '$', '€', '£') NOT NULL DEFAULT '₹',
                           CONSTRAINT `profile_account_id_foreign` FOREIGN KEY (`account_id`) REFERENCES `Account`(`account_id`) ON DELETE CASCADE
);

-- Account Table
CREATE TABLE `Account` (
                           `account_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                           `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `username` VARCHAR(255) NOT NULL,
                           `password` VARCHAR(255) NOT NULL,
                           `email` VARCHAR(255) NOT NULL UNIQUE
);

-- Budget Table
CREATE TABLE `Budget` (
                          `budget_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          `profile_id` BIGINT NOT NULL,
                          `budget_name` VARCHAR(255) NOT NULL,
                          `amount` DECIMAL(10, 2) NOT NULL,
                          `budget_start_date` DATE NOT NULL,
                          `budget_end_date` DATE NOT NULL,
                          `budget_frequency` ENUM('daily', 'weekly', 'monthly', 'yearly') NOT NULL,
                          CONSTRAINT `budget_profile_id_foreign` FOREIGN KEY (`profile_id`) REFERENCES `Profile`(`profile_id`) ON DELETE CASCADE
);

-- Transaction Table
CREATE TABLE `Transaction` (
                               `transaction_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                               `profile_id` BIGINT NOT NULL,
                               `date` DATETIME NOT NULL,
                               `type` ENUM('income', 'expense') NOT NULL,
                               `amount` DECIMAL(10, 2) NOT NULL,
                               CONSTRAINT `transaction_profile_id_foreign` FOREIGN KEY (`profile_id`) REFERENCES `Profile`(`profile_id`) ON DELETE CASCADE
);

-- Categories Table
CREATE TABLE `Categories` (
                              `category_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              `profile_id` BIGINT NOT NULL,
                              `type` ENUM('income', 'expense') NOT NULL,
                              `name` VARCHAR(255) NOT NULL,
                              `parent_id` BIGINT DEFAULT NULL,
                              `is_recurring` BOOLEAN NOT NULL DEFAULT FALSE,
                              CONSTRAINT `categories_profile_id_foreign` FOREIGN KEY (`profile_id`) REFERENCES `Profile`(`profile_id`) ON DELETE CASCADE,
                              CONSTRAINT `categories_parent_id_foreign` FOREIGN KEY (`parent_id`) REFERENCES `Categories`(`category_id`) ON DELETE SET NULL
);

-- Recurring Transactions Table
CREATE TABLE `Recurring_transactions` (
                                          `transaction_id` BIGINT NOT NULL PRIMARY KEY,
                                          `profile_id` BIGINT NOT NULL,
                                          `is_active` BOOLEAN NOT NULL DEFAULT TRUE,
                                          `amount` DECIMAL(10, 2) NOT NULL,
                                          `prev_date` DATE NOT NULL,
                                          `next_date` DATE NOT NULL,
                                          CONSTRAINT `recurring_transactions_transaction_id_foreign` FOREIGN KEY (`transaction_id`) REFERENCES `Transaction`(`transaction_id`) ON DELETE CASCADE,
                                          CONSTRAINT `recurring_transactions_profile_id_foreign` FOREIGN KEY (`profile_id`) REFERENCES `Profile`(`profile_id`) ON DELETE CASCADE
);

-- Category Limits Table
CREATE TABLE `Category_limits` (
                                   `budget_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                   `category_id` BIGINT NOT NULL,
                                   `profile_id` BIGINT NOT NULL,
                                   `start_date` DATE DEFAULT NULL,
                                   `end_date` DATE DEFAULT NULL,
                                   CONSTRAINT `category_limits_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `Categories`(`category_id`) ON DELETE CASCADE,
                                   CONSTRAINT `category_limits_profile_id_foreign` FOREIGN KEY (`profile_id`) REFERENCES `Profile`(`profile_id`) ON DELETE CASCADE
);
