DROP SCHEMA IF EXISTS payment_system;

CREATE SCHEMA payment_system CHARACTER SET utf8 COLLATE utf8_general_ci;
USE payment_system;

CREATE TABLE `role` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `role` VALUES (1,'Admin'),(2,'User');

CREATE TABLE `users` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `role_idrole` INTEGER NOT NULL,
  `series_number_passport` varchar(45) DEFAULT NULL,
  `identification_number_passport` varchar(45) DEFAULT NULL,
  `codeword` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `residence_registr_data_passport` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  CONSTRAINT `fk_users_role1` FOREIGN KEY (`role_idrole`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES (1,'Ale','111','Alexandr','Kedd','Маяковского 114, кв 16',1,'BB26898','46646v66','каваль','+375338942326','Маяковского 114, кв 16'),
                           (2,'Dmitr','222','Dmitriy','Yushko','Deriglazova, 20 flat 27',2,'VV99999','99999v99','venom','+375336897422','Deriglazova, 20 flat 666'),
                           (3,'Vova','333','Vladimir','Smirnov','Baranova, 2 flat 9',2,'BB98743','11236v742','lucky','+375293213971','Baranova, 2 flat 9'),
                           (4,'Fed','444','Fedor','Kashaev','Pushkina, 20 flat 36',2,'BB38946','94380v984','fedric','+375299439620','Pushkina, 20 flat 36'),
                           (5,'Nilik','555','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,NULL,NULL,NULL,NULL,NULL),
                           (6,'Niliki','555','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,'BB78123','48937v69','yen','+37293697841','Dolgobrodskaya 2, flat 20'),
                           (7,'Nilikiii','666','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,'BB26898','46646v66','ius','+37293697841','Dolgobrodskaya 2, flat 20'),
                           (8,'Nilik34','777','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,'BB26898','46646v66','geen','+37293697841','Dolgobrodskaya 2, flat 20'),
                           (9,'Nilikttt','888','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,'BB26898','46646v66','geben','+37293697841','Dolgobrodskaya 2, flat 20'),
						   (10,'Niliktt55t','888','Roma','Nilikovskiy','Dolgobrodskaya 2, flat 20',2,'BB26898','46646v66','geben','+37293697841','Dolgobrodskaya 2, flat 20');

CREATE TABLE `currency` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name_currency` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `currency` VALUES (1,'USD'),(2,'BYN'),(3,'RUB');

CREATE TABLE `status` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `status` VALUES (1,'locked'),(2,'unlocked');

CREATE TABLE `account` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `number` varchar(100) NOT NULL,
  `balance` NUMERIC(8, 2) DEFAULT NULL,
  `creation_date` DATE DEFAULT NULL,
  `iduser` INTEGER NOT NULL,
  `idstatus` INTEGER NOT NULL,
  `currency_idcurrency` INTEGER NOT NULL,
  UNIQUE (`number`),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_account_currency1` FOREIGN KEY (`currency_idcurrency`) REFERENCES `currency` (`id`),
  CONSTRAINT `fk_account_status1` FOREIGN KEY (`idstatus`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_account_user1` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT INTO `account` VALUES (1,'86fdc196-89f0-4626-a859-5194c2cd3093',38,'2019-10-21',1,2,2),
                             (2,'a5045bae-7d44-47e3-a5fe-aa30801ea0a8',30,'2019-10-21',2,2,2),
                             (3,'8910b0d8-435e-4fb5-8f21-69da418282be',90,'2019-10-21',3,2,2),
                             (4,'dcf56486-8339-476e-a205-5a9fad6091ec',69,'2019-10-21',4,2,2),
                             (5,'2ac91bdc-0cfd-46c8-a0dc-96680a154eb9',36,'2019-10-21',5,2,2),
                             (6,'46c9e8cb-a580-46fc-9ecb-73187fb5d07d',56,'2019-10-21',6,2,2),
                             (7,'88ead771-62bc-4c94-a7dc-17a769d899cc',93,'2019-10-21',7,2,2),
                             (8,'bc63f814-8e75-424b-8c9a-ba41ba7d0952',72,'2019-10-21',8,2,2),
                             (9,'641d26c2-be4f-415d-8849-77afaa93405a',0,'2019-10-21',9,2,2),
                             (10,'459b4f52-bdf8-40d0-aca8-d56699999879',0,'2019-10-21',10,2,2),
                             (11,'e4210dad-4ed1-411c-8077-f7d6a1a80698',0,'2019-10-21',10,2,2),
                             (12,'b29bff17-c614-4573-99a3-dbc90a8b5bf5',0,'2019-10-21',10,2,1),
                             (13,'809bfc7f-4917-4249-bca0-84d5f5060bca',0,'2019-10-21',10,2,1),
                             (14,'218fbdad-16b4-430a-8e41-61303782ba1b',0,'2019-10-21',10,2,2);
                             
CREATE TABLE `name_card` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name_card` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `name_card` VALUES (1,'GOLD'),(2,'PRIME'),(3,'GUIDE'),(4,'HOTPOTATO');

CREATE TABLE `payment_system_card` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `type_paym_syst_card` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `payment_system_card` VALUES (1,'VISA'),(2,'MASTERCARD'),(3,'BELCARD');

CREATE TABLE `deposits` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `currency_idcurrency` INTEGER NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_deposits_currency1` FOREIGN KEY (`currency_idcurrency`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `list_deposits` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `users_idusers` INTEGER NOT NULL,
  `deposits_iddeposits` INTEGER NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_list_deposits_deposits1` FOREIGN KEY (`deposits_iddeposits`) REFERENCES `deposits` (`id`),
  CONSTRAINT `fk_list_deposits_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `card` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `creation_date` DATE DEFAULT NULL,
  `account_idaccount` INTEGER NOT NULL,
  `payment_system_card_idpayment_system_card` INTEGER NOT NULL,
  `name_card_idname_card` INTEGER NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_card_account1` FOREIGN KEY (`account_idaccount`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_card_name_card1` FOREIGN KEY (`name_card_idname_card`) REFERENCES `name_card` (`id`),
  CONSTRAINT `fk_card_payment_system_card1` FOREIGN KEY (`payment_system_card_idpayment_system_card`) REFERENCES `payment_system_card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

INSERT INTO `card` VALUES (1,'2019-10-21',1,1,1),
                          (2,'2019-10-21',2,2,2),
                          (3,'2019-10-21',3,3,4),
                          (4,'2019-10-21',4,2,3),
                          (5,'2019-10-21',5,3,4),
                          (6,'2019-10-21',6,1,2),
                          (7,'2019-10-21',7,3,1),
                          (8,'2019-10-21',8,3,3),
                          (9,'2019-10-21',9,1,3),
                          (10,'2019-10-21',10,3,4),
                          (11,'2019-10-21',11,3,4),
                          (12,'2019-10-21',12,1,1),
                          (13,'2019-10-21',13,1,1),
                          (14,'2019-10-21',14,2,3);

CREATE TABLE `payments` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `date` DATE DEFAULT NULL,
  `price` NUMERIC(8, 2) DEFAULT NULL,
  `idaccount` INTEGER NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payments_account1` FOREIGN KEY (`idaccount`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
