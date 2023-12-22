/* create DB for Hotel Management */
CREATE DATABASE HotelManagement;


/* create master table room */
CREATE TABLE room (id INT PRIMARY KEY, price DOUBLE NOT NULL);


/* create table room_booking with reference to room table */
CREATE TABLE room_booking (
    room_number INT AUTO_INCREMENT PRIMARY KEY,
    id INT NOT NULL,
    is_booked BOOLEAN NOT NULL,
    customer_id INT,
    booking_time TIMESTAMP,
    FOREIGN KEY (id) REFERENCES room(id)); 
 
    
/* create master table food_items */
CREATE TABLE food_item (item_id INT PRIMARY KEY, item VARCHAR(50), item_price DOUBLE NOT NULL);


/* create table food_orders with reference to food_items table */
CREATE TABLE food_orders (
    token INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT NOT NULL,
    item VARCHAR(255),
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (item_id) REFERENCES food_item(item_id));


/* query to check room availability for specified bookingId between start and end date provided */
SELECT COUNT(*) FROM room_booking b
JOIN room r ON b.id = r.id 
WHERE b.id = booking_id
AND (booking_end_time < b.booking_start_time OR booking_start_time > b.booking_end_time);
 



