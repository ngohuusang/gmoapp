SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `gmostore_db` ;
CREATE SCHEMA IF NOT EXISTS `gmostore_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `gmostore_db` ;

-- -----------------------------------------------------
-- Table `gmostore_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`category` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `description` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `order` TINYINT(4) NULL DEFAULT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `category_id` INT(11) NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_category_category1`
    FOREIGN KEY (`category_id` )
    REFERENCES `gmostore_db`.`category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`category` (`id` ASC) ;

CREATE INDEX `fk_category_category1` ON `gmostore_db`.`category` (`category_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`platform` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`platform` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `icon` VARCHAR(100) NOT NULL ,
  `custom_code` VARCHAR(255) NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`platform` (`id` ASC) ;

CREATE UNIQUE INDEX `name_UNIQUE` ON `gmostore_db`.`platform` (`name` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`publisher` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`publisher` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `description` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `website` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `phone` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`publisher` (`id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`product` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `full_name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `package_name` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `current_version` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `promotion_text` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `promotion_image_url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `description` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `recent_change` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `icon` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `status` ENUM('published','draft','locked') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `video_url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `recommand` TINYINT(1) NULL DEFAULT NULL ,
  `view_count` INT(11) NOT NULL DEFAULT '0' ,
  `shared_count` INT(11) NOT NULL DEFAULT '0' ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `category_id` INT(11) NOT NULL ,
  `platform_id` INT(11) NOT NULL ,
  `publisher_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_id` )
    REFERENCES `gmostore_db`.`category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_platform1`
    FOREIGN KEY (`platform_id` )
    REFERENCES `gmostore_db`.`platform` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_publisher1`
    FOREIGN KEY (`publisher_id` )
    REFERENCES `gmostore_db`.`publisher` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`product` (`id` ASC) ;

CREATE INDEX `fk_product_category1` ON `gmostore_db`.`product` (`category_id` ASC) ;

CREATE INDEX `fk_product_platform1` ON `gmostore_db`.`product` (`platform_id` ASC) ;

CREATE INDEX `fk_product_publisher1` ON `gmostore_db`.`product` (`publisher_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`user` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `sex` TINYINT(4) NULL DEFAULT NULL ,
  `birthday` INT(11) NULL DEFAULT NULL ,
  `status` ENUM('actived','inactived','locked') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL DEFAULT 'inactived' ,
  `gold` INT(11) NULL DEFAULT '0' ,
  `diamonds` INT(11) NULL DEFAULT '0' ,
  `create_date` BIGINT(20) NULL DEFAULT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `active_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`user` (`id` ASC) ;

CREATE UNIQUE INDEX `username_UNIQUE` ON `gmostore_db`.`user` (`username` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`comment` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `content` TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `created_date` BIGINT(20) NULL DEFAULT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_reason` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_comment_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`comment` (`id` ASC) ;

CREATE INDEX `fk_comment_user1` ON `gmostore_db`.`comment` (`user_id` ASC) ;

CREATE INDEX `fk_comment_product1` ON `gmostore_db`.`comment` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`device_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`device_info` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`device_info` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `os_version` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `phone_number` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`device_info` (`id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`contact` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`contact` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `phone` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `device_info_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_contact_device_info1`
    FOREIGN KEY (`device_info_id` )
    REFERENCES `gmostore_db`.`device_info` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`contact` (`id` ASC) ;

CREATE INDEX `fk_contact_device_info1` ON `gmostore_db`.`contact` (`device_info_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`version` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`version` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `code` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `description` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_version_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`version` (`id` ASC) ;

CREATE INDEX `fk_version_product1` ON `gmostore_db`.`version` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`download_link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`download_link` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`download_link` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `status` ENUM('alive','dead') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `note` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `download_count` INT(11) NOT NULL DEFAULT '0' ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `version_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_download_link_version1`
    FOREIGN KEY (`version_id` )
    REFERENCES `gmostore_db`.`version` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`download_link` (`id` ASC) ;

CREATE INDEX `fk_download_link_version1` ON `gmostore_db`.`download_link` (`version_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`user_action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`user_action` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`user_action` (
  `id` INT(11) NOT NULL ,
  `name` MEDIUMINT(9) NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `gmostore_db`.`history_action`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`history_action` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`history_action` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `version` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  `user_action_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_history_action_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_action_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_action_user_action1`
    FOREIGN KEY (`user_action_id` )
    REFERENCES `gmostore_db`.`user_action` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`history_action` (`id` ASC) ;

CREATE INDEX `fk_history_action_user1` ON `gmostore_db`.`history_action` (`user_id` ASC) ;

CREATE INDEX `fk_history_action_product1` ON `gmostore_db`.`history_action` (`product_id` ASC) ;

CREATE INDEX `fk_history_action_user_action1` ON `gmostore_db`.`history_action` (`user_action_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`payment_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`payment_method` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`payment_method` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`payment_method` (`id` ASC) ;

CREATE UNIQUE INDEX `type_UNIQUE` ON `gmostore_db`.`payment_method` (`type` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`history_recharge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`history_recharge` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`history_recharge` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gold` INT(11) NOT NULL DEFAULT '0' ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  `payment_method_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_history_recharge_payment_method1`
    FOREIGN KEY (`payment_method_id` )
    REFERENCES `gmostore_db`.`payment_method` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_recharge_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`history_recharge` (`id` ASC) ;

CREATE INDEX `fk_history_recharge_user1` ON `gmostore_db`.`history_recharge` (`user_id` ASC) ;

CREATE INDEX `fk_history_recharge_payment_method1` ON `gmostore_db`.`history_recharge` (`payment_method_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`promotion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`promotion` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`promotion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `description` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `diamond` INT(11) NOT NULL DEFAULT '0' ,
  `gold` INT(11) NOT NULL DEFAULT '0' ,
  `point` INT(11) NOT NULL DEFAULT '0' ,
  `start_date` BIGINT(20) NULL DEFAULT NULL ,
  `end_date` BIGINT(20) NULL DEFAULT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_promotion_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`promotion` (`id` ASC) ;

CREATE INDEX `fk_promotion_product1` ON `gmostore_db`.`promotion` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`history_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`history_transaction` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`history_transaction` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `purchased_date` BIGINT(20) NOT NULL ,
  `price` DOUBLE NOT NULL DEFAULT '0' ,
  `point` INT(11) NOT NULL DEFAULT '0' ,
  `user_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  `promotion_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_history_transaction_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_transaction_promotion1`
    FOREIGN KEY (`promotion_id` )
    REFERENCES `gmostore_db`.`promotion` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_transaction_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`history_transaction` (`id` ASC) ;

CREATE INDEX `fk_history_transaction_user1` ON `gmostore_db`.`history_transaction` (`user_id` ASC) ;

CREATE INDEX `fk_history_transaction_product1` ON `gmostore_db`.`history_transaction` (`product_id` ASC) ;

CREATE INDEX `fk_history_transaction_promotion1` ON `gmostore_db`.`history_transaction` (`promotion_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`list_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`list_type` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`list_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `image_url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `order_type` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `position` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `manual` BIT(1) NULL DEFAULT NULL ,
  `create_date` BIGINT(20) NULL DEFAULT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`list_type` (`id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`list_type_has_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`list_type_has_product` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`list_type_has_product` (
  `list_type_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`list_type_id`, `product_id`) ,
  CONSTRAINT `fk_list_type_has_product_list_type1`
    FOREIGN KEY (`list_type_id` )
    REFERENCES `gmostore_db`.`list_type` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_list_type_has_product_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_list_type_has_product_product1` ON `gmostore_db`.`list_type_has_product` (`product_id` ASC) ;

CREATE INDEX `fk_list_type_has_product_list_type1` ON `gmostore_db`.`list_type_has_product` (`list_type_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`price`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`price` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`price` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `gold` INT(11) NOT NULL DEFAULT '0' ,
  `diamond` INT(11) NOT NULL DEFAULT '0' ,
  `create_date` BIGINT(20) NOT NULL ,
  `start_date` BIGINT(20) NULL DEFAULT NULL ,
  `end_date` BIGINT(20) NULL DEFAULT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_price_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`price` (`id` ASC) ;

CREATE INDEX `fk_price_product1` ON `gmostore_db`.`price` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`rating` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`rating` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `point` INT(11) NOT NULL DEFAULT '0' ,
  `rated_date` BIGINT(20) NULL DEFAULT NULL ,
  `mark` INT(11) NOT NULL DEFAULT '0' ,
  `user_id` INT(11) NOT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_rating_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rating_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`rating` (`id` ASC) ;

CREATE INDEX `fk_rating_user1` ON `gmostore_db`.`rating` (`user_id` ASC) ;

CREATE INDEX `fk_rating_product1` ON `gmostore_db`.`rating` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`screen_shot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`screen_shot` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`screen_shot` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `image_url` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `device_type` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `status` BIT(1) NULL DEFAULT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `product_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_screen_shot_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`screen_shot` (`id` ASC) ;

CREATE INDEX `fk_screen_shot_product1` ON `gmostore_db`.`screen_shot` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`sns`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`sns` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`sns` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `sns_name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `sns_type` TINYINT(4) NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_sns_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`sns` (`id` ASC) ;

CREATE INDEX `fk_sns_user1` ON `gmostore_db`.`sns` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`sns_friend_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`sns_friend_list` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`sns_friend_list` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `sns_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_sns_friend_list_sns1`
    FOREIGN KEY (`sns_id` )
    REFERENCES `gmostore_db`.`sns` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`sns_friend_list` (`id` ASC) ;

CREATE INDEX `fk_sns_friend_list_sns1` ON `gmostore_db`.`sns_friend_list` (`sns_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`status_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`status_history` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`status_history` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `status` ENUM('active','inactived') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL DEFAULT 'active' ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_status_history_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`status_history` (`id` ASC) ;

CREATE INDEX `fk_status_history_user` ON `gmostore_db`.`status_history` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`user_device`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`user_device` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`user_device` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `login_date` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` INT(11) NOT NULL ,
  `device_info_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_user_device_device_info1`
    FOREIGN KEY (`device_info_id` )
    REFERENCES `gmostore_db`.`device_info` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_device_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`user_device` (`id` ASC) ;

CREATE INDEX `fk_user_device_user1` ON `gmostore_db`.`user_device` (`user_id` ASC) ;

CREATE INDEX `fk_user_device_device_info1` ON `gmostore_db`.`user_device` (`device_info_id` ASC) ;


-- -----------------------------------------------------
-- Table `gmostore_db`.`wish_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gmostore_db`.`wish_list` ;

CREATE  TABLE IF NOT EXISTS `gmostore_db`.`wish_list` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `create_date` BIGINT(20) NOT NULL ,
  `update_date` BIGINT(20) NULL DEFAULT NULL ,
  `delete_date` BIGINT(20) NULL DEFAULT NULL ,
  `product_id` INT(11) NOT NULL ,
  `user_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_wish_list_product1`
    FOREIGN KEY (`product_id` )
    REFERENCES `gmostore_db`.`product` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_wish_list_user1`
    FOREIGN KEY (`user_id` )
    REFERENCES `gmostore_db`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE UNIQUE INDEX `id_UNIQUE` ON `gmostore_db`.`wish_list` (`id` ASC) ;

CREATE INDEX `fk_wish_list_product1` ON `gmostore_db`.`wish_list` (`product_id` ASC) ;

CREATE INDEX `fk_wish_list_user1` ON `gmostore_db`.`wish_list` (`user_id` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
