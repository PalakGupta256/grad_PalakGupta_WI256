CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('ADMIN','OWNER')) NOT NULL
);

CREATE TABLE site (
    site_id INT PRIMARY KEY,
    site_type VARCHAR(30),
    length INT,
    width INT,
    sqft INT,
    is_occupied BOOLEAN,
    maintenance_rate INT
);

CREATE TABLE owner (
    owner_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100),
    site_id INT REFERENCES site(site_id),
    user_id INT REFERENCES users(user_id)
);

CREATE TABLE payment (
    payment_id SERIAL PRIMARY KEY,
    site_id INT REFERENCES site(site_id),
    total_amount INT,
    paid_amount INT,
    status VARCHAR(10) CHECK (status IN ('PAID','PENDING'))
);

CREATE TABLE update_request (
    request_id SERIAL PRIMARY KEY,
    owner_id INT REFERENCES owner(owner_id),
    site_id INT REFERENCES site(site_id),
    requested_change TEXT,
    status VARCHAR(10) CHECK (status IN ('PENDING','APPROVED','REJECTED'))
);


INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('owner1','owner123','OWNER'),
('owner2','owner123','OWNER'),
('owner3','owner123','OWNER'),
('owner4','owner123','OWNER'),
('owner5','owner123','OWNER'),
('owner6','owner123','OWNER'),
('owner7','owner123','OWNER'),
('owner8','owner123','OWNER'),
('owner9','owner123','OWNER'),
('owner10','owner123','OWNER');

INSERT INTO site VALUES
(1,'VILLA',40,60,2400,true,9),
(2,'APARTMENT',40,60,2400,true,9),
(3,'INDEPENDENT_HOUSE',40,60,2400,true,9),
(4,'OPEN_SITE',40,60,2400,false,6),
(5,'VILLA',40,60,2400,true,9),
(6,'APARTMENT',40,60,2400,true,9),
(7,'OPEN_SITE',40,60,2400,false,6),
(8,'INDEPENDENT_HOUSE',40,60,2400,true,9),
(9,'VILLA',40,60,2400,true,9),
(10,'OPEN_SITE',40,60,2400,false,6);

INSERT INTO site VALUES
(11,'APARTMENT',30,50,1500,true,9),
(12,'VILLA',30,50,1500,true,9),
(13,'OPEN_SITE',30,50,1500,false,6),
(14,'INDEPENDENT_HOUSE',30,50,1500,true,9),
(15,'APARTMENT',30,50,1500,true,9),
(16,'OPEN_SITE',30,50,1500,false,6),
(17,'VILLA',30,50,1500,true,9),
(18,'INDEPENDENT_HOUSE',30,50,1500,true,9),
(19,'OPEN_SITE',30,50,1500,false,6),
(20,'APARTMENT',30,50,1500,true,9);

INSERT INTO site VALUES
(21,'OPEN_SITE',30,40,1200,false,6),
(22,'VILLA',30,40,1200,true,9),
(23,'APARTMENT',30,40,1200,true,9),
(24,'INDEPENDENT_HOUSE',30,40,1200,true,9),
(25,'OPEN_SITE',30,40,1200,false,6),
(26,'VILLA',30,40,1200,true,9),
(27,'APARTMENT',30,40,1200,true,9),
(28,'OPEN_SITE',30,40,1200,false,6),
(29,'INDEPENDENT_HOUSE',30,40,1200,true,9),
(30,'VILLA',30,40,1200,true,9),
(31,'OPEN_SITE',30,40,1200,false,6),
(32,'APARTMENT',30,40,1200,true,9),
(33,'VILLA',30,40,1200,true,9),
(34,'OPEN_SITE',30,40,1200,false,6),
(35,'INDEPENDENT_HOUSE',30,40,1200,true,9);

INSERT INTO owner (name, phone, email, site_id, user_id) VALUES
('Ramesh Kumar','900000001','o1@mail.com',1,2),
('Suresh Rao','900000002','o2@mail.com',2,3),
('Anita Sharma','900000003','o3@mail.com',3,4),
('Vikram Singh','900000004','o4@mail.com',4,5),
('Priya Mehta','900000005','o5@mail.com',5,6),
('Arjun Nair','900000006','o6@mail.com',6,7),
('Neha Verma','900000007','o7@mail.com',7,8),
('Rahul Jain','900000008','o8@mail.com',8,9),
('Kavita Iyer','900000009','o9@mail.com',9,10),
('Amit Patel','900000010','o10@mail.com',10,11);

INSERT INTO payment (site_id, total_amount, paid_amount, status)
SELECT
    site_id,
    sqft * maintenance_rate,
    CASE WHEN site_id % 4 = 0 THEN sqft * maintenance_rate ELSE 0 END,
    CASE WHEN site_id % 4 = 0 THEN 'PAID' ELSE 'PENDING' END
FROM site;

INSERT INTO update_request (owner_id, site_id, requested_change, status) VALUES
(1,1,'Change site type from VILLA to APARTMENT','PENDING'),
(2,2,'Update phone number','APPROVED'),
(3,3,'Change site type to INDEPENDENT_HOUSE','REJECTED'),
(4,4,'Mark open site as occupied','PENDING'),
(5,5,'Update email address','APPROVED'),
(6,6,'Change site dimensions','PENDING');

