/*
 * Create schema with name jpa
 */
CREATE SCHEMA `sample1` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

/*
 * Create table with name item
 */
CREATE  TABLE `item` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `descriptions` VARCHAR(45) NULL ,
  `deleted` CHAR(1) NULL DEFAULT 'N' ,
  `created_date` TIMESTAMP NOT NULL DEFAULT 0000 ,
  `modified_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`));

 /*
 * Create table with name color
 */
CREATE  TABLE color (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `item_id` INT(11) NULL ,
  `color` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Color_1` (`item_id` ASC) ,
  CONSTRAINT `fk_Color_1`
    FOREIGN KEY (`item_id` )
    REFERENCES `Item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
 
 /*
 * Create table with name user
 */
CREATE TABLE user (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
)

INSERT INTO user
(
`USERNAME`,
`PASSWORD`,
`ROLE`)
VALUES
(
'anant',
'anant',
'ROLE_USER'
);

INSERT INTO user
(
`USERNAME`,
`PASSWORD`,
`ROLE`)
VALUES
(
'pinak',
'pinak',
'ROLE_ADMIN'
);

