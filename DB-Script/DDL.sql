CREATE DATABASE `accountdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use accountdb;
CREATE TABLE `user` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `MiddleName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) NOT NULL,
  `UserName` varchar(25) NOT NULL,
  `MobileNo` varchar(10) NOT NULL,
  `EmailId` varchar(50) NOT NULL,
  `DOB` datetime NOT NULL,
  `ActiveStatus` varchar(1) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `address` (
  `AddressId` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `BuildingNo` varchar(45) NOT NULL,
  `Block` varchar(200) NOT NULL,
  `City` varchar(45) DEFAULT NULL,
  `PinCode` varchar(6) NOT NULL,
  `AddressType` varchar(20) NOT NULL,
  `Alias` varchar(20) DEFAULT NULL,
  `IsDefault` varchar(1) NOT NULL,
  PRIMARY KEY (`AddressId`),
  KEY `UserId_idx` (`UserId`),
  CONSTRAINT `UserId` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `payment` (
  `PaymentId` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `CardType` varchar(6) NOT NULL,
  `CardNo` varchar(19) NOT NULL,
  `ExpiryMonth` varchar(2) NOT NULL,
  `ExpiryYear` varchar(4) NOT NULL,
  `CardAlias` varchar(15) DEFAULT NULL,
  `IsDefault` varchar(1) NOT NULL,
  PRIMARY KEY (`PaymentId`),
  KEY `userid1_idx` (`UserId`),
  CONSTRAINT `userid1` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DATABASE `inventorydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use inventorydb;
CREATE TABLE `seller` (
  `SellerId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `RegistrationNo` varchar(20) NOT NULL,
  `JoiningDate` datetime NOT NULL,
  PRIMARY KEY (`SellerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `category` (
  `CategoryId` int NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(45) NOT NULL,
  `ParentId` int DEFAULT NULL,
  `Description` varchar(45) NOT NULL,
  `IsMandatory` varchar(1) NOT NULL,
  PRIMARY KEY (`CategoryId`),
  KEY `ParentId_idx` (`ParentId`),
  CONSTRAINT `ParentId` FOREIGN KEY (`ParentId`) REFERENCES `category` (`CategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `productsummary` (
  `ProductId` int NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(45) NOT NULL,
  `SellerId` int NOT NULL,
  `CategoryId` int NOT NULL,
  `Description` varchar(999) NOT NULL,
  `Price` int NOT NULL,
  `Denomination` varchar(3) NOT NULL DEFAULT 'INR',
  PRIMARY KEY (`ProductId`),
  KEY `SellerId_idx` (`SellerId`),
  KEY `CategoryId_idx` (`CategoryId`),
  CONSTRAINT `CategoryId` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`),
  CONSTRAINT `SellerId` FOREIGN KEY (`SellerId`) REFERENCES `seller` (`SellerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `productdetails` (
  `ProductDetailsId` int NOT NULL AUTO_INCREMENT,
  `ProductId` int NOT NULL,
  `CategoryName` varchar(45) NOT NULL,
  `CategoryValue` varchar(45) NOT NULL,
  PRIMARY KEY (`ProductDetailsId`),
  KEY `ProductId_idx` (`ProductId`),
  CONSTRAINT `ProductId` FOREIGN KEY (`ProductId`) REFERENCES `productsummary` (`ProductId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE DATABASE `deliverydb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use deliverydb;
CREATE TABLE `purchase_info` (
  `PurchaseId` int NOT NULL AUTO_INCREMENT,
  `UserId` int NOT NULL,
  `SellerId` int NOT NULL,
  `ProductId` int NOT NULL,
  `PaymentMode` varchar(15) NOT NULL,
  `PurchaseDate` datetime NOT NULL,
  `Destination` int NOT NULL,
  PRIMARY KEY (`PurchaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `delivery_tracker` (
  `TrackerId` int NOT NULL AUTO_INCREMENT,
  `Status` varchar(20) NOT NULL,
  `UpdatedOn` datetime NOT NULL,
  `PurchaseId` int NOT NULL,
  PRIMARY KEY (`TrackerId`),
  KEY `Purchase_ID_idx` (`PurchaseId`),
  CONSTRAINT `purchase_id` FOREIGN KEY (`PurchaseId`) REFERENCES `purchase_info` (`PurchaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

