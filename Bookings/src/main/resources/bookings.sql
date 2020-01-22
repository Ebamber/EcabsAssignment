CREATE TABLE bookings (
  bookingId varchar(255) NOT NULL,
  passengerName varchar(255) DEFAULT NULL,
  passengerContactNumber varchar(255) DEFAULT NULL,
  pickupTime datetime DEFAULT NULL,
  asap tinyint(1) DEFAULT NULL,
  waitingTime int DEFAULT NULL,
  noOfPassengers int DEFAULT NULL,
  price decimal(19, 2) DEFAULT NULL,
  rating int DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  lastModifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (bookingId)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE bookings
ADD UNIQUE INDEX UK_bookings_bookingId (bookingId);