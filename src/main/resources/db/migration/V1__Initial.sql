CREATE TABLE book (
      id bigint NOT NULL AUTO_INCREMENT,
      author varchar(255) NOT NULL,
      isbn varchar(255) NOT NULL,
      pages int NOT NULL,
      price decimal(5,2) NOT NULL,
      publication_date date NOT NULL,
      publisher varchar(512) NOT NULL,
      quantity_in_stock bigint NOT NULL,
      title varchar(512) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE KEY UK_isbn (isbn)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE customer (
      id bigint NOT NULL AUTO_INCREMENT,
      address varchar(512) NOT NULL,
      email varchar(255) NOT NULL,
      first_name varchar(255) NOT NULL,
      last_name varchar(255) NOT NULL,
      phone_number varchar(255) NOT NULL,
      PRIMARY KEY (id),
      UNIQUE KEY UK_email (email)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE orders (
        id bigint NOT NULL AUTO_INCREMENT,
        order_date datetime(6) NOT NULL,
        order_number bigint NOT NULL,
        status enum('CANCELLED','CREATED','DELIVERED','FULFILLED','IN_TRANSIT','PROBLEM','PROCESSING','RETURNED','SHIPPED') NOT NULL,
        order_total decimal(10,2) NOT NULL,
        customer_id bigint NOT NULL,
        PRIMARY KEY (id),
        UNIQUE KEY UK_order_number (order_number),
        KEY FK_customer_id (customer_id),
        CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE line_item (
     id bigint NOT NULL AUTO_INCREMENT,
     book_id bigint DEFAULT NULL,
     order_id bigint DEFAULT NULL,
     quantity bigint DEFAULT NULL,
     order_price decimal(5,2) NOT NULL,
     PRIMARY KEY (id),
     KEY FK_book_id (book_id),
     KEY FK_order_id (order_id),
     CONSTRAINT FK_book_id FOREIGN KEY (book_id) REFERENCES book (id),
     CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES orders (id)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

