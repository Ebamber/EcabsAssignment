CREATE TABLE bookings.waypoints (
  waypointId varchar(255) NOT NULL,
  lastStop tinyint(1) DEFAULT NULL,
  locality varchar(255) DEFAULT NULL,
  latitude decimal(10, 0) DEFAULT NULL,
  longitude decimal(10, 0) DEFAULT NULL,
  timestamp datetime DEFAULT NULL,
  bookingId varchar(255) NOT NULL,
  PRIMARY KEY (waypointId)
)
ENGINE = INNODB,
CHARACTER SET utf8mb4,
COLLATE utf8mb4_0900_ai_ci;

ALTER TABLE bookings.waypoints
ADD CONSTRAINT FK_waypoint_bookingId FOREIGN KEY (bookingId)
REFERENCES bookings.bookings (bookingId);