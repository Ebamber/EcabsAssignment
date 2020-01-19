CREATE TABLE bookings.audits (
  transactionId int NOT NULL AUTO_INCREMENT,
  event varchar(255) DEFAULT NULL,
  bookingId varchar(255) DEFAULT NULL,
  PRIMARY KEY (transactionId)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE bookings.audits
ADD CONSTRAINT FK_audits_bookingId FOREIGN KEY (bookingId)
REFERENCES bookings.bookings (bookingId);