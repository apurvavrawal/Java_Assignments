/* create DB for Hotel Management */
CREATE DATABASE HotelManagement;


/* create master table room */
CREATE TABLE room (
	room_id BIGINT PRIMARY KEY,
	price_per_day DOUBLE NOT NULL);


/* create table room_booking with reference to room table */
CREATE TABLE room_bookings (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT,
    customer_id BIGINT,
    booking_start_time DATETIME,
    booking_end_time DATETIME,
    is_booked BOOLEAN,
    total_price DOUBLE,
    FOREIGN KEY (room_id) REFERENCES room(room_id));
 

/* query to check room availability for specified bookingId between start and end date provided */
SELECT COUNT(*) FROM room_booking b
JOIN room r ON b.room_id = r.room_id
WHERE b.room_id = 1
AND (booking_end_time < b.booking_start_time AND booking_start_time > b.booking_end_time);

select count(*) FROM room_booking b JOIN room r ON b.room_id = r.room_id where b.room_id = 1;

SELECT COUNT(*) AS booking_count
FROM room_booking rb
         JOIN room r ON rb.room_id = r.room_id
WHERE rb.room_id = 1
  AND 2023-01-06 12:00:00.000000 >= rb.booking_start_time
AND 2023-01-05 10:00:00.000000 <= rb.booking_end_time;

    
/* create master table food_items */
CREATE TABLE food_items (
	food_item_id BIGINT PRIMARY KEY, 
	food_item_name VARCHAR(50),
	food_item_price DOUBLE NOT NULL);


/* create order table for maintaining order with total price */
CREATE TABLE orders (
    order_id BIGINT PRIMARY KEY,
    order_total DOUBLE);


/* create table food_orders with reference to food_items table */
CREATE TABLE food_orders (
    food_order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (food_item_id) REFERENCES food_items(food_item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id));

/* create table for feedback details */
CREATE TABLE feedback_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comments VARCHAR(1000) NOT NULL);
    
/* create table for complaint box details */
CREATE TABLE complaint_box (
    complaint_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    description VARCHAR(1000) NOT NULL);



