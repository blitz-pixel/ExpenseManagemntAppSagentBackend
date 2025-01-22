CREATE TABLE `Profile`(
    `profile_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account_id` VARCHAR(255) NOT NULL,
    `profile_name` DATETIME NOT NULL,
    `total_limit` VARCHAR(255) NULL,
    `created_at` TIMESTAMP NOT NULL,
    `frequency` ENUM('') NULL,
    `limit_start_date` TIMESTAMP NULL,
    `limit_end_date` TIMESTAMP NULL,
    `currency_notation` ENUM('') NOT NULL DEFAULT '₹'
);
CREATE TABLE `Account`(
    `account_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(), `username` BIGINT NOT NULL, `password` VARCHAR(255) NOT NULL, `email` VARCHAR(255) NOT NULL);
CREATE TABLE `Transaction`(
    `transaction_id` BIGINT NOT NULL,
    `profile_id` BIGINT NOT NULL,
    `date` DATETIME NOT NULL,
    `type` ENUM('') NOT NULL,
    `amount` BIGINT NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY(`transaction_id`)
);
CREATE TABLE `Categories`(
    `category_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `profile_id` BIGINT NOT NULL,
    `type` ENUM('') NOT NULL,
    `name` BIGINT NOT NULL,
    `parent_id` BIGINT NULL,
    `is_recurring` BOOLEAN NOT NULL DEFAULT '0'
);
CREATE TABLE Settings(
    `profile_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `dark_mode` BOOLEAN NOT NULL default FALSE,
    `currency_notation` ENUM('') NOT NULL DEFAULT '₹',
    `notification_preferences` BOOLEAN NOT NULL default TRUE
);
CREATE TABLE `Recurring_transactions`(
    `transaction_id` BIGINT NOT NULL,
    `profile_id` BIGINT NOT NULL,
    `is_active` BOOLEAN NOT NULL,
    `amount` BIGINT NOT NULL,
    `prev_date` DATE NOT NULL,
    `next_date` DATE NOT NULL,
    PRIMARY KEY(`transaction_id`)
);
ALTER TABLE
    `Recurring_transactions` ADD PRIMARY KEY(`profile_id`);
CREATE TABLE `Category_limits`(
    `budget_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `category_id` BIGINT NOT NULL,
    `profile_id` BIGINT NOT NULL,
    `start_date` DATE NULL,
    `end_date` DATE NULL
);
ALTER TABLE
    `Categories` ADD CONSTRAINT `categories_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `Profile`(`profile_id`);
ALTER TABLE
    `Transaction` ADD CONSTRAINT `transaction_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `Profile`(`profile_id`);
ALTER TABLE
    `Category_limits` ADD CONSTRAINT `category_limits_category_id_foreign` FOREIGN KEY(`category_id`) REFERENCES `Categories`(`category_id`);
ALTER TABLE
    `Profile` ADD CONSTRAINT `profile_account_id_foreign` FOREIGN KEY(`account_id`) REFERENCES `Account`(`account_id`);
ALTER TABLE
    `Recurring_transactions` ADD CONSTRAINT `recurring_transactions_transaction_id_foreign` FOREIGN KEY(`transaction_id`) REFERENCES `Transaction`(`transaction_id`);
ALTER TABLE
    `Profile` ADD CONSTRAINT `profile_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `Recurring_transactions`(`profile_id`);
ALTER TABLE
    `Category_limits` ADD CONSTRAINT `category_limits_profile_id_foreign` FOREIGN KEY(`profile_id`) REFERENCES `Profile`(`profile_id`);