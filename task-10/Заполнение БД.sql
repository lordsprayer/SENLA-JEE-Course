-- -----------------------------------------------------
-- Data for table `Task10`.`product`
-- -----------------------------------------------------
START TRANSACTION;
USE `Task10`;
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '123', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '145', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '150', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '160', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '170', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '180', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '190', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('C', '195', 'PC');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '210', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '220', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '230', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '240', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '250', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '260', 'Laptop');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '310', 'Printer');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '320', 'Printer');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '330', 'Printer');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '340', 'Printer');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('B', '350', 'Printer');
INSERT INTO `Task10`.`product` (`maker`, `model`, `type`) VALUES ('A', '360', 'Printer');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Task10`.`pc`
-- -----------------------------------------------------
START TRANSACTION;
USE `Task10`;
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (1, '123', 400, 512, 80, '12x', 450);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (2, '145', 800, 1024, 120, '12x', 550);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (3, '150', 1000, 512, 100, '24x', 580);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (4, '160', 800, 1024, 120, '12x', 1200);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (5, '170', 400, 512, 100, '24x', 1050);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (6, '180', 600, 1024, 120, '8x', 470);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (7, '190', 1000, 1536, 160, '32x', 1800);
INSERT INTO `Task10`.`pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (8, '195', 1000, 1024, 160, '16x', 1400);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Task10`.`laptop`
-- -----------------------------------------------------
START TRANSACTION;
USE `Task10`;
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (1, '210', 300, 512, 80, 1200, 10);
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (2, '220', 800, 1024, 120, 1400, 12);
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (3, '230', 1000, 1536, 160, 1800, 12);
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (4, '240', 800, 512, 80, 1000, 8);
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (5, '250', 600, 1024, 100, 1250, 10);
INSERT INTO `Task10`.`laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (6, '260', 800, 1024, 120, 1300, 12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Task10`.`printer`
-- -----------------------------------------------------
START TRANSACTION;
USE `Task10`;
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (1, '310', 'y', 'Laser', 1000);
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (2, '320', 'y', 'Jet', 800);
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (3, '330', 'n', 'Matrix', 600);
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (4, '340', 'n', 'Jet', 700);
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (5, '350', 'y', 'Laser', 1200);
INSERT INTO `Task10`.`printer` (`code`, `model`, `color`, `type`, `price`) VALUES (6, '360', 'y', 'Laser', 1300);

COMMIT;

