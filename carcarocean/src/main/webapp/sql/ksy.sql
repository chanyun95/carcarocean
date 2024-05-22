-- 판매 정보 테이블
CREATE TABLE SELL (
	sell_num NUMBER NOT NULL,
	sell_mile NUMBER NOT NULL,
	sell_cnumber VARCHAR2(100) NOT NULL,
	sell_maker VARCHAR2(100) NOT NULL,
	sell_cname VARCHAR2(100) NOT NULL,
	sell_name VARCHAR2(50) NOT NULL,
	sell_phone VARCHAR2(11) NOT NULL,
	sell_place1 VARCHAR2(100)NOT NULL,
	sell_place2 VARCHAR2(100) NOT NULL,
	sell_date NUMBER NOT NULL,
	sell_check NUMBER(1) NOT NULL DEFAULT 0,
	sell_reg DATE NOT NULL DEFAULT SYSDATE,
	sell_modify DATE,
	mem_num NUMBER NOT NULL,
	constraint sell_pk primary key (sell_num),
	constraint sell_fk foreign key (mem_num) references member (mem_num)
);
CREATE SEQUENCE SELL_SEQ;

-- 검수자 테이블
CREATE TABLE CHECKER(
	checker_num NUMBER NOT NULL,
	checker_name VARCHAR2(15) NOT NULL,
	checker_photo VARCHAR2(400) NOT NULL,
	checker_company VARCHAR2(30) NOT NULL,
	checker_phone VARCHAR2(11) NOT NULL,
	checker_opinion VARCHAR2(150) NOT NULL,
	CONSTRAINT checker_pk PRIMARY KEY (checker_num)
);
CREATE CHECKER_SEQ;

-- 차량 테이블
CREATE TABLE CAR(
	car_num NUMBER NOT NULL,
	car_maker VARCHAR2(50) NOT NULL,
	car_name VARCHAR2(50) NOT NULL,
	car_size NUMBER(1) NOT NULL,
	car_birth DATE NOT NULL,
	car_fuel_type NUMBER(1) NOT NULL,
	car_mile NUMBER NOT NULL,
	car_price NUMBER NOT NULL,
	car_color VARCHAR2(20) NOT NULL,
	car_photo VARCHAR2(400) NOT NULL,
	car_auto NUMBER(1),
	car_fuel_efficiency NUMBER NOT NULL,
	car_use NUMBER NOT NULL,
	car_cc NUMBER NOT NULL,
	car_accident VARCHAR2(100) NOT NULL,
	car_owner_change NUMBER NOT NULL,
	car_design_op VARCHAR2(400) NOT NULL,
	car_con_op VARCHAR2(400) NOT NULL,
	car_drive_op VARCHAR2(400) NOT NULL,
	checker_num NUMBER NOT NULL,
	CONSTRAINT car_pk PRIMARY KEY (car_num),
	CONSTRAINT car_fk FOREIGN KEY (checker_num) REFERENCES checker (checker_num)
)
CREATE SEQUENCE CAR_SEQ;

-- 관심 차량 테이블
CREATE TABLE FAVORITE_CAR (
	fav_num NUMBER NOT NULL,
	car_num NUMBER NOT NULL,
	mem_num NUMBER NOT NULL,
	CONSTRAINT favorite_car_fk1 FOREIGN KEY (car_num) REFERENCES car (car_num),
	CONSTRAINT favorite_car_fk2 FOREIGN KEY (mem_num) REFERENCES member (mem_num)
)
CREATE SEQUENCE FAVORITE_CAR_SEQ;